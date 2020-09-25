package com.bridgelabz.indiancensusanalser

import java.io.FileNotFoundException

import scala.io.Source

class CensusAnalyser {
  def loadIndiaCensusData(filePath: String): Int = {
    try {
      if(!filePath.endsWith(".csv")){
        throw new CensusAnalyserException(CensusAnalyzerExceptionEnums.inCorrectFileType)
      }
      val fileReader = Source.fromFile(filePath)
      var countRow = 0
      for (_ <- fileReader.getLines()) {
        countRow += 1
      }
      countRow - 1
    }
    catch {
      case ex: FileNotFoundException => throw new CensusAnalyserException(CensusAnalyzerExceptionEnums.inCorrectFilePath)
    }
  }
}
