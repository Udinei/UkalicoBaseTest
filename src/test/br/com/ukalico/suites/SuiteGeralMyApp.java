package br.com.ukalico.suites;

import com.my.app.pages.LoginPageMyApp;
import com.my.app.tests.TestCoreMyApp;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	     // Inserir classes da suite de testes do MyApp aqui
		TestCoreMyApp.class

})
public class SuiteGeralMyApp {
	
	private static LoginPageMyApp page = new LoginPageMyApp();


	
	/** Usar metodo reset() para resetar BD via aplicação ou script SQL*/
	@BeforeClass
	public static void reset() {

		//page.resetar();

		// fecha janela do browser apos executar os testes
		//DriverFactory.killDriver();
		
		
	}

}
