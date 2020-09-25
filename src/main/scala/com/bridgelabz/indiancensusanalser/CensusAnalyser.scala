package com.bridgelabz.indiancensusanalser

import scala.io.Source

class CensusAnalyser {
  def loadIndiaCensusData(filePath: String): Int = {
    try {
      val fileReader = Source.fromFile(filePath)
      var countRow = 0
      for (_ <- fileReader.getLines()) {
        countRow += 1
      }
      countRow - 1
    }
    catch {
      case ex: Exception => println(ex)
        1
    }
  }
}
