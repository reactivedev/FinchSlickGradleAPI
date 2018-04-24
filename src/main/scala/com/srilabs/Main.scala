package com.srilabs

import com.twitter.finagle.param.Stats
import com.twitter.finagle.Http
import com.twitter.server.TwitterServer
import com.twitter.util.Await
import io.finch._
import io.finch.circe._
import io.circe.generic.auto._
import com.srilabs.api.Router._
import com.srilabs.config.Settings
import com.srilabs.orm.createSchema


object Main extends TwitterServer {

  def main(): Unit = {

    createSchema()

    val endpoints =   echo :+: candidates :+: jobs :+: interviews :+:
                      candidate :+: interview :+: job :+:
                      uCandidate :+: uInterview :+: uJob :+:
                      dCandidate :+: dInterview :+: dJob

    val port = "127.0.0.1:" + Settings.port

    val server = Http.server
      .withAdmissionControl.concurrencyLimit(maxConcurrentRequests = 5, maxWaiters = 5)
      .configured(Stats(statsReceiver))
      .serve(port, endpoints.toServiceAs[Application.Json])

    onExit {
      server.close()
    }

    Await.ready(server)
  }
}
