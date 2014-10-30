package controllers
import components.ActiveSlickCake.cake._
import io.strongtyped.active.slick.exceptions.RowNotFoundException
import models.Player
import play.api.db.slick.{DBAction, _}
import play.api.libs.json.{Writes, JsError, JsSuccess, Json}
import play.api.mvc._

import scala.util.control.NonFatal
import scala.util.{Failure, Success, Try}

object PlayerController extends Controller {

  implicit val playerFormat = Json.format[Player]

  def api = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def list = DBAction { implicit rs =>
  	val allPlayers = Players.fetchAll
    Ok(Json.toJson(allPlayers))
  }

  def get(id:Int) = DBAction { implicit rs =>
    Players.tryFindById(id) match {
      case Success(player) => Ok(Json.toJson(player))
      case Failure(RowNotFoundException(_))  => NotFound(s"No Player found for $id")
      case Failure(exp) => BadRequest(s"Can't find player for $id: ${exp.getMessage}")
    }
  }

  def create = DBAction.transaction(parse.json) { implicit rs =>
    val playerResult = Json.fromJson[Player](rs.request.body)

    val tryCreate = Try(playerResult.get).flatMap { player =>
      player.trySave
    }

    tryCreate match {
      case Success(player) => Created(Json.toJson(player)).withHeaders("Location" -> s"/player/${player.id.get}")
      case Failure(exp) => BadRequest(s"Can't create player: ${exp.getMessage}")
    }

  }

  def update(id:Int) = DBAction.transaction(parse.json) { implicit rs =>
    val playerResult = Json.fromJson[Player](rs.request.body)

    val tryUpdate = Try(playerResult.get).flatMap { player =>
      player.tryUpdate
    }

    tryUpdate match {
      case Success(player) => Ok(Json.toJson(player))
      case Failure(RowNotFoundException(_))  => NotFound(s"No Player found for $id")
      case Failure(exp) => BadRequest(s"Can't update player: ${exp.getMessage}")
    }
  }

  def delete(id:Int) = DBAction.transaction { implicit rs =>
    // DELETE is idempotent, 
    // but we make sure that no exception is thrown 
    Players.tryFindById(id).flatMap { player =>
      player.tryDelete
    }

    NoContent
  }

}