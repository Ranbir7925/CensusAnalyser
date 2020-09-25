import com.bridgelabz.indiancensusanalser.{CensusAnalyser, CensusAnalyzerExceptionEnums}
import org.scalatest.FunSuite

class CensusAnalyserTest extends FunSuite {
  test("given_IndianCensusCSVFile_ShouldReturnsCorrectRecords") {
    val objCensus = new CensusAnalyser()
    assert(objCensus.loadIndiaCensusData("./src/test/resources/IndiaStateCensusData.csv") == 29)
  }
  test("givenIndianCensusDataCSVFile_whenWithWrongFilePath_ShouldThrowException") {
    val throws = intercept[Exception] {
      val objCensus = new CensusAnalyser()
      objCensus.loadIndiaCensusData("./src/resources/IndiaStateCensusData.csv")
    }
    assert(throws.getMessage == CensusAnalyzerExceptionEnums.inCorrectFilePath.toString)
  }
}
