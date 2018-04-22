package com.srilabs.config

import com.typesafe.config.ConfigFactory

import scala.util.Try

object Settings {


  val config = ConfigFactory.load()
  private val settings = config.getConfig("sri")
  lazy val profileToUse = Try(settings.getString("dbProfileToUse")).getOrElse("h2mem1")



}
