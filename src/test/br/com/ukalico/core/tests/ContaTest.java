package br.com.ukalico.core.tests;

import br.com.ukalico.core.core.BaseTestCore;
import br.com.ukalico.core.pages.ContasPage;
import br.com.ukalico.core.pages.MenuPage;
import org.junit.Assert;
import org.junit.Test;


public class ContaTest extends BaseTestCore {
	
	@Test
	public void test() {
		
	}
	
	MenuPage menuPage = new MenuPage();
	ContasPage contasPage = new ContasPage();
	
	@Test
	public void test1_InserirConta() throws InterruptedException{
		menuPage.acessarTelaInserirConta();
		
		contasPage.setNome("Conta do Teste");
		contasPage.salvar();
		
		Thread.sleep(8000);
		Assert.assertEquals("Conta adicionada com sucesso!", contasPage.obterMensagemSucesso());
	}
	
	@Test
	public void test2_AlterarConta(){
		menuPage.acessarTelaListarConta();
		
		contasPage.clicarAlterarConta("Conta para alterar");
		
		contasPage.setNome("Conta alterada");
		contasPage.salvar();
		
		Assert.assertEquals("Conta alterada com sucesso!", contasPage.obterMensagemSucesso());
	}
	
	@Test
	public void test3_InserirContaMesmoNome(){
		menuPage.acessarTelaInserirConta();
		
		contasPage.setNome("Conta mesmo nome");
		contasPage.salvar();
		
		Assert.assertEquals("Já existe uma conta com esse nome!", contasPage.obterMensagemErro());
	}
}
