package com.gsireesh

import java.io.File

import com.gsireesh.SttServices.WatsonSttService

/**
 * @author gsireesh
 */
object App {

  def main(args : Array[String]) {
    val config = new Configuration("src/main/resources/config.properties")
    val expectedValues = ExpectedValueLoader("src/main/resources/expected_values.csv")
    val watsonStt = new WatsonSttService(config("username"), config("password"))

    val audio = new File("src/main/resources/speech_files/OSR_us_000_0010_8k.wav")
    println(watsonStt.transcribe(audio))
  }

}
