package com.srilabs


import slick.jdbc.H2Profile.api._
import scala.concurrent.{Await, ExecutionContext}
import scala.concurrent.duration._

package object orm {

  implicit val db: Database = Database.forConfig("h2mem1")
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
