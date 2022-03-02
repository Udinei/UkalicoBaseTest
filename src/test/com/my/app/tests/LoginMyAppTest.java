package com.my.app.tests;

import static br.com.ukalico.core.core.DriverFactory.getDriver;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.my.app.core.MenuPageMyApp;
import com.my.app.pages.LoginPageMyApp;

import br.com.ukalico.core.core.BaseTest;
import br.com.ukalico.core.core.Propriedades;

/**
 * @author Udinei Silva 
 * Classe responsavel por executar os testes de login, portanto nao extende BaseTestCoreMyApp
 * estende diretamente BaseTest
 *  
 *  */
public class LoginMyAppTest extends BaseTest {

	
	MenuPageMyApp menuPageMyApp = new MenuPageMyApp();
	LoginPageMyApp loginPageMyApp = new LoginPageMyApp();
	
    @Override
	@Before
	public void inicializa() {
		/** Endereco da aplicação a ser testada */
    	loginPageMyApp.acessarHomePage("https://livros.inoveteste.com.br/");
                   
      	loginPageMyApp.acessarPaginaDeContato();
      	loginPageMyApp.setUsername("udineisilva@gmail.com");
      	loginPageMyApp.setPassword("Livsol519*");
      	loginPageMyApp.entrar();
		
	}

	 
	@Override
	@After
	public void finaliza() throws IOException {
		 TakesScreenshot ss = (TakesScreenshot) getDriver();
	        File arquivo = ss.getScreenshotAs(OutputType.FILE);
	        FileUtils.copyFile(arquivo, new File("target" + File.separator + "screenshot" +
	                File.separator + testName.getMethodName() + ".jpg"));

	        if(Propriedades.FECHAR_BROWSER) {
	         //  killDriver();
	        }
		
	}
	
	@Test
	public void sairDoSistema() {
		loginPageMyApp.sair();
		
	}

}
