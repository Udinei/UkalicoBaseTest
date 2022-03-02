package br.com.ukalico.core.core;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;

import com.my.app.pages.LoginPageMyApp;

import br.com.ukalico.core.pages.LoginPage;
/**
 * @author Udinei Silva
 * <p> Classe de definição das variaveis e metodos de login, inicio e fim  dos testes
 *  
 */
public abstract class BaseTest {

	/** testName - Armazena o nome do teste */
    @Rule
    public TestName testName = new TestName(); 
	
    /** Classe de acesso ao elementos da pagina de login do core do framework */
    protected LoginPage loginPage = new LoginPage(); 

	/** Classe de acesso ao elementos da pagina de login da MyApp */
    protected  LoginPageMyApp loginPageMyApp = new LoginPageMyApp(); 

	/** Inicializa o teste com acesso a pagina de login da aplicação e outras ações iniciais */
	@Before
    protected abstract void inicializa(); 
	
	/** Finaliza execução dos testes e registra um print da tela de execução do teste e outras ações finais*/
	@After
	protected abstract void finaliza() throws IOException;

}
