package com.my.app.pages;

import static br.com.ukalico.core.core.DriverFactory.getDriver;

import br.com.ukalico.core.core.BasePage;
import br.com.ukalico.core.core.DriverFactory;

public class LoginPageMyApp extends BasePage {
    
    
	public void acessarHomePage(String url) {
		 DriverFactory.getDriver().get(url);
		 
	}

	public void acessarPaginaDeContato() {
       getDriver().get(getDriver().getCurrentUrl()+"/minha-conta/");
	}

	public void setUsername(String username) {
	   escrever("username", username);
	}
	
	public void setPassword(String password) {
	   escrever("password", password);
	}
	
	public void entrar() {
		clicarBotaoPorTexto("Acessar");
	}
	
	public void sair() {
		clicarLink("Sair");
	}
				
}
