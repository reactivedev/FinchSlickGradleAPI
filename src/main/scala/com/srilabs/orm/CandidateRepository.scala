package com.srilabs.orm
import com.srilabs.models.Candidate
import slick.jdbc.H2Profile.api._
import com.twitter.util.{Future => TFuture}
import com.srilabs.util.FutureUtils._

object CandidateRepository extends Repository[Candidate, Candidates](TableQuery[Candidates]) {

// Abstracted into Repository
//  val candidates = TableQuery[Candidates]
//  def getCandidates(offset: Int = 0, batch: Int = 25): TFuture[Seq[Candidate]] =
//    this.all.asTwitter
//  def insertCandidate(candidate: Candidate): TFuture[Int] =
//    db.run(candidates += candidate).asTwitter

}
