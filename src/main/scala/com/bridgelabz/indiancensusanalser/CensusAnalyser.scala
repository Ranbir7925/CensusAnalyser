package com.bridgelabz.indiancensusanalser

import java.io.{FileNotFoundException, Reader}
import java.nio.file.{Files, Paths}
import java.util
import com.opencsv.bean.CsvToBeanBuilder
class CensusAnalyser {
  def loadIndiaCensusData(filePath: String): Int = {
    try {
      if (!filePath.endsWith(".csv")) {
        throw new CensusAnalyserException(CensusAnalyzerExceptionEnums.inCorrectFileType)
      }
      val reader = Files.newBufferedReader(Paths.get(filePath))
      val censusCSVIterator = getCSVFileIterator(reader, classOf[IndiaCensusCSV])
      getRowCount(censusCSVIterator)
    }
    catch {
      case ex: FileNotFoundException => throw new CensusAnalyserException(CensusAnalyzerExceptionEnums.inCorrectFilePath)
    }
  }

  def loadIndiaStateCode(filePath: String): Int = {
    try {
      if (!filePath.endsWith(".csv")) {
        throw new CensusAnalyserException(CensusAnalyzerExceptionEnums.inCorrectFileType)
      }
      val reader = Files.newBufferedReader(Paths.get(filePath))
      val censusCSVIterator = getCSVFileIterator(reader, classOf[IndiaStateCodeCSV ])
      getRowCount(censusCSVIterator)
    }
    catch {
      case ex: FileNotFoundException => throw new CensusAnalyserException(CensusAnalyzerExceptionEnums.inCorrectFilePath)
    }
  }

  def getCSVFileIterator[A](reader: Reader, csvClass: Class[A]): util.Iterator[A]={
    val csvToBeanBuilder = new CsvToBeanBuilder[A](reader)
    csvToBeanBuilder.withType(csvClass).withIgnoreLeadingWhiteSpace(true)
    val csvToBean = csvToBeanBuilder.build()
    csvToBean.iterator()
  }
  def getRowCount[A](commonIterator:util.Iterator[A]):Int= {
    var count = 0
    while (commonIterator.hasNext()) {
      count += 1
      commonIterator.next()
    }
    count
  }
}
