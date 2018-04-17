package com.srilabs.api

import com.srilabs.models._
import com.srilabs.orm._
import io.finch._
import io.finch.syntax._
import io.finch.circe._
import io.circe.generic.auto._
import com.twitter.util.{Await, Duration, Future, FuturePool}
import io.circe.Encoder

object Router {


  private def wrapInFuture[T](v: Seq[T]) = FuturePool.unboundedPool(Ok(v))


  val jobs: Endpoint[Seq[Job]] = get("jobs") {
      wrapInFuture[Job](getJobs())
  }

  val interviews: Endpoint[Seq[Interview]] = get("interviews") {
    wrapInFuture[Interview](getInterviews())
  }

  val candidates: Endpoint[Seq[Candidate]] = get("candidates") {
    wrapInFuture[Candidate](getCandidates())
  }

  val job: Endpoint[Job] = post("job" :: jsonBody[Job]) { c : Job =>
    FuturePool.unboundedPool {
      insertJob(c)
      Created(c)
    }
  }

  val candidate: Endpoint[Candidate] = post("candidate" :: jsonBody[Candidate]) { c : Candidate =>
      FuturePool.unboundedPool {
        insertCandidate(c)
        Created(c)
      }
  }

  val interview: Endpoint[Interview] = post("interview" :: jsonBody[Interview]) { c : Interview =>
    FuturePool.unboundedPool {
      insertInterview(c)
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