package com.bridgelabz.indiancensusanalser

object CSVBuilderFactory {
  def createCSVBuilder() = {
    new OpenCSVBuilder()
  }
}
