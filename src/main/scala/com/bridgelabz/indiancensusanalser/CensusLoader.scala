package com.bridgelabz.indiancensusanalser

import java.nio.file.{Files, NoSuchFileException, Paths}
import java.util

class CensusLoader {
  def loadData[A](csvClass: Class[A], filePaths: String*): Map[String, IndiaStateCensusDAO] = {
    try {
      var censusMap: Map[String, IndiaStateCensusDAO] = Map()
      for (filePath <- filePaths) {
        if (!filePath.toLowerCase.endsWith(".csv")) {
          throw new CensusAnalyserException(CensusAnalyzerExceptionEnums.inCorrectFileType)
        }
        val reader = Files.newBufferedReader(Paths.get(filePath))
        val csvBuilder = CSVBuilderFactory.createCSVBuilder()
        if(csvClass.getName == "com.bridgelabz.indiancensusanalser.IndiaCensusCSV"){
          val censusCSVIterator: util.Iterator[IndiaCensusCSV] = csvBuilder.getCSVFileIterator(reader, classOf[IndiaCensusCSV])
          while (censusCSVIterator.hasNext){
            val objDAO = censusCSVIterator.next()
            censusMap += (objDAO.state -> new IndiaStateCensusDAO(objDAO))
          }
        }
        else if(csvClass.getName == "com.bridgelabz.indiancensusanalser.IndiaStateCodeCSV"){
          val censusCSVIterator: util.Iterator[IndiaStateCodeCSV] = csvBuilder.getCSVFileIterator(reader, classOf[IndiaStateCodeCSV])
          while (censusCSVIterator.hasNext){
            val objDAO = censusCSVIterator.next()
            censusMap += (objDAO.sateName -> new IndiaStateCensusDAO(objDAO))
          }
        }
      }
      censusMap
    }
    catch {
      case _: NoSuchFileException => throw new CensusAnalyserException(CensusAnalyzerExceptionEnums.inCorrectFilePath)
      case _: CSVBuilderException => throw new CensusAnalyserException(CensusAnalyzerExceptionEnums.unableToParse)
    }
  }
}
