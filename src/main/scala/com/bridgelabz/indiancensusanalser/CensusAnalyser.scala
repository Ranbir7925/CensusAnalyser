package com.bridgelabz.indiancensusanalser

import com.google.gson.Gson

class CensusAnalyser {
  var censusMap: Map[String, CensusDAO] = Map()
  var censusStateMap: Map[String, CensusDAO] = Map()

  def loadIndiaCensusData(filePath: String): Int = {
    censusMap = new CensusLoader().loadData(classOf[IndiaCensusCSV], filePath)
    censusMap.size
  }

  def loadIndiaStateCode(filePath: String): Int = {
    censusStateMap = new CensusLoader().loadData(classOf[IndiaStateCodeCSV], filePath)
    censusStateMap.size
  }

  def loadCensusUSData(filePath: String):Int={
    censusMap = new CensusLoader().loadData(classOf[USCensusDTO],filePath)
    censusMap.size
  }

  def sort(choice: Int): String = {
    if (censusMap == null || censusMap.size == 0) {
      throw new CensusAnalyserException(CensusAnalyzerExceptionEnums.noCensusData)
    }
    var censusCSVList = censusMap.values.toArray
    censusCSVList = choice match {
      case 1 => censusCSVList.sortBy(_.state)
      case 2 => censusCSVList.sortBy(_.stateCode)
      case 3 => censusCSVList.sortBy(_.population).reverse
      case 4 => censusCSVList.sortBy(_.populationDensity).reverse
      case 5 => censusCSVList.sortBy(_.totalArea).reverse
    }
    val sortedStateCensusCensus = new Gson().toJson(censusCSVList)
    sortedStateCensusCensus
  }

  def getStateWiseSortedCensusData: String = {
    sort(1)
  }

  def getStateCodeWiseSortedCensusData: String = {
    for (stateNameCensus <- censusMap.keys; stateName <- censusStateMap.keys; if (stateName.equals(stateNameCensus)) == true) {
      val censusData = censusMap(stateNameCensus)
      censusData.stateCode = censusStateMap(stateName).stateCode
    }
    sort(2)
  }

  def getPopulationWiseSortedCensusData: String = {
    sort(3)
  }

  def getPopulationDensityWiseSortedCensusData: String = {
    sort(4)
  }

  def getAreaWiseSortedCensusData: String = {
    sort(5)
  }
}
