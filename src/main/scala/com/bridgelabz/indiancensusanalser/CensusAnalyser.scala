package com.bridgelabz.indiancensusanalser
import java.util
import java.util.Comparator
import com.google.gson.Gson


class CensusAnalyser {
  var censusMap: Map[String, IndiaStateCensusDAO] = Map()
  var censusStateMap: Map[String, IndiaStateCensusDAO] = Map()


  def loadIndiaCensusData(filePath: String): Int = {
    censusMap = new CensusLoader().loadData(classOf[IndiaCensusCSV], filePath)
    censusMap.size
  }

  def loadIndiaStateCode(filePath: String): Int = {
    censusStateMap = new CensusLoader().loadData(classOf[IndiaStateCodeCSV], filePath)
    censusStateMap.size
  }


  def sort(censusComparator: Comparator[IndiaStateCensusDAO]): String = {
    if (censusMap == null || censusMap.size == 0) {
      throw new CensusAnalyserException(CensusAnalyzerExceptionEnums.noCensusData)
    }
    val size = censusMap.size
    val censusCSVList = censusMap.values.toArray
    for (counter <- 0 until size - 1) {
      for (secondCounter <- 0 until size - counter - 1) {
        val census1 = censusCSVList(secondCounter)
        val census2 = censusCSVList(secondCounter + 1)
        if (censusComparator.compare(census1, census2) > 0) {
          censusCSVList(secondCounter) = census2
          censusCSVList(secondCounter + 1) = census1
        }
      }
    }
    val sortedStateCensusCensus = new Gson().toJson(censusCSVList)
    sortedStateCensusCensus
  }

  def getStateCodeWiseSortedCensusData():String={
    for (stateNameCensus <- censusMap.keys;stateName <- censusStateMap.keys;if (stateName.equals(stateNameCensus))==true){
      val censusData = censusMap(stateNameCensus)
      censusData.stateCode = censusStateMap(stateName).stateCode
    }
      val censusComparator = new Comparator[IndiaStateCensusDAO] {
        override def compare(o1: IndiaStateCensusDAO, o2: IndiaStateCensusDAO): Int = {
          o1.stateCode.compareTo(o2.stateCode)
        }
      }
    sort(censusComparator)
  }

  def getStateWiseSortedCensusData(): String = {
    val censusComparator = new Comparator[IndiaStateCensusDAO] {
      override def compare(o1: IndiaStateCensusDAO, o2: IndiaStateCensusDAO): Int = {
        o1.state.compareTo(o2.state)
      }
    }
    sort(censusComparator)
  }

  def getCountRows[T](fileIterator: util.Iterator[T]):Int = {
    var countRows = 0
    while(fileIterator.hasNext()) {
      countRows += 1
      fileIterator.next()
    }
    countRows
  }
}
