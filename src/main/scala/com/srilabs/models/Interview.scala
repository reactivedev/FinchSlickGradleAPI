package com.srilabs.models

import com.srilabs.util._

case class Interview(candidateId: Long,
                     jobId: Long,
                     startTime: Long,
                     createdAt: Long = now,
                     updatedAt: Option[Long] = None,
                     id: Option[Long] = None
                    ) extends Entity


