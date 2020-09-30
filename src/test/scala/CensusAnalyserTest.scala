import com.bridgelabz.indiancensusanalser.{CensusAnalyser, Country, CensusAnalyzerExceptionEnums, IndiaCensusDTO}
import com.google.gson.Gson
import org.scalatest.FunSuite

class CensusAnalyserTest extends FunSuite {
  val INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv"
  val INDIA_CENSUS_WRONG_CSV_FILE_PATH = "./main/resources/IndiaStateCensusData.csv"
  val INDIA_CENSUS_WRONG_CSV_FILE_TYPE_PATH = "./src/test/resources/IndiaStateCensusData.txt"
  val INDIA_CENSUS_INVALID_DELIMITER_FILE_PATH = "./src/test/resources/InvalidDelimitersIndiaStateCensusData.csv"
  val INDIA_CENSUS_INVALID_HEADER_FILE_PATH = "./src/test/resources/InvalidHeadersIndiaStateCensusData.csv"

  val INDIA_STATE_CODE_CSV_FILE_PATH = "./src/test/resources/IndiaStateCode.csv"
  val INDIA_STATE_CODE_WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCode.csv"
  val INDIA_STATE_CODE_WRONG_CSV_FILE_TYPE_PATH = "./src/main/resources/IndiaStateCode.txt"
  val INDIA_STATE_CODE_INVALID_CSV_DELIMITER_FILE_PATH = "./src/test/resources/InvalidDelimitersIndiaStateCode.csv"
  val INDIA_STATE_CODE_INVALID_CSV_HEADER_FILE_PATH = "./src/test/resources/InvalidHeadersIndiaStateCode.csv"
  val US_CENSUS_CSV_FILE_PATH = "./src/test/resources/USCensusData.csv"


  test("given_IndianCensusCSVFile_ShouldReturnCorrectRecords") {
    val objCensus = new CensusAnalyser()
    assert(objCensus.loadCensusData(Country.India, INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH) == 29)
  }

  test("givenIndianCensusDataCSVFile_whenWithWrongFilePath_ShouldThrowException") {
    val throws = intercept[Exception] {
      val objCensus = new CensusAnalyser()
      objCensus.loadCensusData(Country.India, INDIA_CENSUS_WRONG_CSV_FILE_PATH, INDIA_CENSUS_WRONG_CSV_FILE_PATH)
    }
    assert(throws.getMessage == CensusAnalyzerExceptionEnums.inCorrectFilePath.toString)
  }

  test("givenIndianCensusDataCSVFile_whenWithWrongFileType_ShouldThrowException") {
    val throws = intercept[Exception] {
      val objCensus = new CensusAnalyser
      objCensus.loadCensusData(Country.India, INDIA_CENSUS_WRONG_CSV_FILE_TYPE_PATH, INDIA_STATE_CODE_WRONG_CSV_FILE_TYPE_PATH)
    }
    assert(throws.getMessage == CensusAnalyzerExceptionEnums.inCorrectFileType.toString)
  }

  test("givenIndianCensusDataCSVFile_whenWithWrongDelimiters_shouldThrowException") {
    val throws = intercept[Exception] {
      val objCensus = new CensusAnalyser
      objCensus.loadCensusData(Country.India, INDIA_CENSUS_INVALID_DELIMITER_FILE_PATH, INDIA_STATE_CODE_INVALID_CSV_DELIMITER_FILE_PATH)
    }
    assert(throws.getMessage == CensusAnalyzerExceptionEnums.unableToParse.toString)
  }

  test("givenIndianCensusDataCSVFile_WhenWithWrongHeaders_ShouldThrowException") {
    val throws = intercept[Exception] {
      val objCensus = new CensusAnalyser
      objCensus.loadCensusData(Country.India, INDIA_CENSUS_INVALID_HEADER_FILE_PATH, INDIA_STATE_CODE_INVALID_CSV_HEADER_FILE_PATH)
    }
    assert(throws.getMessage == CensusAnalyzerExceptionEnums.unableToParse.toString)
  }

