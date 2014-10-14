import play.api._
import play.api.Play.current
import components.ActiveSlickCake.cake._
import models.Player

object Global extends GlobalSettings {

  override def onStart(app: Application): Unit = {
    super.onStart(app)
    play.api.db.slick.DB.withTransaction { implicit session =>
      // adding some players
      val players = Player("PlayerOne") :: Player("PlayerTwo") :: Player("PlayerThree") :: Nil
      players.foreach(_.save)
    }
  }
}