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

        if (countRow == 0){
          if(cols(0) != "State" || cols(1) != "Population" || cols(2) != "AreaInSqKm" || cols(3) != "DensityPerSqKm"){
            throw new CensusAnalyserException(CensusAnalyzerExceptionEnums.inCorrectHeaders)
          }
        }
        countRow += 1
      }
      countRow - 1
    }
    catch {
      case ex: FileNotFoundException => throw new CensusAnalyserException(CensusAnalyzerExceptionEnums.inCorrectFilePath)
    }
  }

  def loadIndiaStateCode(filePath:String):Int={
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

        if (countRow == 0){
          if(cols(1) != "State Name" || cols(2) != "TIN" || cols(3) != "StateCode"){
            throw new CensusAnalyserException(CensusAnalyzerExceptionEnums.inCorrectHeaders)
          }
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
