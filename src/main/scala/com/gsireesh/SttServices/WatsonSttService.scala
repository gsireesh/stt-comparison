package com.gsireesh.SttServices

import java.io.File

import scala.collection.JavaConversions._

import com.ibm.watson.developer_cloud.speech_to_text.v1.model.{SpeechResults, Transcript}
import com.ibm.watson.developer_cloud.speech_to_text.v1.{RecognizeOptions, SpeechToText}


/**
  * Created by sireesh on 4/3/16.
  */
class WatsonSttService(username: String, password: String) extends SttService {
  val sttService = new SpeechToText()
  sttService.setUsernameAndPassword(username, password)

  override def transcribe(audio: File): List[String] = {
    val options = new RecognizeOptions().model("en-US_NarrowbandModel").continuous(true).maxAlternatives(2)
    val response = sttService.recognize(audio, options)
    transformResults(response)
  }

  private def transformResults(results: SpeechResults) :List[String] = {
    results.getResults.map(x => x.getAlternatives.get(0).getTranscript).toList
  }

}
