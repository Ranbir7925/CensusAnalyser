package com.bridgelabz.indiancensusanalser
import java.io.FileNotFoundException
import java.nio.file.{Files, Paths}
import java.util
class CensusAnalyser {
  def loadIndiaCensusData(filePath: String): Int = {
    try {
      if (!filePath.toLowerCase.endsWith(".csv")) {
        throw new CensusAnalyserException(CensusAnalyzerExceptionEnums.inCorrectFileType)
      }
      val reader = Files.newBufferedReader(Paths.get(filePath))
//      val censusCSVIterator = new OpenCSVBuilder getCSVFileIterator(reader, classOf[IndiaCensusCSV])
      val csvBuilder = CSVBuilderFactory.createCSVBuilder()
      val censusCSVIterator = csvBuilder.getCSVFileIterator(reader,classOf[IndiaCensusCSV])
      getRowCount(censusCSVIterator)

    }
    catch {
      case ex: FileNotFoundException => throw new CensusAnalyserException(CensusAnalyzerExceptionEnums.inCorrectFilePath)
    }
  }

  def loadIndiaStateCode(filePath: String): Int = {
    try {
      if (!filePath.toLowerCase.endsWith(".csv")) {
        throw new CensusAnalyserException(CensusAnalyzerExceptionEnums.inCorrectFileType)
      }
      val reader = Files.newBufferedReader(Paths.get(filePath))
      val csvBuilder = CSVBuilderFactory.createCSVBuilder()
      val censusCSVIterator = csvBuilder.getCSVFileIterator(reader,classOf[IndiaStateCodeCSV])
      getRowCount(censusCSVIterator)
    }
    catch {
      case ex: FileNotFoundException => throw new CensusAnalyserException(CensusAnalyzerExceptionEnums.inCorrectFilePath)
    }
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
