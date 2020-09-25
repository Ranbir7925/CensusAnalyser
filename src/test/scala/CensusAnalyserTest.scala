import com.bridgelabz.indiancensusanalser.{CensusAnalyser, CensusAnalyzerExceptionEnums}
import org.scalatest.FunSuite

class CensusAnalyserTest extends FunSuite {
  val INDIA_CENSUS_CSV_FILE_PATH   = "./src/test/resources/IndiaStateCensusData.csv"
  val WRONG_CSV_FILE_PATH  = "./src/resources/IndiaStateCensusData.csv"
  val WRONG_CSV_FILE_TYPE_PATH  = "./src/test/resources/IndiaStateCensusData.txt"
  val INVALID_DELIMITER_FILE_PATH = "./src/test/resources/InvalidDelimitersIndiaStateCensusData.csv"
  val INVALID_HEADER_FILE_PATH = "./src/test/resources/InvalidHeadersIndiaStateCensusData.csv"

  test("given_IndianCensusCSVFile_ShouldReturnCorrectRecords") {
    val objCensus = new CensusAnalyser()
    assert(objCensus.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH) == 29)
  }
  test("givenIndianCensusDataCSVFile_whenWithWrongFilePath_ShouldThrowException") {
    val throws = intercept[Exception] {
      val objCensus = new CensusAnalyser()
      objCensus.loadIndiaCensusData(WRONG_CSV_FILE_PATH)
    }
    assert(throws.getMessage == CensusAnalyzerExceptionEnums.inCorrectFilePath.toString)
  }

  test("givenIndianCensusDataCSVFile_whenWithWrongFileType_ShouldThrowException"){
    val throws= intercept[Exception]{
      val objCensus = new CensusAnalyser
      objCensus.loadIndiaCensusData(WRONG_CSV_FILE_TYPE_PATH)
    }
    assert(throws.getMessage == CensusAnalyzerExceptionEnums.inCorrectFileType.toString)
  }

  test("givenIndianCensusDataCSVFile_whenWithWrongDelimiters_shouldThrowException"){
    val throws = intercept[Exception] {
      val objCensus = new CensusAnalyser
      objCensus.loadIndiaCensusData(INVALID_DELIMITER_FILE_PATH)
    }
    assert(throws.getMessage == CensusAnalyzerExceptionEnums.inCorrectDelimiter.toString)
  }

  test("givenIndianCensusDataCSVFile_WhenWithWrongHeaders_ShouldThrowException"){
    val throws = intercept[Exception]{
      val objCensus = new CensusAnalyser
      objCensus.loadIndiaCensusData(INVALID_HEADER_FILE_PATH)
    }
    assert(throws.getMessage == CensusAnalyzerExceptionEnums.inCorrectHeaders.toString)
  }
}
