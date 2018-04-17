package com.srilabs.orm

import slick.jdbc.H2Profile.api._
import com.srilabs.models.Interview

class Interviews(tag: Tag) extends Table[Interview](tag, "INTERVIEWS") {

  def jobId = column[Int]("ID", O.PrimaryKey, O.AutoInc)
  def candidateId = column[Int]("EMAIL")
  def startTime = column[Long]("START_TIME")

  def * = (candidateId, startTime, jobId) <> (Interview.tupled, Interview.unapply)

}