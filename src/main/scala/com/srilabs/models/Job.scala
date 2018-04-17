package com.srilabs.models

import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}


case class Job(recruiter: String,
               title: String,
               subject: String,
               description: String,
               keywords: String,
               postedOn: Long = now,
               status: Int = 0,
               id: Int = 0 // Option[Int] = None
              )

object Job extends ((String, String, String, String, String, Long, Int, Int) => Job){

  implicit val decider: Decoder[Interview] = deriveDecoder[Interview]
  implicit val encoder: Encoder[Interview] = deriveEncoder[Interview]

  def create(recruiter: String, title: String, subject: String, description: String, keywords: String): Job =
      new Job(recruiter, title, subject, description, keywords)
}
