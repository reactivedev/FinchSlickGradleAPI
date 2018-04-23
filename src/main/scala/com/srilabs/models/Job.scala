package com.srilabs.models

import com.srilabs.util._

case class Job(recruiter: String,
               title: String,
               subject: String,
               description: String,
               keywords: String,
               status: Int = 0,
               createdAt: Long = now,
               updatedAt: Option[Long] = None,
               id: Option[Long] = None
              ) extends Entity

