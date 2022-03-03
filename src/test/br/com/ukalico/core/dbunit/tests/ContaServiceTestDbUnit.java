package br.com.ukalico.core.dbunit.tests;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import br.ce.wcaquino.entidades.Conta;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.service.ContaService;
import br.ce.wcaquino.service.UsuarioService;
import br.com.ukalico.core.dbunit.utils.ImportExport;

/** Nessa Classe esta implementada os testes, usando as entidades e servicos de um projeto externo ao framework
 *  
 *  ERROS:Caso haja conflitos de libs ao executar os testes, deve ser incluido no Javabuild Path do projeto de testes
*   projeto que esta sendo testado, Inclua somente as pastas de Entidades e Servicos do projeto.
*     
 *  Notas:
 *  Caso o projeto testado ja use o Selenium o mesmo deve usar a mesma versão do chrome driver do framework 
 * 
 * */

public class ContaServiceTestDbUnit {
	/** PATH_MASSA_PROJECT - create path to massa for project, Windows or Linux 
	 *  put file testDbUnit project.dbunit.massa at UkalicoBaseTest project
	 * */
	public static final Path PATH_MASSA_PROJECT = Paths.get(System.getProperty("user.dir")
			                                            .toString(),"src","test","br","com","ukalico","core","dbunit","massa");
	
	
	ContaService service = new ContaService();
	UsuarioService userService = new UsuarioService();
	
	@BeforeClass
	public static void inserirConta() throws Exception {
		ImportExport.importarBanco("est5.xml", PATH_MASSA_PROJECT);
	}

	@Test
	public void testInserir() throws Exception {
		Usuario usuario = userService.findById(1L);
		Conta conta = new Conta("Conta salva", usuario);
		Conta contaSalva = service.salvar(conta);
		Assert.assertNotNull(contaSalva.getId());
		userService.printAll();
		service.printAll();
	}

	@Test
	public void testAlterar() throws Exception {
		Conta contaTeste = service.findByName("Conta CT005 alteracao");
		contaTeste.setNome("Conta alterada");
		Conta contaAlterada = service.salvar(contaTeste);
		Assert.assertEquals("Conta alterada", contaAlterada.getNome());
		service.printAll();
	}
	
	@Test
	public void testConsultar() throws Exception {
		Conta contaBuscada = service.findById(1L);
		Assert.assertEquals("Conta para testes", contaBuscada.getNome());
	}
	
	@Test
	public void testExcluir() throws Exception {
		Conta contaTeste = service.findByName("Conta para deletar");
		service.printAll();
		service.delete(contaTeste);
		Conta contaBuscada = service.findById(contaTeste.getId());
		Assert.assertNull(contaBuscada);
		service.printAll();
	}
}
