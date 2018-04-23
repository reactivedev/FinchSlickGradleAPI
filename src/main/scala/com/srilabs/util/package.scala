package com.srilabs

package object util {

  def now: Long = System.currentTimeMillis()

  def getMillis(date: String): Long = date.toLong // TODO: validate date then translate to millis from Date

}
