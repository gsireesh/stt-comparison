package com.gsireesh

import scala.io.Source

/**
  * Created by sireesh on 4/3/16.
  */
object ExpectedValueLoader {

  def apply(path :String) = transformToMap(Source.fromFile(path).getLines.map(processLine).toList)

  private def processLine(line: String):(String, List[String]) = {
    val lineList = line.split(",")
    (lineList(0), lineList.tail.toList)
  }

  private def transformToMap[T, U](listOfTuples : List[(T, U)]) = {
    listOfTuples.foldLeft(Map.empty[T, U])((map, tuple) => map + (tuple._1 -> tuple._2))
  }
}

