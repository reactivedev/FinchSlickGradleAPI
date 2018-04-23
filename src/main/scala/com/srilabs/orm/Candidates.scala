package com.srilabs.orm

import com.srilabs.models.Candidate
import Profile.api._

class Candidates(tag: Tag) extends BaseTable[Candidate](tag, "CANDIDATES") {

  def email = column[String]("EMAIL")
  def name = column[String]("NAME")
  def dob = column[String]("DOB")
  def token = column[String]("TOKEN")

  def * = (email, name, dob, token, createdAt, updatedAt.?, id.?) <> (Candidate.tupled, Candidate.unapply)

}