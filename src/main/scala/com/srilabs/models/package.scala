package com.srilabs




package object models {


  case class Email(id: String)

  case class Name(first: String, last: String)

  case class Employee(id: Long, name: Name)

  //TODO: Figureout Compound Column Mapping so we could use Custom DSL i.e. Name, Email, Employee etc.
  // case class Candidate(id: Long, email: Email, name: Name, createTime: Long, updateTime: Long, token: String)
  // case class Job(id: Long, recruiter: Employee, title: String, subject: String, description: String, keywords: String, postedOn: Long, status: Int)

  // case class Interview(jobId: Long, candidateId: Long, startTime: Long )

  // Including Notes here triggers update .. how about denormalizing to its own table? scheduled interview is different from its occurrence
  // This is a case for POST scenario
  // case class InterviewSlot(interviewErId: Long, employeeId: Long, jobId: Long, candidateId: Long, scheduledTime: Long, duration: Long, notes: String)
  // case class InterviewerNotes(interviewerId: Long, notes: String)


  private[models] def now = System.currentTimeMillis()

  private[models] def getMillis(date: String) = date.toLong // TODO: validate date then translate to millis from Date




}
