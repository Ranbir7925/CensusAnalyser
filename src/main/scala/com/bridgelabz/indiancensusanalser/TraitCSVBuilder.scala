package com.bridgelabz.indiancensusanalser

import java.io.Reader
import java.util

@throws[CSVBuilderException]
trait TraitCSVBuilder {
  def getCSVFileIterator[A] (reader:Reader,csvClass:Class[A]): util.Iterator[A]
  def getCSVFileList[A] (reader:Reader,csvClass:Class[A]): util.List[A]
}
