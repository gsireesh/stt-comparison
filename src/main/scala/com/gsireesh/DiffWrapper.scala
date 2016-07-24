package com.gsireesh


import com.gsireesh.diffmatchpatch.DiffMatchPatch

import scala.collection.JavaConverters._
import com.gsireesh.diffmatchpatch.DiffMatchPatch.{Diff, Operation}
/**
  * Created by sireesh on 5/8/16.
  */
object DiffWrapper {

  def getPrintableDiff(expected :String, actual :String): String = {
    val diffs = getDiffsList(expected, actual)
    val printable = diffs.foldLeft(""){ (str :String, diff :Diff) => diff.operation match  {
      case Operation.DELETE => str + Console.RED + diff.text
      case Operation.EQUAL => str +  Console.RESET + diff.text
      case Operation.INSERT => str + Console.GREEN + diff.text
    }} + Console.RESET
    expected + "\n" + printable
  }

  def getWerAndPrintableDiff(expected :String, actual :String) : (Double, String) = {
    val diffs = getDiffsList(expected, actual)
    def foldFunction(aggr :((Int, Int, Int), String), diff :Diff) :((Int, Int, Int), String) = {
      val (d, c, i) = aggr._1
      val str = aggr._2
      val wordsInDiff = diff.text.split(' ').length
      diff.operation match  {
        case Operation.DELETE => ((d+wordsInDiff, c, i), str + Console.RED + diff.text)
        case Operation.EQUAL => ((d, c+wordsInDiff, i), str +  Console.RESET + diff.text)
        case Operation.INSERT => ((d, c, i+wordsInDiff), str + Console.GREEN + diff.text)
      }
    }

    val ((d,c,i), str) = diffs.foldLeft(((0,0,0), "")) {foldFunction}
    val s = calculateSubstitutions(diffs)
    //this formula because the true numbers of insertions and deletions are (i-s) and (d-s) respectively. The actual
    // formula is (s + d + i)/(s + d + c)
    val wer = (d + i - s).toFloat/(d + c)
    (wer, str)
  }


  def calculateSubstitutions(diffs :List[Diff]) :Int = {
    def getConsecutiveTuples (tuples :List[(Diff, Diff)], diffs :List[Diff]) : List[(Diff, Diff)] = {
      diffs match {
        case x::y::xs => getConsecutiveTuples((x, y)::tuples, y::xs)
        case x :: Nil => tuples
        case Nil => tuples
      }
    }
    val consecutiveTuples = getConsecutiveTuples(List(), diffs);
    consecutiveTuples.foldLeft(0){ (i, tuple) => i + (if (tuple._1.operation == Operation.DELETE && tuple._2.operation == Operation.INSERT) 1 else 0) }
  }

  def getDiffsList(expected :String, actual :String) :List[Diff] = {
    val dmp = new DiffMatchPatch
    val wordDiffStuff = dmp.diffLinesToChars(expected, actual)
    val lineText1 = wordDiffStuff.chars1
    val lineText2 = wordDiffStuff.chars2
    val lineArray = wordDiffStuff.lineArray
    val diffs = dmp.diffMain(lineText1.asInstanceOf[String], lineText2.asInstanceOf[String])
    dmp.diffCharsToLines(diffs, lineArray)
    diffs.asScala.toList
  }


}
