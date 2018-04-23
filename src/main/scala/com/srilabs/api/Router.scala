package com.srilabs.api

import com.srilabs.models._
import com.srilabs.orm._
import io.finch._
import io.finch.syntax._
import io.finch.circe._
import io.circe.generic.auto._
import com.twitter.util.{Await, Future, FuturePool}
import slick.jdbc.H2Profile.api._
import com.srilabs.util._

object Router {

  private val jobsRepo = new Repository[Job, Jobs](TableQuery[Jobs]) {}
  private val interviewRepo = new Repository[Interview, Interviews](TableQuery[Interviews]) {}
  private val candidateRepo = new Repository[Candidate, Candidates](TableQuery[Candidates]) {}


  // private def wrapInFuture[T](v: Seq[T]) = FuturePool.unboundedPool(Ok(v))

  def handle[T](f: Future[T]): T = {
     val res = f.onFailure(e => {
                  e.printStackTrace()
                  BadRequest(new Exception(e.getMessage))
                })
               .onSuccess(res => {
                  println(res)
                  Ok(res)
                })
    Await.result[T](res)
  }


  val echo: Endpoint[String] = get("echo") {
    println("echo")
    Ok("echo")
  }

  val jobs: Endpoint[Seq[Job]] = get("jobs") {
      Ok(Await.result(jobsRepo.all()))
  }

  val interviews: Endpoint[Seq[Interview]] = get("interviews") {
    Ok(Await.result(interviewRepo.all()))
  }

  val candidates: Endpoint[Seq[Candidate]] = get("candidates") {
    Ok(Await.result(candidateRepo.all()))
  }


  val job: Endpoint[Option[Long]] = post("job" :: jsonBody[Job]) { c : Job =>
    FuturePool.unboundedPool {
      Ok(handle(jobsRepo.upsert(c)))
    }
  }

  val uJob: Endpoint[Option[Long]] = put("job" :: jsonBody[Job]) { c : Job =>
    FuturePool.unboundedPool {
      val updated = c.copy(updatedAt = Some(now))
      Ok(handle(jobsRepo.upsert(updated)))
    }
  }

  val dJob: Endpoint[Int] = delete("job" :: jsonBody[Long]) { c : Long =>
    FuturePool.unboundedPool {
      Ok(handle[Int](jobsRepo.delete(c)))
    }
  }

  val candidate: Endpoint[Option[Long]] = post("candidate" :: jsonBody[Candidate]) { c : Candidate =>
    FuturePool.unboundedPool {
      Ok(handle(candidateRepo.upsert(c)))
    }
  }

  val uCandidate: Endpoint[Option[Long]] = put("candidate" :: jsonBody[Candidate]) { c : Candidate =>
     FuturePool.unboundedPool {
       val updated = c.copy(updatedAt = Some(now))
       Ok(handle(candidateRepo.upsert(updated)))
    }
  }

  val dCandidate: Endpoint[Int] = delete("candidate" :: jsonBody[Long]) { c : Long =>
    FuturePool.unboundedPool {
      Ok(handle[Int](candidateRepo.delete(c)))
    }
  }

  val interview: Endpoint[Option[Long]] = post("interview" :: jsonBody[Interview]) { c : Interview =>
    FuturePool.unboundedPool {
      Ok(handle(interviewRepo.upsert(c)))
    }
  }

  val uInterview: Endpoint[Option[Long]] = post("interview" :: jsonBody[Interview]) { c : Interview =>
    FuturePool.unboundedPool {
      val updated = c.copy(updatedAt = Some(now))
      Ok(handle(interviewRepo.upsert(updated)))
    }
  }

  val dInterview: Endpoint[Int] = delete("interview" :: jsonBody[Long]) { c : Long =>
    FuturePool.unboundedPool {
      Ok(handle[Int](interviewRepo.delete(c)))
    }
  }
}


///candidate – represents an interview candidate
//POST – Create a new candidate
//PUT – update existing candidate
//DELETE – remove candidate
//GET – retrieve candidate
///job – represents an available job
//POST – create new job
//PUT – update job
//DELETE – remove job posting
//GET – retrieve candidate
///interview – represents a specific time a candidate is scheduled for an interview
//POST – create new interview, should specify candidate and job it is part of and time
//PUT – update interview
//DELETE – remove interview
//GET – retrieve interview information