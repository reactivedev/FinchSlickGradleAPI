package com.srilabs.orm

import slick.jdbc.H2Profile.api._
import com.srilabs.models.Candidate

class Candidates(tag: Tag) extends Table[Candidate](tag, "CANDIDATES") {

  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)
  def email = column[String]("EMAIL")
  def name = column[String]("NAME")
  def dob = column[String]("DOB")
  def createTime = column[Long]("CREATED_AT")
  def updateTime = column[Long]("UPDATED_ON")
  def token = column[String]("TOKEN")

  def * = (email, name, dob, token, createTime, updateTime, id) <> (Candidate.tupled, Candidate.unapply)

}