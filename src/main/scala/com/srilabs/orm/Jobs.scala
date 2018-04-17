package com.srilabs.orm


import slick.jdbc.H2Profile.api._
import com.srilabs.models.Job

class Jobs(tag: Tag) extends Table[Job](tag, "JOBS") {

  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)
  def recruiter = column[String]("RECRUITER")
  def title = column[String]("TITLE")
  def subject = column[String]("SUBJECT")
  def description = column[String]("DESCRIPTION")
  def keywords = column[String]("KEYWORDS")
  def postedOn = column[Long]("POSTED_ON")
  def status = column[Int]("STATUS")

  def * = (recruiter, title, subject, description, keywords, postedOn, status, id) <> (Job.tupled, Job.unapply)

}
