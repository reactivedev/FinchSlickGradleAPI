package com.srilabs.api

import com.srilabs.models._
import com.srilabs.orm._
import io.finch._
import io.finch.syntax._
import io.finch.circe._
import io.circe.generic.auto._
import com.twitter.util.{Await, Future, FuturePool}
import slick.jdbc.H2Profile.api._

object Router {

  val jobsRepo = new Repository[Job, Jobs](TableQuery[Jobs]) {}
  val interviewRepo = new Repository[Interview, Interviews](TableQuery[Interviews]) {}

  // private def wrapInFuture[T](v: Seq[T]) = FuturePool.unboundedPool(Ok(v))

  def logger[T](f: Future[T]): Future[T] = f.onFailure(e => e.printStackTrace())


  val jobs: Endpoint[Seq[Job]] = get("jobs") {
      Ok(Await.result(logger(jobsRepo.all())))
  }

  val interviews: Endpoint[Seq[Interview]] = get("interviews") {
    Ok(Await.result(logger(interviewRepo.all())))
  }

  val candidates: Endpoint[Seq[Candidate]] = get("candidates") {
    Ok(Await.result(logger(CandidateRepository.all())))
  }

  val job: Endpoint[Job] = post("job" :: jsonBody[Job]) { c : Job =>
    FuturePool.unboundedPool {
      logger(jobsRepo.insert(c))
      Created(c)
    }
  }

  val candidate: Endpoint[Candidate] = post("candidate" :: jsonBody[Candidate]) { c : Candidate =>
      FuturePool.unboundedPool {
        logger(CandidateRepository.insert(c))
        Created(c)
      }
  }

  val interview: Endpoint[Interview] = post("interview" :: jsonBody[Interview]) { c : Interview =>
    FuturePool.unboundedPool {
      logger(interviewRepo.insert(c))
      Created(c)
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