package com.gsireesh

import com.gsireesh.SttServices.WatsonSttService

/**
 * @author gsireesh
 */
object App {

  val NumSentences = 2

  def main(args : Array[String]) {
    val config = new Configuration("src/main/resources/config.properties")
    val speechSource = new SpeechSourceWashington("src/main/resources/Washington_speech_files")
    val watsonStt = new WatsonSttService(config("username"), config("password"))

    for ((audio, expectedResponse) <- speechSource.getSpeechFiles().take(1)) {
      val watsonResponse = "keep the hatch tight on the watch constant"//watsonStt.transcribe(audio).map(_.toLowerCase).head
      val (wer,comparedResults) = DiffWrapper.getWerAndPrintableDiff(expectedResponse.toLowerCase, watsonResponse.toLowerCase)
      println(s"$comparedResults\nWord Error Rate: $wer")
    }
  }

}
