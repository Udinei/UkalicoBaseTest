package br.com.ukalico.core.tests;

import br.com.ukalico.core.core.BaseTestCore;
import br.com.ukalico.core.pages.MenuPage;
import br.com.ukalico.core.pages.MovimentacaoPage;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static br.com.ukalico.core.utils.DataUtils.obterDataFormatada;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MovimentacaoTest extends BaseTestCore {
	private MenuPage menuPage = new MenuPage();
	private MovimentacaoPage movPage = new MovimentacaoPage();

	LocalDate hoje = LocalDate.now();
	
	@Test
	public void test1InserirMovimentacao() throws InterruptedException{
		menuPage.acessarTelaInserirMovimentacao();
		movPage.setDataMovimentacao(obterDataFormatada(hoje));
		movPage.setDataPagamento(obterDataFormatada(hoje));
		movPage.setDescricao("Movimentação do Teste");
		movPage.setInteressado("Interessado Qualquer");
		movPage.setValor("500");
		movPage.setConta("Conta para movimentacoes");
		movPage.setStatusPago();
		movPage.salvar();
		
		Thread.sleep(8000);
		Assert.assertEquals("Movimentação adicionada com sucesso!", movPage.obterMensagemSucesso());
	}
	
	@Test
	public void test2CamposObrigatorios(){
		menuPage.acessarTelaInserirMovimentacao();
		
		movPage.salvar();
		List<String> erros = movPage.obterErros();
//		Assert.assertEquals("Data da Movimenta��o � obrigat�rio", erros.get(0));
//		Assert.assertTrue(erros.contains("Data da Movimenta��o � obrigat�rio"));
		Assert.assertTrue(erros.containsAll(Arrays.asList(
				"Data da Movimentação é obrigatório", "Data do pagamento é obrigatório",
				"Descrição é obrigatório", "Interessado é obrigatório",
				"Valor é obrigatório", "Valor deve ser um número")));
		Assert.assertEquals(6, erros.size());
	}
	
	@Test
	public void test3InserirMovimentacaoFutura(){
		menuPage.acessarTelaInserirMovimentacao();
		
		//Date dataFutura = DataUtils.obterDataComDiferencaDias(5);
		LocalDate dataFutura = LocalDate.now().plusDays(5);
		
		movPage.setDataMovimentacao(obterDataFormatada(dataFutura));
		movPage.setDataPagamento(obterDataFormatada(dataFutura));
		movPage.setDescricao("Movimentação do Teste");
		movPage.setInteressado("Interessado Qualquer");
		movPage.setValor("500");
		movPage.setConta("Conta para movimentacoes");
		movPage.setStatusPago();
		movPage.salvar();
		
		List<String> erros = movPage.obterErros();
		Assert.assertTrue(
				erros.contains("Data da Movimentação deve ser menor ou igual à data atual"));
		Assert.assertEquals(1, erros.size());
	}
}
