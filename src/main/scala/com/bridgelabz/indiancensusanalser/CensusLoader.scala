package com.bridgelabz.indiancensusanalser

import java.nio.file.{Files, NoSuchFileException, Paths}
import java.util

class CensusLoader {
  def loadData[A](csvClass: Class[A], filePaths: String*): Map[String, CensusDAO] = {
    try {
      var censusMap: Map[String, CensusDAO] = Map()
      for (filePath <- filePaths) {
        if (!filePath.toLowerCase.endsWith(".csv")) {
          throw new CensusAnalyserException(CensusAnalyzerExceptionEnums.inCorrectFileType)
        }
        val reader = Files.newBufferedReader(Paths.get(filePath))
        val csvBuilder = CSVBuilderFactory.createCSVBuilder()
        if (csvClass.getName == "com.bridgelabz.indiancensusanalser.IndiaCensusCSV") {
          val censusCSVIterator: util.Iterator[IndiaCensusCSV] = csvBuilder.getCSVFileIterator(reader, classOf[IndiaCensusCSV])
          censusCSVIterator.forEachRemaining { objDAO => censusMap += (objDAO.state -> new CensusDAO(objDAO)) }
        }
        else if (csvClass.getName == "com.bridgelabz.indiancensusanalser.IndiaStateCodeCSV") {
          val censusCSVIterator: util.Iterator[IndiaStateCodeCSV] = csvBuilder.getCSVFileIterator(reader, classOf[IndiaStateCodeCSV])
          censusCSVIterator.forEachRemaining { objDAO => censusMap += (objDAO.sateName -> new CensusDAO(objDAO)) }
        }
        else if (csvClass.getName == "com.bridgelabz.indiancensusanalser.USCensusDTO"){
          val censusCSVIterator: util.Iterator[USCensusDTO] = csvBuilder.getCSVFileIterator(reader,classOf[USCensusDTO])
          censusCSVIterator.forEachRemaining { objDAO => censusMap += (objDAO.state -> new CensusDAO(objDAO))}
        }
      }
      censusMap
    }
    catch {
      case _: NoSuchFileException => throw new CensusAnalyserException(CensusAnalyzerExceptionEnums.inCorrectFilePath)
      case _: CSVBuilderException => throw new CensusAnalyserException(CensusAnalyzerExceptionEnums.unableToParse)
      case _: CSVBuilderException => throw new CensusAnalyserException(CensusAnalyzerExceptionEnums.unableToParse)
    }
  }
}
