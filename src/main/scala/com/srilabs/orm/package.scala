package com.srilabs

import com.srilabs.models._
import slick.jdbc.H2Profile.api._
import com.twitter.util.{Return, Throw, Future => TFuture, Promise => TPromise}
import scala.concurrent.{Await, ExecutionContext, Future => SFuture, Promise => SPromise}
import com.srilabs.util.FutureUtils._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._


package object orm {

  val db: Database = Database.forConfig("h2mem1")
  val timeout = 10.seconds
  val jobs = TableQuery[Jobs]
  val candidates = TableQuery[Candidates]
  val interviews = TableQuery[Interviews]

  import scala.concurrent.ExecutionContext.Implicits.global

  def createSchema(): Unit = {
    Await.result(db.run((jobs.schema ++ candidates.schema ++ interviews.schema).create), timeout)
  }

  def insertJob(job: Job): TFuture[Int] = db.run(jobs += job).asTwitter

  def insertCandidate(candidate: Candidate): TFuture[Int] = {
    db.run(candidates += candidate).asTwitter
  }

  def insertInterview(interview: Interview): TFuture[Int] = db.run(interviews += interview).asTwitter

//  TODO: Why can't it be this simple? Some circe encoder is missing somewhere
//  def getJobs(offset: Int = 0, batch: Int = 25): TFuture[Seq[Job]] = {
//    db.run(jobs.drop(offset).take(batch).result).asTwitter
//  }

  // Implicit conversion is not being forced at the collection level, force on each element
    def getJobs(offset: Int = 0, batch: Int = 25): Seq[Job] = {
      var res: Seq[Job] = Vector()
      Await.result(db.run(jobs.result).map { s => res = s }, timeout)
      res
    }


  def getCandidates(offset: Int = 0, batch: Int = 25): Seq[Candidate] = {

    var res: Seq[Candidate] = Vector()
    Await.result(db.run(candidates.result).map { s => res = s }, timeout)
    res
    }

  def getInterviews(offset: Int = 0, batch: Int = 25): Seq[Interview] = {

    var res: Seq[Interview] = Vector()
    Await.result(db.run(interviews.result).map { s => res = s }, timeout)
    res
  }


//  def getInterviews(offset: Int = 0, batch: Int = 25): TFuture[Seq[Interview]] =
//    db.run(interviews.drop(offset).take(batch).result).asTwitter


//  def get[T, R](a: FixedSqlStreamingAction[Seq[T], T, Effect], offset: Int = 0, batch: Int = 25): Seq[T] =
//  {
//
//    var res: Seq[T] = Vector()
//
//    val f: SFuture[Seq[T]] = db.run(a)
//    val ff = f.map { s => res = s }
//
//    Await.result(ff, timeout)
//    res
//  }
//
//  def getCandidates2(offset: Int = 0, batch: Int = 25): TFuture[Seq[Candidate]] =
//    db.run(candidates.drop(offset).take(batch).result).asTwitter
//
//  def getCandidatesScala(offset: Int = 0, batch: Int = 25): SFuture[Seq[Candidate]] =
//    db.run(candidates.drop(offset).take(batch).result)


}
