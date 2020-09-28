package com.bridgelabz.indiancensusanalser

class CensusAnalyserException(message: CensusAnalyzerExceptionEnums.Value) extends Exception(message.toString) {}

object CensusAnalyzerExceptionEnums extends Enumeration {
  type CensusAnalyserException = Value

  val inCorrectFilePath = Value("Incorrect File Path provided")
  val inCorrectFileType = Value("Incorrect File Type provided")
  val unableToParse = Value("Not able to Parse Invalid Delimiter or Fields")
  val noCensusData = Value("Not Data available")
}