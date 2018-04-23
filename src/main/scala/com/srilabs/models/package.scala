package com.srilabs




package object models {


  case class Email(id: String)

  case class Name(first: String, last: String)

  case class Employee(id: Long, name: Name)


}
