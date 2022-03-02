package br.com.ukalico.core.core;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import br.com.ukalico.core.core.Propriedades.TipoExecucao;

/**
 * @author Udinei
 *Classe de fabricação, gerenciamento e execuçaõ dos driver dos browsers usados pelo framework
 * 
 */
public class DriverFactory {
   
	/** PATH_WEBDRIVER - create path to Webdriver in Windows or Linux 
	 *  put driver in UkalicoBaseTest\\src\\test\\resources\\driver\\
	 * */
	public static final Path PATH_WEBDRIVER = Paths.get(System.getProperty("user.dir")
			                                            .toString(),"src","test","resources","driver",
			                                            Propriedades.BROWSER.getDescricao());
			
	/** Gerencia o driver do browser, cada teste tera seu driver do browser */
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
	    		
		System.setProperty("webdriver.chrome.driver", PATH_WEBDRIVER.toString()); 
		WebDriver driver = null;
	
             		
        // TODO: verificar porque ao usar o codigo abaixo, o driver não fecha o browser?
		// WebDriver driver = new ChromeDriver();    

		if (Propriedades.TIPO_EXECUCAO == TipoExecucao.LOCAL) {

			switch (Propriedades.BROWSER) {
			case FIREFOX:
				driver = new FirefoxDriver();
				break;
			case CHROME:
				driver = new ChromeDriver(createChromeOptions());
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

				/** driver - Armazena endereco do GRID. 
				 * Pre-condicao: Selenium Grid hub em execucao
				 * 
				 * Startando o Selenium Grid hub
				 * java "-Dwebdriver.chrome.driver=E:\\workspace-dev\\UkalicoBaseTest\\src\\test\\resources\\driver\\chromedriver.exe" -jar selenium-server-standalone-3.5.3.jar -role hub
				 * 
				 * Conectando um node ao grid 
				 * java "-Dwebdriver.chrome.driver=E:\\workspace-dev\\UkalicoBaseTest\\src\\test\\resources\\driver\\chromedriver.exe" -jar selenium-server-standalone-3.5.3.jar -role node
				 * 
			     * Conectando um novo nó na porta 5556 um novo no				   
				 * java "-Dwebdriver.chrome.driver=E:\\workspace-dev\\UkalicoBaseTest\\src\\test\\resources\\driver\\chromedriver.exe" -jar selenium-server-standalone-3.5.3.jar -role node -port 5556
				 *  
				 * Conectando maquina remota ao Grid hub remoto 				   
				 * java "-Dwebdriver.chrome.driver=E:\\workspace-dev\\UkalicoBaseTest\\src\\test\\resources\\driver\\chromedriver.exe" -jar selenium-server-standalone-3.5.3.jar -role node -hub http://192.168.40.1:4444/grid/register
				 * java "-Dwebdriver.chrome.driver=C:\\testeGrid\\resources\\resources\\driver\\chromedriver.exe" -jar selenium-server-standalone-3.5.3.jar -role node -port 5557 -hub http://192.168.100.9:4444/grid/register
				 *  */

                 // a URL deve ser o da maquina que executando o hub, o ip sera exibido apos subir o nó de hub
				driver = new RemoteWebDriver(new URL("http://192.168.40.1:4444/wd/hub"), cap);
                
			} catch (MalformedURLException e) {
				System.err.println("Falha na conexão com o GRID");
				e.printStackTrace();
			}
		}
        
		if (Propriedades.TIPO_EXECUCAO == TipoExecucao.NUVEM) {
			DesiredCapabilities cap = new DesiredCapabilities();
			
			   cap.setCapability("extendedDebugging", true);     
			   cap.setCapability("platformName","WIN10"); 

			switch (Propriedades.BROWSER) {
			case FIREFOX:
				cap.setBrowserName("Firefox");
				break;
			case CHROME:
				cap.setBrowserName("chrome");
				
				break;
			}

			try {

				
                 /** @Param  URL-  Deve ser o da maquina SauceLabs executando o hub na nuvem */
				String url = "https://saucelabs.com:443/wd/hub";
				driver = new RemoteWebDriver(new URL(url), cap);
                
			} catch (MalformedURLException e) {
				System.err.println("Falha na conexão com o GRID");
				e.printStackTrace();
			}
		}
		
		driver.manage().window().setSize(new Dimension(1200, 765));
		return driver;
	}

	
	/** Retorna opções de execução do Browser Chrome */
	private static ChromeOptions createChromeOptions() {
		
		/** Armazena as opções de execução do browser Chrome durante os testes */
		ChromeOptions chromeOptions = new ChromeOptions();
		
        chromeOptions.addArguments(
           // "start-maximized",
           // "enable-automation",
           // "--headless", // is not display browser while execute tests
           // "--no-sandbox", //this is the relevant other arguments came from solving other issues
           // "--disable-infobars",
           // "--disable-dev-shm-usage",
           //"--disable-browser-side-navigation",
           // "--disable-gpu",
            "--ignore-certificate-errors"
            );
		
        return chromeOptions;
	}

	/** Finaliza o driver */
	public static void killDriver() {
		WebDriver driver = getDriver();

		// se o driver exitir 
		if (driver != null) {
			driver.quit(); //fecha
			driver = null;
		}

		// se a thead gereciada do driver existir
		if (threadDriver != null) {
			threadDriver.remove(); // remove o driver da thread
		}

	}
}
