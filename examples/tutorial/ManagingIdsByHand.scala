package tutorial

object ManagingIdsByHand {


  import scala.slick.driver.H2Driver.simple._

  val db = Database.forURL("jdbc:h2:mem:active-slick", driver = "org.h2.Driver")

  case class Foo(name: String, id: Option[Int] = None)

  class FooTable(tag: Tag) extends Table[Foo](tag, "FOOS") {
    def name = column[String]("NAME")

    def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)

    def * = (name, id.?) <>(Foo.tupled, Foo.unapply)
  }

  val Foos = TableQuery[FooTable]

  db.withTransaction { implicit sess =>
    Foos.ddl.create
    val foo = Foo("foo")
    // persist and fetch generated id
    val id = Foos.returning(Foos.map(_.id)).insert(foo)
    // a copy of foo with the generated id
    foo.copy(id = Some(id))
  }

}