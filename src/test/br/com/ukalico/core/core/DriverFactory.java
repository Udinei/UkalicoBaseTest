package br.com.ukalico.core.core;

import br.com.ukalico.core.core.Propriedades.TipoExecucao;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory {

	/** Desentralizando drivers do browser com  threadDrive, cada teste tera seu driver do browser */
	private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>() {

		@Override
		protected synchronized WebDriver initialValue() {
			return initDriver();
		}

	};

	private DriverFactory() {
	}

	public static WebDriver getDriver() {
		return threadDriver.get();
	}

	public static WebDriver initDriver() {
		System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\driver\\chromedriver.exe");
		WebDriver driver = null;

		if (Propriedades.TIPO_EXECUCAO == TipoExecucao.LOCAL) {

			switch (Propriedades.BROWSER) {
			case FIREFOX:
				driver = new FirefoxDriver();
				break;
			case CHROME:
				driver = new ChromeDriver();
				break;
			}
		}

		if (Propriedades.TIPO_EXECUCAO == TipoExecucao.GRID) {
			DesiredCapabilities cap = new DesiredCapabilities();

			switch (Propriedades.BROWSER) {
			case FIREFOX:
				cap.setBrowserName("Firefox");
				break;
			case CHROME:
				cap.setBrowserName("chrome");
				break;
			}

			try {

				/** driver - Armazena endere�o do GRID.
				 *  Pre-condi��o: App selenium-server-standalone em execu��o, execute:
				 * java "-Dwebdriver.chrome.driver=c:\\workspace-dev\\UkalikoBaseTest\\src\\test\\resources\\driver\\chromedriver.exe" -jar selenium-server-standalone-3.5.3.jar -role hub - startando o grid hub
				 * java "-Dwebdriver.chrome.driver=c:\\workspace-dev\\UkalikoBaseTest\\src\\test\\resources\\driver\\chromedriver.exe" -jar selenium-server-standalone-3.5.3.jar -role node - conectando um node ao grid
				 * java "-Dwebdriver.chrome.driver=c:\\workspace-dev\\UkalikoBaseTest\\src\\test\\resources\\driver\\chromedriver.exe" -jar selenium-server-standalone-3.5.3.jar -role node -port 5556 - conectando um novo nó na porta 5556 um novo n�
				 *  
				 *  */

                 // a URL deve ser a da maquina local exibida no console apos subir o nó de hub
				driver = new RemoteWebDriver(new URL("http://10.125.0.77:4444/wd/hub"), cap);

			} catch (MalformedURLException e) {
				System.err.println("Falha na conexão com o GRID");
				e.printStackTrace();
			}
		}

		driver.manage().window().setSize(new Dimension(1200, 765));
		return driver;
	}

	public static void killDriver() {
		WebDriver driver = getDriver();

		if (driver != null) {
			driver.quit();
			driver = null;
		}

		// limpa driver gerenciado
		if (threadDriver != null) {
			threadDriver.remove();
		}

	}
}
