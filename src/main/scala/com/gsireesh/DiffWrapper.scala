package com.gsireesh

import difflib.{DiffUtils, Patch}

import scala.collection.JavaConverters._
import scala.collection.mutable

/**
  * Created by sireesh on 5/8/16.
  */
object DiffWrapper {

  def getPrintableDiff(expected: String, actual: String): String = {
    val patch: Patch[String] = DiffUtils.diff(expected.split(' ').toSeq.asJava, actual.split(' ').toSeq.asJava)
    var oldIndex = 0
    val actualArray = actual.split(' ')
    val actualArrayColored = new mutable.MutableList[String]
    for (delta <- patch.getDeltas.asScala) {
      actualArray.slice(oldIndex, delta.getRevised.getPosition).foreach(a => actualArrayColored += (Console.GREEN + a))
      delta.getRevised.getLines.asScala.foreach(a => actualArrayColored += (Console.RED + a))
      oldIndex = delta.getRevised.getPosition + delta.getRevised.getLines.size
    }
    if (oldIndex != actualArray.length) {
      actualArray.drop(oldIndex).foreach(a => actualArrayColored += (Console.GREEN + a))
    }
    actualArrayColored += Console.RESET
    expected + "\n" + actualArrayColored.mkString(" ")
  }

}
