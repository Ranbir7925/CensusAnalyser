package com.bridgelabz.indiancensusanalser

case class IndiaCensusCSV() {

  import com.opencsv.bean.CsvBindByName

  @CsvBindByName(column = "State", required = true) var state: String = null
  @CsvBindByName(column = "Population", required = true) var population = 0
  @CsvBindByName(column = "AreaInSqKm", required = true) var areaInSqKm = 0
  @CsvBindByName(column = "DensityPerSqKm", required = true) var densityPerSqKm = 0

  override def toString: String = "IndiaCensusCSV{" + "state='" + state + '\'' + ", population=" + population + ", areaInSqKm=" + areaInSqKm + ", densityPerSqKm=" + densityPerSqKm + '}'
}
