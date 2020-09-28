package com.bridgelabz.indiancensusanalser

class CSVBuilderException(message: CSVBuilderExceptionEnums.Value) extends Exception(message.toString) {}

object CSVBuilderExceptionEnums extends Enumeration {
  type CSVBuilderException = Value
  val inCorrectFilePath = Value("Incorrect File Path provided")
  val inCorrectFileType = Value("Incorrect File Type provided")
  val unableToParse = Value("Not able to Parse Invalid Delimiter or Fields")
}