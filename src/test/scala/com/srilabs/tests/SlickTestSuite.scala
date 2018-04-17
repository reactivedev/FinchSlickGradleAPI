package com.srilabs.tests

import com.srilabs.models.Job
import org.scalatest._
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Seconds, Span}
import slick.driver.H2Driver.api._
import slick.jdbc.meta._
import com.srilabs.orm._



class SlickTestSuite extends FunSuite with BeforeAndAfter with ScalaFutures {

  implicit override val patienceConfig = PatienceConfig(timeout = Span(5, Seconds))

  val jobs = TableQuery[Jobs]
  val candidates = TableQuery[Candidates]
  val interviews = TableQuery[Interviews]
  
  var db: Database = _

  def createSchema() =
    db.run((jobs.schema ++ candidates.schema ++ interviews.schema).create).futureValue
  
  def insertJob(): Int =
    db.run(jobs += Job("Whitney Who", "Engineer", "Awesome Engineer Needed!", "foo bar", "scala, slick", System.currentTimeMillis(), 1)).futureValue
  
  before { db = Database.forConfig("h2mem1") }
  
  test("Creating the Schema works") {
    createSchema()
    
    val tables = db.run(MTable.getTables).futureValue

    assert(tables.size == 3)
    assert(tables.count(_.name.name.equalsIgnoreCase("candidates")) == 1)
    assert(tables.count(_.name.name.equalsIgnoreCase("jobs")) == 1)
    assert(tables.count(_.name.name.equalsIgnoreCase("interviews")) == 1)
  }

  test("Inserting a Supplier works") {
    createSchema()

    val insertCount = insertJob()
    assert(insertCount == 1)
  }

  test("Query Suppliers works") {
    createSchema()
    insertJob()
    val results = db.run(jobs.result).futureValue
    assert(results.size == 1)
  }
  
  after { db.close }
}
