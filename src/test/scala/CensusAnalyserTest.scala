import com.bridgelabz.indiancensusanalser.{CensusAnalyser, CensusAnalyzerExceptionEnums, IndiaCensusCSV}
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

  test("given_IndianCensusCSVFile_ShouldReturnCorrectRecords") {
    val objCensus = new CensusAnalyser()
    assert(objCensus.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH) == 29)
  }
  test("givenIndianCensusDataCSVFile_whenWithWrongFilePath_ShouldThrowException") {
    val throws = intercept[Exception] {
      val objCensus = new CensusAnalyser()
      objCensus.loadIndiaCensusData(INDIA_CENSUS_WRONG_CSV_FILE_PATH)
    }
    assert(throws.getMessage == CensusAnalyzerExceptionEnums.inCorrectFilePath.toString)
  }

  test("givenIndianCensusDataCSVFile_whenWithWrongFileType_ShouldThrowException") {
    val throws = intercept[Exception] {
      val objCensus = new CensusAnalyser
      objCensus.loadIndiaCensusData(INDIA_CENSUS_WRONG_CSV_FILE_TYPE_PATH)
    }
    assert(throws.getMessage == CensusAnalyzerExceptionEnums.inCorrectFileType.toString)
  }

  test("givenIndianCensusDataCSVFile_whenWithWrongDelimiters_shouldThrowException") {
    val throws = intercept[Exception] {
      val objCensus = new CensusAnalyser
      objCensus.loadIndiaCensusData(INDIA_CENSUS_INVALID_DELIMITER_FILE_PATH)
    }
    assert(throws.getMessage == CensusAnalyzerExceptionEnums.unableToParse.toString)
  }

  test("givenIndianCensusDataCSVFile_WhenWithWrongHeaders_ShouldThrowException") {
    val throws = intercept[Exception] {
      val objCensus = new CensusAnalyser
      objCensus.loadIndiaCensusData(INDIA_CENSUS_INVALID_HEADER_FILE_PATH)
    }
    assert(throws.getMessage == CensusAnalyzerExceptionEnums.unableToParse.toString)
  }


  test("givenIndiaStateCodeCSVFile_ShouldReturn_CorrectRecords") {
    val objCensus = new CensusAnalyser
    assert(objCensus.loadIndiaStateCode(INDIA_STATE_CODE_CSV_FILE_PATH) == 37)
  }
  test("givenIndianStateCodeCSVFile_whenWithWrongPath_shouldThrowException") {
    val throws = intercept[Exception] {
      val objCensus = new CensusAnalyser
      objCensus.loadIndiaStateCode(INDIA_STATE_CODE_WRONG_CSV_FILE_PATH)
    }
    assert(throws.getMessage == CensusAnalyzerExceptionEnums.inCorrectFilePath.toString)
  }

  test("givenIndianStateCodeCSVFile_whenWithWrongFileType_shouldThrowException") {
    val throws = intercept[Exception] {
      val objCensus = new CensusAnalyser
      objCensus.loadIndiaStateCode(INDIA_STATE_CODE_WRONG_CSV_FILE_TYPE_PATH)
    }
    assert(throws.getMessage == CensusAnalyzerExceptionEnums.inCorrectFileType.toString)
  }

  test("givenIndianStateCodeCSVFile_whenWithWrongDelimiters_shouldThrowException") {
    val throws = intercept[Exception] {
      val objCensus = new CensusAnalyser
      objCensus.loadIndiaStateCode(INDIA_STATE_CODE_INVALID_CSV_DELIMITER_FILE_PATH)
    }
    assert(throws.getMessage == CensusAnalyzerExceptionEnums.unableToParse.toString)
  }
  test("givenIndianStateCodeCSVFile_whenWithWrongHeaders_shouldThrowException") {
    val throws = intercept[Exception] {
      val objCensus = new CensusAnalyser
      objCensus.loadIndiaStateCode(INDIA_STATE_CODE_INVALID_CSV_HEADER_FILE_PATH)
    }
    assert(throws.getMessage == CensusAnalyzerExceptionEnums.unableToParse.toString)
  }
  test("givenIndianCensusData_whenSortedByState_shouldReturnSortedResult"){
    val objCensus = new CensusAnalyser
    objCensus.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH)
    val sortedCensusData = objCensus.getStateWiseSortedCensusData()

    val censusCSV = new Gson().fromJson(sortedCensusData,classOf[Array[IndiaCensusCSV]])
    assert(censusCSV(0).state == "Andhra Pradesh")
    assert(censusCSV.last.state == "West Bengal")
  }

  test("givenIndianCensusData_whenEmptyData_shouldReturnException"){
    val objCensus =  new CensusAnalyser
    val throws = intercept[Exception]{
      objCensus.getStateCodeWiseSortedCensusData()
    }
    assert(throws.getMessage == CensusAnalyzerExceptionEnums.noCensusData.toString)
  }

  test("givenIndianCensusDataAndStateDate_whenSortedByState_shouldReturnSortedResult"){
    val objCensus = new CensusAnalyser
    objCensus.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH)
    objCensus.loadIndiaStateCode(INDIA_STATE_CODE_CSV_FILE_PATH)

    val sortedCensusData = objCensus.getStateCodeWiseSortedCensusData()

    val censusCSV = new Gson().fromJson(sortedCensusData,classOf[Array[IndiaCensusCSV]])
    assert(censusCSV(0).state == "Andhra Pradesh")
    assert(censusCSV.last.state == "West Bengal")
  }
  test("givenIndianStateData_whenEmptyData_shouldReturnException"){
    val objCensus = new CensusAnalyser()
    val throws = intercept[Exception]{
     objCensus.getStateCodeWiseSortedCensusData()
    }
    assert(throws.getMessage == CensusAnalyzerExceptionEnums.noCensusData.toString)
  }
}
