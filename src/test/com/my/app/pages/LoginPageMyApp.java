package com.my.app.pages;

import br.com.ukalico.core.core.BasePage;
import br.com.ukalico.core.core.DriverFactory;

public class LoginPageMyApp extends BasePage {

	public void acessarTelaInicial(String url){ DriverFactory.getDriver().get(url); 	}

	public void setLoginUsername(String username){ escrever("loginUsername", username); }

	public void setLoginPassword(String password){ escrever("loginPassword", password); }

	public void entrar(){ clicarBotaoInput("submit","Entrar");	}

	public void autorizar(){ clicarBotao("details-button");	}

	public void processaAutorizacao(){clicarLinkPorId("proceed-link"); }

	public void loginDatabase(String dataBase) {selecionarCombo("loginDatabase", dataBase);	}



/*
	public void logar(String username, String password,String dataBase ) {
		setLoginUsername(username);
        setLoginPassword(password);
	    entrar();
		autorizar();
		processaAutorizacao();
		loginDatabase(dataBase);
	}*/

	public void resetar() {
		clicarLink("reset");
	}

}
