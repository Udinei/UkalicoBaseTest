package br.com.ukalico.core.pages;

import br.com.ukalico.core.core.BasePage;
import br.com.ukalico.core.core.DriverFactory;

public class LoginPage extends BasePage {
	
	public void acessarHomePage(String url){
		DriverFactory.getDriver().get(url);
	}
	
	public void setEmail(String email) {
		escrever("email", email);
	}
	
	public void setSenha(String senha) {
		escrever("senha", senha);
	}
	
	public void entrar(){
		clicarBotaoPorTexto("Entrar");
	}


	public void logar(String email, String senha) {
		setEmail(email);
		setSenha(senha);
		entrar();
	}
    
	public void resetar() {
		clicarLink("reset");
	}

}
