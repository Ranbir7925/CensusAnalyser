package com.bridgelabz.indiancensusanalser

import java.io.Reader
import java.util

import com.opencsv.bean.CsvToBeanBuilder

class OpenCSVBuilder[A] extends TraitCSVBuilder {
  @throws[CSVBuilderException]
  def getCSVFileIterator[A](reader: Reader, csvClass: Class[A]): util.Iterator[A]  = {
    try {
      val csvToBeanBuilder = new CsvToBeanBuilder[A](reader)
      csvToBeanBuilder.withType(csvClass).withIgnoreLeadingWhiteSpace(true)
      val csvToBean = csvToBeanBuilder.build()
      csvToBean.iterator()
    }
    catch {
      case _:Exception=> throw new CensusAnalyserException(CensusAnalyzerExceptionEnums.unableToParse)
    }
  }
}
