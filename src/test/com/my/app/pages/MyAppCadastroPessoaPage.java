package com.my.app.pages;

import br.com.ukalico.core.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class MyAppCadastroPessoaPage extends BasePage {


   public void pesquisarFuncao(){
        buscarFuncao("Cadastro Completo de Pessoas");
    }

   public void clicarBotaoAdicionar(){
       clickBotaoCRUD("Adicionar");
   }

    public void clickNomeDaFuncao(){
       wait.withTimeout(Duration.ofSeconds(10));
        WebElement linkFunction = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='w-feature-app']/span[text()='Cadastro Completo de Pessoas']")));
        linkFunction.click();

    }


}
