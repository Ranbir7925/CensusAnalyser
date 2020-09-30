package com.bridgelabz.indiancensusanalser

import java.io.Reader
import java.util

import com.opencsv.bean.{CsvToBean, CsvToBeanBuilder}

class OpenCSVBuilder[A] extends TraitCSVBuilder {
  @throws[CSVBuilderException]
  def getCSVFileIterator[A](reader: Reader, csvClass: Class[A]): util.Iterator[A] = {
    try {
      val csvToBean = getCSVBean(reader, csvClass)
      csvToBean.iterator()
    }
    catch {
      case _: RuntimeException => throw new CensusAnalyserException(CensusAnalyzerExceptionEnums.unableToParse)
    }
  }

  def getCSVFileList[A](reader: Reader, csvClass: Class[A]): util.List[A] = {
    try {
      val csvToBean = getCSVBean(reader, csvClass)
      csvToBean.parse()
    }
    catch {
      case _: RuntimeException => throw new CensusAnalyserException(CensusAnalyzerExceptionEnums.unableToParse)
    }
  }

  def getCSVBean[A](reader: Reader, csvClass: Class[A]): CsvToBean[A] = {
    try {
      val csvToBeanBuilder = new CsvToBeanBuilder[A](reader)
      csvToBeanBuilder.withType(csvClass).withIgnoreLeadingWhiteSpace(true)
      val csvToBean = csvToBeanBuilder.build()
      csvToBean
    }
    catch {
      case _: RuntimeException => throw new CensusAnalyserException(CensusAnalyzerExceptionEnums.unableToParse)
    }
  }
}
