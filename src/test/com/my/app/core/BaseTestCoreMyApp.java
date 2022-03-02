package com.my.app.core;

import static br.com.ukalico.core.core.DriverFactory.getDriver;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import br.com.ukalico.core.core.BaseTest;
import br.com.ukalico.core.core.Propriedades;

/**
 * @author Udinei Silva
 * Classe ponto de entrada da aplicação, para todas as outras 
 * classes de testes do framework que necessitam de Login e Finalização do teste,
 * exceto a classe LoginMyAppTest.java que e o teste de login da aplicação
 *  
 * */
public class BaseTestCoreMyApp extends BaseTest {


    @Override
    @Before
    public void inicializa(){
        /** Endereco da aplicação a ser testada */
        loginPageMyApp.acessarHomePage("https://livros.inoveteste.com.br/");
        //	https://www.amcom.com.br/
              
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
}
