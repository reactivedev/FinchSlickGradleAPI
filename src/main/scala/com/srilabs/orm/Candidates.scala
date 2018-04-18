package com.srilabs.orm

import slick.jdbc.H2Profile.api._
import com.srilabs.models.Candidate

class Candidates(tag: Tag) extends BaseTable[Candidate](tag, "USERS") {

  def email = column[String]("EMAIL")
  def name = column[String]("NAME")
  def dob = column[String]("DOB")
  def token = column[String]("TOKEN")

  def * = (email, name, dob, token, createdAt, updatedAt, deletedAt, id) <> (Candidate.tupled, Candidate.unapply)

}