package com.bridgelabz.indiancensusanalser

import java.io.Reader
import java.util

import com.opencsv.bean.CsvToBeanBuilder

class OpenCSVBuilder {
  def getCSVFileIterator[A](reader: Reader, csvClass: Class[A]): util.Iterator[A]={
    val csvToBeanBuilder = new CsvToBeanBuilder[A](reader)
    csvToBeanBuilder.withType(csvClass).withIgnoreLeadingWhiteSpace(true)
    val csvToBean = csvToBeanBuilder.build()
    csvToBean.iterator()
  }
}
