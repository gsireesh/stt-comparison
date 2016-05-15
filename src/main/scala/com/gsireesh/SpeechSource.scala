package com.gsireesh

import java.io.File

/**
  * Created by sireesh on 5/7/16.
  */
trait SpeechSource {

  /**
    * Gets a list of all the speech files from the source.
    *
    * @return A list of tuples of the speech files and their expected transcription
    */
  //noinspection AccessorLikeMethodIsEmptyParen
  def getSpeechFiles(): List[(File, String)]

  /**
    * Gets a random list of speech files from the source
    *
    * @param num the number of speech files to get
    * @return A list of tuples of the speech files and their expected transcription
    */
  def getSpeechFiles(num: Int): List[(File, String)]

  /**
    * Gets the associated transcription for a given speech file
    *
    * @param speechFile the speech file for which to get the transcription
    * @return the expected transcription
    */
  def getExpectedTranscription(speechFile: File): String

}
