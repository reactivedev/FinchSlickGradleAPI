package com.srilabs.orm


import slick.jdbc.H2Profile.api._
import com.srilabs.models.Job

class Jobs(tag: Tag) extends BaseTable[Job](tag, "JOBS") {

  def recruiter = column[String]("RECRUITER")
  def title = column[String]("TITLE")
  def subject = column[String]("SUBJECT")
  def description = column[String]("DESCRIPTION")
  def keywords = column[String]("KEYWORDS")
  def status = column[Int]("STATUS")

  def * = (recruiter, title, subject, description, keywords, status, createdAt, updatedAt, deletedAt, id) <> (Job.tupled, Job.unapply)

}
