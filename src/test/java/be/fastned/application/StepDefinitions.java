package be.fastned.application;

import java.util.List;

import be.fastned.application.service.PlannerService;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.*;

//import jdk.internal.util.xml.impl.Input;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.AssertTrue;

public class StepDefinitions {
	
	WebDriver driver;

	@Given("^Ik ben ingelogd als planner$")
	public void IkBenIngelogdAlsPlanner() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "C:\\applicaties\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8080/");

		new WebDriverWait(driver, 60).until(ExpectedConditions
				.textToBePresentInElementLocated(By.tagName("body"), "# Welkom op de fastned-applicatie! #"));

		driver.findElement(By.id("wachtwoord")).sendKeys("test");
		driver.findElement(By.id("gebruikersnaam")).sendKeys("test");
		driver.findElement(By.id("login")).click();
	}

	@And("^Ik bevind me op de Home pagina$")
	public void IkBevindMeOpDeHomePagina() throws Throwable {
		driver.getTitle().equals("Home");
	}

	@When("^Ik klik op de \"Beheer Afspraken\" knop$")
	public void DeDatabasesZijnNietLeeg() throws Throwable {
		driver.findElement(By.id("linkBeheerAfspraak")).click();
	}

	@And("^Ik een installateur selecteer$")
	public void SelecteerInstallateur() throws Throwable {
		Select opties = new Select(driver.findElement(By.id("selectCreateInstallateur")));
		opties.selectByIndex(3);
	}
	@And("^ik een laadpaal selecteer$")
	public void SelecteerLaadpaal() throws Throwable {
		Select opties = new Select(driver.findElement(By.id("selectCreateLaadpaal")));
		opties.selectByIndex(1);
	}
	@And("^ik een contract selecteer$")
	public void SelecteerContract() throws Throwable {
		Select opties = new Select(driver.findElement(By.id("selectCreateContract")));
		opties.selectByIndex(1);
	}
	@And("^ik een status ingeef$")
	public void GeefStatusIn() throws Throwable {
		driver.findElement(By.id("createStatus")).sendKeys("test");
	}
	@And("^ik afsluit door op de \"Verwerk Nieuwe Afspraak\" knop te klikken$")
	public void VerwerkNieuweAfspraak() throws Throwable {
		driver.findElement(By.id("btnMaakAfspraak")).click();
	}

	@And("^ik naar de nieuwe afspraak navigeer door op de \"Bekijk Afspraak\" knop te klikken$")
	public void toonNieuweAfspraak() throws Throwable {
		driver.findElement(By.id("btnBekijkAfspraak")).click();
	}

	@Then("^Zie ik de nieuwe afspraak$")
	public void bekijkNieuweAfspraak() throws Throwable {
		Select opties = new Select(driver.findElement(By.id("selectBekijkAfspraak")));
		opties.selectByIndex(5);
	}

	@And("^Kan ik op \"Terug naar Home\" klikken$")
	public void KeerTerugNaarHome() throws Throwable {
		driver.findElement(By.id("btnHome")).click();
	}

	@And("^Word ik teruggeleid naar de Home Pagina$")
	public void TeruggeleidNaarHome() throws Throwable {
		driver.getTitle().equals("Home");
		driver.quit();
	}

	/*@When("^I enter \"([^\"]*)\" in the ([^\"]*) field$")
	public void i_enter_in_the_firstname_field(String enteredText, String fieldName) throws Throwable {

		driver.findElement(By.id(fieldName)).sendKeys(enteredText);
	}

	@When("^I press on the Submit button$")
	public void i_press_on_the_Submit_button() throws Throwable {
		driver.findElement(By.name("submit")).click();
	}

	class LabelData {
		String label;
		String data;
	}
	
	@Then("^I should see the following on the screen$")
	public void i_should_see_the_following_on_the_screen(List<LabelData> checklist) throws Throwable {
		// wacht tot de juiste pagina geladen is
		new WebDriverWait(driver, 10).until(ExpectedConditions
												.textToBePresentInElementLocated(By.tagName("body"), "Details van persoon")); 

		String bodyText = driver.findElement(By.tagName("body")).getText();
		for (LabelData ld: checklist){
			String text2bFound = ld.label + " " + ld.data;
			Assert.assertTrue("Did not find this text: "+text2bFound+"\n",bodyText.contains(text2bFound));
		}

	}

	@When("^I click the Lijst van alle personen Link$")
	public void i_click_the_Home_Link() throws Throwable {
		driver.findElement(By.linkText("Lijst van alle personen")).click();
	}

	@Then("^I should see a list containing \"([^\"]*)\"$")
	public void i_should_see_a_list_containing(String text2bFound) throws Throwable {
		// wacht tot de juiste pagina geladen is
		new WebDriverWait(driver, 10).until(ExpectedConditions
												.textToBePresentInElementLocated(By.tagName("body"), "Lijst van personen")); 
		String bodyText = driver.findElement(By.tagName("body")).getText();
		Assert.assertTrue("Did not find this text: "+text2bFound+"\n",bodyText.contains(text2bFound));
	}

	@Then("^I close the browser$")
	public void i_close_the_browser() throws Throwable {
		driver.quit();
	}*/
}
