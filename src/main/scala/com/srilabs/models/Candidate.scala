package com.srilabs.models
import com.srilabs.util._

case class Candidate(email: String,
                     name: String,
                     dob: String,
                     token: String,
                     createdAt: Long = now,
                     updatedAt: Option[Long] = None,
                     id: Option[Long] = None
                    ) extends Entity
//
//object Candidate extends ((String, String, String, String, Long, Option[Long], Option[Long], Option[Long]) => Candidate) {
//
//
//  implicit val decoder: Decoder[Candidate] = deriveDecoder[Candidate]
//  implicit val encoder: Encoder[Candidate] = deriveEncoder[Candidate]
//
//
////  def create(email: String, name: String, dob: String): Candidate = {
////
////    val token = RandomStringUtils.random(15, true, true)
////    val candidate = new Candidate(email, name, dob, token)
////    // email token as password to candidate as a simple way authentication
////    // Federated Identity is checked based on token & date of birth (What I gave, What he knew)
////    candidate
////  }
//}



