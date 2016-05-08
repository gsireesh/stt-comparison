package com.gsireesh

import java.io.File

import com.gsireesh.DiffWrapper.getPrintableDiff
import com.gsireesh.SttServices.WatsonSttService

/**
 * @author gsireesh
 */
object App {

  def main(args : Array[String]) {
    val config = new Configuration("src/main/resources/config.properties")
    val expectedValues = ExpectedValueLoader("src/main/resources/expected_values.csv")
    val watsonStt = new WatsonSttService(config("username"), config("password"))

    for (key <- expectedValues.keys) {
      val audio = new File("src/main/resources/speech_files/" + key)
      //      val watsonResponses = watsonStt.transcribe(audio)
      val watsonResponses = List("the birch canoes and then on the smooth planes", "glue the sheet to the dock background", "it is easy to tell the death of a well", "these days that you can they because they were dish", "rice is often served and rumbles", "the Jews of London's next", "the box was thrown beside the park truck", "the hogs are fed chopped corn and garbage", "four hours of study work faced us", "a large size and stockings is hard to sell")
      val expectedResponses = expectedValues(key).map(x => x.toLowerCase.replaceAllLiterally("\"", ""))
      val comparedResults = (expectedResponses, watsonResponses).zipped map ((a, b) => getPrintableDiff(a, b))
      val output = new StringBuilder
      comparedResults.addString(output, "\n\n")
      println(output.toString())
    }
  }

}
