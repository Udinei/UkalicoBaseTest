package com.my.app.core;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.my.app.pages.LoginPageMyApp;

@RunWith(Suite.class)
@SuiteClasses({
	     // Inserir classes da suite de testes do MyApp aqui
		//TestCoreMyApp.class

})
public class SuiteGeralMyApp {
	
	private static LoginPageMyApp page = new LoginPageMyApp();


	
	/** Usar metodo resetDB() para resetar BD via aplicação ou script SQL*/
	@BeforeClass
	public static void resetDB() {

		//loginPage.resetar();

		// fecha janela do browser apos executar os testes
		//DriverFactory.killDriver();
		
		
	}

}
