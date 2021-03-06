package com.bridgelabz.indiancensusanalser
import com.bridgelabz.indiancensusanalser.Country.Country
import java.nio.file.{Files, NoSuchFileException, Paths}
import java.util

import com.bridgelab.csvbuilder.{CSVBuilderException, CSVBuilderFactory}


class CensusLoader {
  /**
   *
   * @param country Enum Value
   * @param filePaths Take File Paths
   * @return Map key value pair type value
   */
  def loadData[A](country:Country, filePaths:Seq[String]): Map[String, CensusDAO] = {
    try {
      for (filePath <- filePaths) {
        if (!filePath.toLowerCase.endsWith(".csv")) {
          throw new CensusAnalyserException(CensusAnalyzerExceptionEnums.inCorrectFileType)
        }
      }
      var censusMap: Map[String, CensusDAO] = Map()
      val readerStateCensus = Files.newBufferedReader(Paths.get(filePaths(0)))
      val csvBuilderStateCensus = CSVBuilderFactory.createCSVBuilder()
      if (country.equals(Country.India)) {
        val censusCSVIteratorStateCensus: util.Iterator[IndiaCensusDTO] = csvBuilderStateCensus.getCSVFileIterator(readerStateCensus, classOf[IndiaCensusDTO])
        censusCSVIteratorStateCensus.forEachRemaining { obj => censusMap += (obj.state -> new CensusDAO(obj)) }
      }
      else if (country.equals(Country.USA)) {
        val UScensusCSVIterator: util.Iterator[USCensusDTO] = csvBuilderStateCensus.getCSVFileIterator(readerStateCensus, classOf[USCensusDTO])
        UScensusCSVIterator.forEachRemaining { obj => censusMap += (obj.state -> new CensusDAO(obj)) }
      }
      else {
        throw new CensusAnalyserException(CensusAnalyzerExceptionEnums.invalidCountry)
      }
      if (filePaths.length == 1) {
        return censusMap
      }
      loadStateCode(censusMap, filePaths(1): String)
    }
    catch {
      case _: NoSuchFileException => throw new CensusAnalyserException(CensusAnalyzerExceptionEnums.inCorrectFilePath)
      case _: CSVBuilderException => throw new CensusAnalyserException(CensusAnalyzerExceptionEnums.unableToParse)
      case _: CSVBuilderException => throw new CensusAnalyserException(CensusAnalyzerExceptionEnums.unableToParse)
    }
  }

  /**
   * Function Loads Data for StateCod
   * @return Map,key value pair type value
   */
  def loadStateCode(censusMap:Map[String,CensusDAO],filePath:String): Map[String,CensusDAO]={
    try {
      val readerStateCode = Files.newBufferedReader(Paths.get(filePath))
      val CSVBuilderStateCode = CSVBuilderFactory.createCSVBuilder()
      val censusCSVIteratorStateCode: util.Iterator[IndiaStateCodeDTO] = CSVBuilderStateCode.getCSVFileIterator(readerStateCode, classOf[IndiaStateCodeDTO])
      var censusStateMap: Map[String, CensusDAO] = Map()
      censusCSVIteratorStateCode.forEachRemaining { obj => censusStateMap += (obj.stateName -> new CensusDAO(obj)) }

      for (stateNameCensus <- censusMap.keys; stateName <- censusStateMap.keys; if (stateName.equals(stateNameCensus)) == true) {
        val censusData = censusMap(stateNameCensus)
        censusData.stateCode = censusStateMap(stateName).stateCode
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
