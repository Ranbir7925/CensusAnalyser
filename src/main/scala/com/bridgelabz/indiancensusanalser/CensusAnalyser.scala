package com.bridgelabz.indiancensusanalser

import com.bridgelabz.indiancensusanalser.Country.Country
import com.google.gson.Gson

class CensusAnalyser() {
  var censusMap: Map[String, CensusDAO] = Map()

  def loadCensusData(country: Country, filepath: String*): Int = {
    censusMap = new CensusLoader().loadData(country, filepath)
    censusMap.size
  }

  /**
   * Sort function sorts the object wrt to certain values
   * @param choice to select between multiple value
   * @return sorted data
   */
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

  /**
   * Function calls sort function and tells to perform sorting wrt state wise census data
   * @return value of sorted data
   */
  def getStateWiseSortedCensusData: String = {
    sort(1)
  }

  /**
   *Function calls sort function and tells to perform sorting wrt State code
   * @return value of sorted data
   */
  def getStateCodeWiseSortedCensusData: String = {
    sort(2)
  }

  /**
   *Function calls sort function and tells to perform sorting wrt population
   * @return value of sorted data
   */
  def getPopulationWiseSortedCensusData: String = {
    sort(3)
  }

  /**
   *Function calls sort function and tells to perform sorting wrt population density
   * @return value of sorted data
   */
  def getPopulationDensityWiseSortedCensusData: String = {
    sort(4)
  }

  /**
   * Function calls sort function and tells to perform sorting wrt largest Area
   * @return value of sorted data
   */
  def getAreaWiseSortedCensusData: String = {
    sort(5)
  }
}
