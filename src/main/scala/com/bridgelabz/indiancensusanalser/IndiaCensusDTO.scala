package com.bridgelabz.indiancensusanalser

import com.opencsv.bean.CsvBindByName

/**
 * Class Binds Data to form Json format
 */
class IndiaCensusDTO() {

  @CsvBindByName(column = "State", required = true)
  var state: String = null
  @CsvBindByName(column = "Population", required = true)
  var population: Int = 0
  @CsvBindByName(column = "AreaInSqKm", required = true)
  var areaInSqKm: Int = 0
  @CsvBindByName(column = "DensityPerSqKm", required = true)
  var densityPerSqKm: Int = 0

  /**
   * overrides objects to form string representation of an object
   * @return String
   */
  override def toString: String = "IndiaCensusCSV{" +
    "State='" + state + '\'' +
    ", Population='" + population + '\'' +
    ", AreaInSqKm='" + areaInSqKm + '\'' +
    ", DensityPerSqKm='" + densityPerSqKm + '\'' + '}'
}
