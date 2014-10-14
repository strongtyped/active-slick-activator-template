package components

import io.strongtyped.active.slick.{ TableQueries, Tables, Profile }
import models.Player


trait Schema { this: Tables with TableQueries with Profile =>

  import jdbcDriver.simple._

  class PlayersTable(tag: Tag) extends EntityTable[Player](tag, "PLAYER") {

    def name = column[String]("PLAYER_NAME")
    def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)

    def * = (name, id.?) <> (Player.tupled, Player.unapply)
  }

}