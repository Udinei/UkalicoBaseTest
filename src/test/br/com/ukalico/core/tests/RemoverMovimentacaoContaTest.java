package br.com.ukalico.core.tests;

import br.com.ukalico.core.core.BaseTestCore;
import br.com.ukalico.core.pages.ContasPage;
import br.com.ukalico.core.pages.MenuPage;
import org.junit.Assert;
import org.junit.Test;

public class RemoverMovimentacaoContaTest extends BaseTestCore {
	
	MenuPage menuPage = new MenuPage();
	ContasPage contasPage = new ContasPage();

	@Test
	public void testExcluirContaComMovimentacao(){
		menuPage.acessarTelaListarConta();
		
		contasPage.clicarExcluirConta("Conta com movimentacao");
		
		Assert.assertEquals("Conta em uso na movimentações", contasPage.obterMensagemErro());
	}
}
