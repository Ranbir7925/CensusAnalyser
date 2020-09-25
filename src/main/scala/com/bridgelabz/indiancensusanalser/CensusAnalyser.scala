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
      for (line <- fileReader.getLines()) {
        val cols = line.split(",").map(_.trim)
        if (cols.length != 4){
          throw new CensusAnalyserException(CensusAnalyzerExceptionEnums.inCorrectDelimiter)
        }
        countRow += 1
      }
      countRow - 1
    }
    catch {
      case ex: FileNotFoundException => throw new CensusAnalyserException(CensusAnalyzerExceptionEnums.inCorrectFilePath)
    }
  }
}
