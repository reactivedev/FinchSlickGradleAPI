package com.srilabs.models

import java.time.LocalDateTime


trait Entity {
  def id: Long
  def createdAt: Long
  def updatedAt: Long
  def deletedAt: Long
}



//trait BaseEntity {
//  def id: Option[Long] = None
//  def createdAt: Option[LocalDateTime] = None
//  def updatedAt: Option[LocalDateTime] = None
//  def deletedAt: Option[LocalDateTime] = None
//}
