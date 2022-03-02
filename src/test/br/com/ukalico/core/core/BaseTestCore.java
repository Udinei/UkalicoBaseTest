package br.com.ukalico.core.core;

import static br.com.ukalico.core.core.DriverFactory.getDriver;
import static br.com.ukalico.core.core.DriverFactory.killDriver;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

/**
 * @author Udinei Silva
 * Classe que fornece acesso a pagina de login para todos os testes controlados pelo core framework 
 **/
public class BaseTestCore extends BaseTest {


    @Override
    @Before
    public void inicializa(){
        /**Endereco do sistema a ser testado */
    	loginPage.acessarHomePage("https://seubarriga.wcaquino.me");

    	loginPage.setEmail("udineisilva@gmail.com");
    	loginPage.setSenha("123456");
    	loginPage.entrar();
    }

    @Override
    @After
    public void finaliza() throws IOException {
        TakesScreenshot ss = (TakesScreenshot) getDriver();
        File arquivo = ss.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(arquivo, new File("target" + File.separator + "screenshot" +
                File.separator + testName.getMethodName() + ".jpg"));

        if(Propriedades.FECHAR_BROWSER) {
            killDriver();
        }
    }
}
