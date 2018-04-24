package com.srilabs

import config.Settings
import slick.jdbc.meta.MTable

import scala.concurrent.{Await, ExecutionContext, Future}
import scala.concurrent.duration._

package object orm {

  val timeout = 10.seconds
  val Profile = Settings.db match {
    case "mysql" => slick.jdbc.MySQLProfile
    case _ => slick.jdbc.H2Profile
  }

  import Profile.api._
  implicit lazy val db: Database = getDB
  implicit lazy val ec = ExecutionContext.global


  private def create[T <: Table[_]](q: TableQuery[T]): Future[Unit] =
    db.run(q.schema.create)

  private def exists[T <: Table[_]](tables: Vector[MTable], q: TableQuery[T]): Boolean =
    tables.exists(t => t.name.name == q.baseTableRow.tableName)


  def createSchemaAsync(): Unit = {

    for {

      tables <- db.run(MTable.getTables)
      c <- create(TableQuery[Candidates]) if !exists(tables, TableQuery[Candidates])
      j <- create(TableQuery[Jobs]) if !exists(tables, TableQuery[Jobs])
      i <- create(TableQuery[Interviews]) if !exists(tables, TableQuery[Interviews])


    } yield(c, j, i)
  }


  def createSchema(): Unit = {

    val tables = Await.result(db.run(MTable.getTables), timeout)

    createTable(TableQuery[Candidates])
    createTable(TableQuery[Jobs])
    createTable(TableQuery[Interviews])

    def createTable[T <: Table[_]](q: TableQuery[T]): Unit = if(exists(tables, q)) {
      println(s"Table ${q.baseTableRow.tableName} exists")
    } else {
      println(s"Table ${q.baseTableRow.tableName} doesn't exists, will create it.")
      Await.result(create(q), timeout)
    }
  }

  private def getDB: Database = {
    val dbConfPath = "sri." + Settings.env + "." + Settings.db
    println(dbConfPath)
    Database.forConfig(dbConfPath)
  }

}