  test("givenIndianCensusData_whenSortedByState_shouldReturnSortedResult") {
    val objCensus = new CensusAnalyser
    objCensus.loadCensusData(Country.India, INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH)
    val sortedCensusData = objCensus.getStateWiseSortedCensusData

    val censusCSV = new Gson().fromJson(sortedCensusData, classOf[Array[IndiaCensusDTO]])
    assert(censusCSV(0).state == "Andhra Pradesh")
    assert(censusCSV.last.state == "West Bengal")
  }

  test("givenIndianCensusData_whenEmptyData_shouldReturnException") {
    val objCensus = new CensusAnalyser
    val throws = intercept[Exception] {
      objCensus.getStateCodeWiseSortedCensusData
    }
    assert(throws.getMessage == CensusAnalyzerExceptionEnums.noCensusData.toString)
  }

  test("givenIndianCensusDataAndStateData_whenSortedByState_shouldReturnSortedResult") {
    val objCensus = new CensusAnalyser
    objCensus.loadCensusData(Country.India, INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH)
    val sortedCensusData = objCensus.getStateCodeWiseSortedCensusData

    val censusCSV = new Gson().fromJson(sortedCensusData, classOf[Array[IndiaCensusDTO]])
    assert(censusCSV(0).state == "Andhra Pradesh")
    assert(censusCSV.last.state == "West Bengal")
  }

  test("givenIndianStateData_whenEmptyData_shouldReturnException") {
    val objCensus = new CensusAnalyser()
    val throws = intercept[Exception] {
      objCensus.getStateCodeWiseSortedCensusData
    }
    assert(throws.getMessage == CensusAnalyzerExceptionEnums.noCensusData.toString)
  }

  test("givenIndianCensusDataAndStateData_whenSortedByPopulationDensity_shouldReturnSortedResult") {
    val objCensus = new CensusAnalyser()
    objCensus.loadCensusData(Country.India, INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH)
    val sortedCensusData = objCensus.getPopulationDensityWiseSortedCensusData

    val censusCSV = new Gson().fromJson(sortedCensusData, classOf[Array[IndiaCensusDTO]])
    assert(censusCSV(0).state == "Bihar")
    assert(censusCSV.last.state == "Arunachal Pradesh")
  }

  test("givenIndianCensusDataAndStateData_whenSortedByPopulation_shouldReturnSortedResult") {
    val objCensus = new CensusAnalyser()
    objCensus.loadCensusData(Country.India, INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH)
    val sortedCensusData = objCensus.getPopulationWiseSortedCensusData

    val censusCSV = new Gson().fromJson(sortedCensusData, classOf[Array[IndiaCensusDTO]])
    assert(censusCSV(0).state == "Uttar Pradesh")
    assert(censusCSV.last.state == "Sikkim")
  }

  test("givenIndianStateData_whenEmptyDataPopulation_shouldReturnException") {
    val objCensus = new CensusAnalyser()
    val throws = intercept[Exception] {
      objCensus.getPopulationWiseSortedCensusData
    }
    assert(throws.getMessage == CensusAnalyzerExceptionEnums.noCensusData.toString)
  }

  test("givenIndianCensusDataAndStateData_whenSortedByArea_shouldReturnSortedResult") {
    val objCensus = new CensusAnalyser()
    objCensus.loadCensusData(Country.India, INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH)
    val sortedCensusData = objCensus.getAreaWiseSortedCensusData

    val censusCSV = new Gson().fromJson(sortedCensusData, classOf[Array[IndiaCensusDTO]])
    assert(censusCSV(0).state == "Rajasthan")
    assert(censusCSV.last.state == "Goa")
  }

  test("givenIndianStateData_whenEmptyDataArea_shouldReturnException") {
    val objCensus = new CensusAnalyser()
    val throws = intercept[Exception] {
      objCensus.getAreaWiseSortedCensusData
    }
    assert(throws.getMessage == CensusAnalyzerExceptionEnums.noCensusData.toString)
  }

