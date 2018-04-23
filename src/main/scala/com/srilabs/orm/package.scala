package com.srilabs

import config.Settings
import scala.concurrent.{Await, ExecutionContext}
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

  def createSchema(): Unit = {
    println("Creating Schema...")
    val schema = TableQuery[Candidates].schema ++ TableQuery[Jobs].schema ++ TableQuery[Interviews].schema
    println(schema.createStatements.foreach(println(_)))
    val f = db.run(schema.create)
    f.onComplete { x =>
      println("Done....")
    }
    Await.result(f, timeout)
  }

  private def getDB: Database = {
    val dbConfPath = "sri." + Settings.env + "." + Settings.db
    println(dbConfPath)
    Database.forConfig(dbConfPath)
  }

}
