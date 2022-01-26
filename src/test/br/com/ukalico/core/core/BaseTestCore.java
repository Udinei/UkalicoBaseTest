package br.com.ukalico.core.core;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

import static br.com.ukalico.core.core.DriverFactory.getDriver;
import static br.com.ukalico.core.core.DriverFactory.killDriver;

public class BaseTestCore extends BaseTest {


    @Override
    @Before
    public void inicializa(){
        /**Endereco do sistema a ser testado */
        page.acessarTelaInicial("https://seubarriga.wcaquino.me");

        page.setEmail("udineisilva@gmail.com");
        page.setSenha("123456");
        page.entrar();
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
