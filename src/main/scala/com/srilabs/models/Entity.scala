package com.srilabs.models

import java.time.LocalDateTime

trait Entity {
  def createdAt: Long
  def updatedAt: Option[Long]
  def id: Option[Long]
}

