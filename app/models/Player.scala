package models

import io.strongtyped.active.slick.models.Identifiable

case class Player(name: String, id: Option[Int] = None) 
    extends Identifiable[Player] {
  
  override type Id = Int
  override def withId(id: Id): Player = copy(id = Option(id))
}