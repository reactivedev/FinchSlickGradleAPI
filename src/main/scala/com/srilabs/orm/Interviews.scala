package com.srilabs.orm

import slick.jdbc.H2Profile.api._
import com.srilabs.models.Interview

class Interviews(tag: Tag) extends BaseTable[Interview](tag, "INTERVIEWS") {

  def candidateId = column[Long]("CANDIDATE_ID")
  def jobId = column[Long]("JOB_ID")
  def startTime = column[Long]("START_TIME")


  def * = (candidateId, jobId, startTime, createdAt, updatedAt, deletedAt, id) <> (Interview.tupled, Interview.unapply)

}