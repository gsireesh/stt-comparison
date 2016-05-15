package com.gsireesh

import java.io.File

import scala.io.Source
import scala.util.Random

/**
  * Created by sireesh on 5/14/16.
  */
class SpeechSourceWashington(folder: String) extends SpeechSource {

  val speechList = pairAudioFilesWithTranscripts(folder, getTranscripts(folder))


  private def getTranscripts(folder: String): Map[String, String] = {
    val separator = if (folder.endsWith("/")) "" else "/"
    Source.fromFile(folder + separator + "PNNC-transcripts.txt").getLines().map(a => {
      val b = a.split("\t");
      (b(0), b(1).init.toLowerCase)
    }).toMap
  }

  private def pairAudioFilesWithTranscripts(folder: String, transcripts: Map[String, String]): Map[File, String] = {
    val separator = if (folder.endsWith("/")) "" else "/"
    val audioFolder = new File(folder + separator + "audio")
    if (!audioFolder.isDirectory) throw new Exception("Folder 'audio' does not exist!")
    audioFolder.listFiles.map(file => (file, transcripts.getOrElse(getSentenceNumber(file.getName), ""))).toMap
  }

  private def getSentenceNumber(filename: String): String = filename.split(Array('_', '.'))(1)

  override def getSpeechFiles(): List[(File, String)] = speechList.toList

  override def getExpectedTranscription(speechFile: File): String = speechList(speechFile)

  override def getSpeechFiles(num: Int): List[(File, String)] = Random.shuffle(speechList.toList).take(num)
}
