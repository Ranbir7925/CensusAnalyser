package com.bridgelabz.indiancensusanalser

import java.io.Reader
import java.util

trait TraitCSVBuilder {
  def getCSVFileIterator[A] (reader:Reader,csvClass:Class[A]): util.Iterator[A]
}
