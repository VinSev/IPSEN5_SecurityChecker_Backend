package nl.hsleiden.IPRWC_Webshop_Backend;

import nl.hsleiden.IPSEN5_SecurityChecker_Backend.MainApplication;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.model.scan.ScanReport;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.scan.header.HeaderScan;
import nl.hsleiden.IPSEN5_SecurityChecker_Backend.service.scan.ScanReportService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest(classes = {MainApplication.class, ScanReport.class, ScanReportService.class})
class ScanRapportServiceTest {
	private ScanReportService scanReportService = new ScanReportService();
	private Map<String, Object> resultMap = new HashMap<>();
	private ScanReport testRapport = new ScanReport("Header","/scan/header", 0 , resultMap);

	@Test
	void contextLoads() {
	}

	@Test
	void CheckGrading_OfRapportScan(){
		//Arrange
		this.resultMap.put("Header", new HeaderScan());
		int expectedGrade = 7;

		//Act
		testRapport.setResult(resultMap);
		ScanReport result = this.scanReportService.giveGradingToScanReport(testRapport);

		//Assert
		assertThat(scanReportService).isNotNull();
		assertThat(result.getGrade()).isEqualTo(expectedGrade);
	}

}
