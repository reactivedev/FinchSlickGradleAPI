package com.srilabs.orm

import com.srilabs.models.Interview
import Profile.api._

class Interviews(tag: Tag) extends BaseTable[Interview](tag, "INTERVIEWS") {

  def candidateId = column[Long]("CANDIDATE_ID")
  def jobId = column[Long]("JOB_ID")
  def startTime = column[Long]("START_TIME")

  def * = (candidateId, jobId, startTime, createdAt, updatedAt.?, id.?) <> (Interview.tupled, Interview.unapply)

}