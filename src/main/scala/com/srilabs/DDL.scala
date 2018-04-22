package com.srilabs

import scala.util.{Failure, Try}

object DDL extends App {

  Try(orm.createSchema()) match{
    case Failure(e) => println("One or more tables exists..." + e.getMessage)
    case _ => println("created schema")
  }
}
