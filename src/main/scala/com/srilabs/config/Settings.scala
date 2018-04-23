package com.srilabs.config

import com.typesafe.config.ConfigFactory

import scala.util.Try

object Settings {

  val config = ConfigFactory.load()
  val settings = config.getConfig("sri")
  val env = Try(settings.getString("env")).getOrElse("dev")
  val db = Try(settings.getString("db")).getOrElse("h2")
  val port = Try(settings.getString(env + ".port")).getOrElse("8085")

}
