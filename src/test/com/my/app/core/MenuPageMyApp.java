package com.my.app.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import br.com.ukalico.core.core.BasePage;
import br.com.ukalico.core.core.DriverFactory;

/** 
 * Colocar nessa classe os metodos de acesso aos menus e telas iniciais das funcoes do sistema
 * ou metodos de ações que devem ser executadas após fazer o login 
 * 
 */
public class MenuPageMyApp extends BasePage {

   
	public void acessarHomePage(String url) {
		DriverFactory.getDriver().get(url);
	}
	
	 public void acessarOpcaoMinhaConta(){
		 WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='https://livros.inoveteste.com.br/minha-conta/'])")));
         element.click();		
	 }

	

    /*
	public void acessarTelaInserirPessoa(){
		//clicarLink("Contas");
		//clicarLink("Listar");
	}
	
	public void acessarTelaInserirMovimentacao(){
		clicarLink("Criar Movimentação");
	}
	
	public void acessarTelaResumo(){
		clicarLink("Resumo Mensal");
	}

	public void acessarFuncao(){

	}*/

}
