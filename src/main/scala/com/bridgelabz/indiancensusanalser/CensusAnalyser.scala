package com.bridgelabz.indiancensusanalser

import java.nio.file.{Files, NoSuchFileException, Paths}
import java.util.Comparator
import java.util

import com.google.gson.Gson


class CensusAnalyser {

  var censusCSVList:util.List[IndiaCensusCSV] = null

  def sort(censusComparator:Comparator[IndiaCensusCSV])={
    val size = censusCSVList.size
    for (counter <- 0 until size-1){
      for (secondCounter <-  0 until size - counter -1 ){
        val census1 = censusCSVList.get(secondCounter)
        val census2 = censusCSVList.get(secondCounter+1)
        if (censusComparator.compare(census1,census2)>0){
          censusCSVList.set(secondCounter,census2)
          censusCSVList.set(secondCounter+1,census1)
        }
      }
    }
  }

  def getStateWiseSortedStatesData()={
    if(censusCSVList == null || censusCSVList.size() == 0){
      throw new CensusAnalyserException(CensusAnalyzerExceptionEnums.noCensusData)
    }
    val censusComparator = new Comparator[IndiaCensusCSV] {
      override def compare(firstObject: IndiaCensusCSV, secondObject: IndiaCensusCSV): Int = {
        firstObject.state.compareTo(secondObject.state)
      }
    }
    sort(censusComparator)
    val sortedStateCensusCensus = new Gson().toJson(censusCSVList)
    sortedStateCensusCensus
  }

  def loadIndiaCensusData(filePath: String): Int = {
    try {
      if (!filePath.toLowerCase.endsWith(".csv")) {
        throw new CensusAnalyserException(CensusAnalyzerExceptionEnums.inCorrectFileType)
      }
      val reader = Files.newBufferedReader(Paths.get(filePath))
      val csvBuilder = CSVBuilderFactory.createCSVBuilder()
      censusCSVList = csvBuilder.getCSVFileList(reader, classOf[IndiaCensusCSV])
      censusCSVList.size()
    }
    catch {
      case _: NoSuchFileException => throw new CensusAnalyserException(CensusAnalyzerExceptionEnums.inCorrectFilePath)
      case _: CSVBuilderException => throw new CensusAnalyserException(CensusAnalyzerExceptionEnums.unableToParse)
    }
  }

  def loadIndiaStateCode(filePath: String): Int = {
    try {
      if (!filePath.toLowerCase.endsWith(".csv")) {
        throw new CensusAnalyserException(CensusAnalyzerExceptionEnums.inCorrectFileType)
      }
      val reader = Files.newBufferedReader(Paths.get(filePath))
      val csvBuilder = CSVBuilderFactory.createCSVBuilder()
      val censusCSVList = csvBuilder.getCSVFileList(reader, classOf[IndiaStateCodeCSV])
      censusCSVList.size()
    } catch {
      case _: NoSuchFileException => throw new CensusAnalyserException(CensusAnalyzerExceptionEnums.inCorrectFilePath)
      case _: CSVBuilderException => throw new CensusAnalyserException(CensusAnalyzerExceptionEnums.unableToParse)
    }
  }
}
