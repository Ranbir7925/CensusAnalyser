package com.bridgelabz.indiancensusanalser

class IndiaStateCensusDAO {
  var state:String =null
  var population = 0
  var areaInSqKm = 0
  var densityPerSqKm = 0
  var stateCode:String=null

  def this(indiaCensusCSV: IndiaCensusCSV){
    this()
    state=indiaCensusCSV.state
    areaInSqKm=indiaCensusCSV.areaInSqKm
    densityPerSqKm=indiaCensusCSV.densityPerSqKm
    population=indiaCensusCSV.population
  }

  def this(indiaStateCodeCSV: IndiaStateCodeCSV){
    this()
    state=indiaStateCodeCSV.sateName
    stateCode= indiaStateCodeCSV.stateCode
  }
}
