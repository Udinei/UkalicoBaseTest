package com.my.app.core;

import br.com.ukalico.core.core.BaseTest;
import br.com.ukalico.core.core.Propriedades;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

import static br.com.ukalico.core.core.DriverFactory.getDriver;
import static br.com.ukalico.core.core.DriverFactory.killDriver;

public class BaseTestMyApp extends BaseTest {


    @Override
    @Before
    public void inicializa(){
        /** Endereco do sistema a ser acessado */
        pageMyApp.acessarTelaInicial("https://myapp.com.br/");
        pageMyApp.autorizar();
        pageMyApp.processaAutorizacao();

        pageMyApp.setLoginUsername("usilva");
        pageMyApp.setLoginPassword("myapp3001");
        pageMyApp.loginDatabase("BDMYAPP");
        pageMyApp.entrar();

        pageMyApp.fechaAlertElementosInvalidos();
        pageMyApp.fechaAlertBoasVindas();
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
