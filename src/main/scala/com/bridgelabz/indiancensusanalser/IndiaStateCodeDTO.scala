package com.bridgelabz.indiancensusanalser

import com.opencsv.bean.CsvBindByName

/**
 * class Binds Data to form Json format
 */
class IndiaStateCodeDTO {

  @CsvBindByName(column = "SrNo", required = true)
  var SrNo: String = null
  @CsvBindByName(column = "State Name", required = true)
  var stateName: String = null
  @CsvBindByName(column = "TIN", required = true)
  var TIN: Int = 0
  @CsvBindByName(column = "StateCode", required = true)
  var stateCode: String = null

  /**
   *  overrides objects to form string representation of an object
   * @return String
   */
  override def toString: String = "IndiaStateCodeCSV{" +
    "SrNo='" + SrNo + '\'' +
    ", state='" + stateName + '\'' +
    ", TIN='" + TIN + '\'' +
    ", stateCode='" + stateCode + '\'' + '}'
}
