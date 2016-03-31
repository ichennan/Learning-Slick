import scala.slick.driver.MySQLDriver.simple._
import scala.slick.jdbc.meta.MTable

/**
  * Created by Terry on 2016/3/31.
  */
object Main extends App {
  val table_city = TableQuery[CityTable]
  val db = Database.forURL("jdbc:mysql://localhost:3306/testDB", "root", "password")
  db.withSession { implicit session =>
    //if not exit, create
    if (MTable.getTables(table_city.baseTableRow.tableName).list.isEmpty) table_city.ddl.create
    table_city += City("Guangzhou", 888888)
    println(table_city.list)
  }
}

case class City(name: String, zipp: Int)

class CityTable(tag: Tag) extends Table[City](tag, "terrytable2") {
  def name = column[String]("city")

  def zip = column[Int]("ZIP")

  def * = (name, zip) <>(City.tupled, City.unapply)
}