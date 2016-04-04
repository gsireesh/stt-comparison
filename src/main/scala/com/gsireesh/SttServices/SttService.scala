package com.gsireesh.SttServices

import java.io.File

/**
  * Created by sireesh on 4/3/16.
  */
trait SttService {

  def transcribe(audio: File):List[String]
}
