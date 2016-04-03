package com.gsireesh

import java.util.Properties
import java.io.FileInputStream

/**
  * Created by sireesh on 4/3/16.
  */
class Configuration(filePath: String) {
  val props = new Properties
  props.load(new FileInputStream(filePath))

  def apply(propertyName: String) = {
    props.getProperty(propertyName, "")
  }
}
