package com.srilabs.models

import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto._

case class Interview(candidateId: Int, startTime: Long, jobId: Int = 0 ) //Option[Int] = None)

object Interview extends ((Int, Long, Int) => Interview)  {

  implicit val decider: Decoder[Interview] = deriveDecoder[Interview]
  implicit val encoder: Encoder[Interview] = deriveEncoder[Interview]

  def create(candidateId: Int, startTime: String): Interview = new Interview(candidateId, getMillis(startTime))

}



