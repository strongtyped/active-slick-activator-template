package components

import play.api.db.slick.Config
import io.strongtyped.active.slick.ActiveSlick
import scala.slick.driver.JdbcDriver

class ActiveSlickCake(override val jdbcDriver: JdbcDriver) 
        extends ActiveSlick with Schema with PlayerExtensions {
  
  import jdbcDriver.simple._
  
  def createSchema(implicit sess: Session) = {
    (Players.ddl).create
  }

}

object ActiveSlickCake {
  val cake = new ActiveSlickCake(Config.driver)
}
