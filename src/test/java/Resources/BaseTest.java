package Resources;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import Client.ConfigManager;
import Client.RestClient;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

public class BaseTest {

	protected RestClient restClient;

	@Parameters({ "baseUrl" })
	@BeforeTest
	public void setup(@Optional String baseUrl) {

		RestAssured.filters(new AllureRestAssured());
		if (baseUrl != null) {
			ConfigManager.set("baseUrl", baseUrl);
		}
		// Initialize RestClient
		restClient = new RestClient();
	}

}
