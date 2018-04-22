package com.srilabs

import config.Settings
import scala.concurrent.{Await, ExecutionContext}
import scala.concurrent.duration._

package object orm {

  private val profileToUse = Settings.profileToUse

  // This limits DB support to mysql & H2
  // TODO: Generalize so to use slick.SqlProfile dynamically instantiated based on config
  val Profile = profileToUse match {
    case "mysql" => slick.jdbc.MySQLProfile
    case _ => slick.jdbc.H2Profile
  }

  import Profile.api._

  implicit val db: Database = Database.forConfig("sri." + profileToUse)
  implicit val ec = ExecutionContext.global
  val timeout = 10.seconds

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
}
