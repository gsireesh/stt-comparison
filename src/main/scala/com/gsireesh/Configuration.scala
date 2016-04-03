package com.gsireesh

import java.util.Properties
import java.io.FileInputStream

/**
  * Created by sireesh on 4/3/16.
  */
class Configuration(filePath: String) {
  val props = new Configuration("test")
  val inStream = new FileInputStream(filePath)
  val props.load()
}
