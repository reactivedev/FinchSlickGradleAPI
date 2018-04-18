package com.srilabs.models

import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto._

case class Interview(candidateId: Long,
                     jobId: Long,
                     startTime: Long,
                     createdAt: Long,
                     updatedAt: Long,
                     deletedAt: Long,
                     id: Long
                    ) extends Entity

//object Interview extends ((Int, Long, Int) => Interview)  {
//
//  implicit val decider: Decoder[Interview] = deriveDecoder[Interview]
//  implicit val encoder: Encoder[Interview] = deriveEncoder[Interview]
//
//  def create(candidateId: Int, startTime: String): Interview = new Interview(candidateId, getMillis(startTime))
//
//}



