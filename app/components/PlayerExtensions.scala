package components

import io.strongtyped.active.slick.ActiveSlick
import scala.slick.jdbc.JdbcBackend
import scala.util.Try
import models.Player

trait PlayerExtensions { this: ActiveSlick with Schema =>

  import jdbcDriver.simple._

  val Players = EntityTableQuery[Player, PlayersTable](tag => new PlayersTable(tag))

  implicit class PlayersExtensions(val model: Player) extends ActiveRecord[Player] {
    
    override def table = Players
  }

}
