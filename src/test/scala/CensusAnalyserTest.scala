import com.bridgelabz.indiancensusanalser.{CensusAnalyser, CensusAnalyzerExceptionEnums}
import org.scalatest.FunSuite

class CensusAnalyserTest extends FunSuite {
  val INDIA_STATE_CODE_CSV_FILE_PATH  = "./src/test/resources/IndiaStateCensusData.csv"
  val STATE_CODE_CSV_WRONG_FILE_PATH = "./src/resources/IndiaStateCensusData.csv"
  val STATE_CODE_CSV_WRONG_TYPE_PATH = "./src/test/resources/IndiaStateCensusData.txt"
  test("given_IndianCensusCSVFile_ShouldReturnCorrectRecords") {
    val objCensus = new CensusAnalyser()
    assert(objCensus.loadIndiaCensusData(INDIA_STATE_CODE_CSV_FILE_PATH) == 29)
  }
  test("givenIndianCensusDataCSVFile_whenWithWrongFilePath_ShouldThrowException") {
    val throws = intercept[Exception] {
      val objCensus = new CensusAnalyser()
      objCensus.loadIndiaCensusData(STATE_CODE_CSV_WRONG_FILE_PATH)
    }
    assert(throws.getMessage == CensusAnalyzerExceptionEnums.inCorrectFilePath.toString)
  }
  test("givenIndianCensusDataCSVFile_whenWithWrongFileType_ShouldThrowException"){
    val throws= intercept[Exception]{
      val objCensus = new CensusAnalyser
      objCensus.loadIndiaCensusData(STATE_CODE_CSV_WRONG_TYPE_PATH)
    }
    assert(throws.getMessage == CensusAnalyzerExceptionEnums.inCorrectFileType.toString)
  }
}