  test("given_USCensusCSVFile_ShouldReturnCorrectRecords") {
    val objCensus = new CensusAnalyser()
    assert(objCensus.loadCensusData(Country.USA, US_CENSUS_CSV_FILE_PATH) == 51)
  }

  test("givenUSCensusData_whenSortedByPopulation_shouldReturnSortedResult") {
    val objCensus = new CensusAnalyser()
    objCensus.loadCensusData(Country.USA, US_CENSUS_CSV_FILE_PATH)
    val sortedCensusData = objCensus.getPopulationWiseSortedCensusData

    val censusCSV = new Gson().fromJson(sortedCensusData, classOf[Array[IndiaCensusDTO]])
    assert(censusCSV(0).state == "California")
    assert(censusCSV.last.state == "Wyoming")
  }
  test("givenUSCensusData_whenSortedByArea_shouldReturnSortedResult") {
    val objCensus = new CensusAnalyser()
    objCensus.loadCensusData(Country.USA, US_CENSUS_CSV_FILE_PATH)
    val sortedCensusData = objCensus.getAreaWiseSortedCensusData

    val censusCSV = new Gson().fromJson(sortedCensusData, classOf[Array[IndiaCensusDTO]])
    assert(censusCSV(0).state == "Alaska")
    assert(censusCSV.last.state == "District of Columbia")
  }

  test("givenUSCensusData_whenSortedByPopulationDensity_shouldReturnSortedResult") {
    val objCensus = new CensusAnalyser()
    objCensus.loadCensusData(Country.USA, US_CENSUS_CSV_FILE_PATH)
    val sortedCensusData = objCensus.getPopulationDensityWiseSortedCensusData

    val censusCSV = new Gson().fromJson(sortedCensusData, classOf[Array[IndiaCensusDTO]])
    assert(censusCSV(0).state == "District of Columbia")
    assert(censusCSV.last.state == "Alaska")
  }

  test("givenUSCensusData_whenSortedByStateWise_shouldReturnSortedResult") {
    val objCensus = new CensusAnalyser()
    objCensus.loadCensusData(Country.USA, US_CENSUS_CSV_FILE_PATH)
    val sortedCensusData = objCensus.getStateWiseSortedCensusData

    val censusCSV = new Gson().fromJson(sortedCensusData, classOf[Array[IndiaCensusDTO]])
    assert(censusCSV(0).state == "Alabama")
    assert(censusCSV.last.state == "Wyoming")
  }

  test("givenUSCensusData_whenSortedByStateCodeWise_shouldReturnSortedResult") {
    val objCensus = new CensusAnalyser()
    objCensus.loadCensusData(Country.USA, US_CENSUS_CSV_FILE_PATH)
    val sortedCensusData = objCensus.getStateCodeWiseSortedCensusData

    val censusCSV = new Gson().fromJson(sortedCensusData, classOf[Array[IndiaCensusDTO]])
    assert(censusCSV(0).state == "Alaska")
    assert(censusCSV.last.state == "Wyoming")
  }

  test("givenIndiaCensus&USCensusData_whenSortedByPopulationDensity_shouldReturnSortedResult") {
    val objCensus = new CensusAnalyser()
    objCensus.loadCensusData(Country.USA, US_CENSUS_CSV_FILE_PATH)
    val sortedCensusData = objCensus.getPopulationDensityWiseSortedCensusData
    val censusCSV = new Gson().fromJson(sortedCensusData, classOf[Array[IndiaCensusDTO]])
    assert(censusCSV(0).state == "District of Columbia")

    objCensus.loadCensusData(Country.India, INDIA_CENSUS_CSV_FILE_PATH, INDIA_STATE_CODE_CSV_FILE_PATH)
    val sortedCensusDataIndia = objCensus.getPopulationDensityWiseSortedCensusData
    val censusCSVIndia = new Gson().fromJson(sortedCensusDataIndia, classOf[Array[IndiaCensusDTO]])
    assert(censusCSVIndia(0).state == "Bihar")
  }
}
