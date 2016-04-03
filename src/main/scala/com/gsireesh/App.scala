package com.gsireesh

import com.ibm.watson.developer_cloud.speech_to_text.v1._
import java.io.File

import com.ibm.watson.developer_cloud.http.HttpMediaType

/**
 * @author gsireesh
 */
object App {

  def main(args : Array[String]) {
    val config = new Configuration("src/main/resources/config.properties")

    val sttService = new SpeechToText()
    sttService.setUsernameAndPassword(config("username"), config("password"))

    val audio = new File("src/main/resources/speech_files/OSR_us_000_0010_8k.wav")
    val options = new RecognizeOptions().model("en-US_NarrowbandModel").continuous(true).maxAlternatives(2)
    val transcript = sttService.recognize(audio, HttpMediaType.AUDIO_WAV, options)
    println(transcript)
  }

}
