package com.bridgelabz.indiancensusanalser

case class IndiaStateCodeCSV() {

  import com.opencsv.bean.CsvBindByName

  @CsvBindByName(column = "SrNo", required = true) var SrNo: String = null
  @CsvBindByName(column = "State Name", required = true) var state: String = null
  @CsvBindByName(column = "TIN", required = true) var TIN: Int = 0
  @CsvBindByName(column = "StateCode", required = true) var stateCode: String = null

  override def toString: String = "IndiaStateCodeCSV{" + "SrNo='" + SrNo + '\''+ ", state='" + state + '\'' + ", TIN='" + TIN + '\'' + ", stateCode='" + stateCode + '\'' + '}'
}
