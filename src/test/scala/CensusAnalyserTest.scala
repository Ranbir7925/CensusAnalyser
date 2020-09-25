import org.scalatest.FunSuite

class CensusAnalyserTest extends FunSuite{
  test("given_IndianCensusCSVFile_ShouldReturnsCorrectRecords"){
    val objCensus = new CensusAnalyser()
    assert(objCensus.loadIndiaCensusData("./src/test/resources/IndiaStateCensusData.csv")==29)
  }

}
