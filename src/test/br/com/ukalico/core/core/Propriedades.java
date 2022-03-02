package br.com.ukalico.core.core;
/**
 * @author Udinei
 * @since 21-12-2021
 * <p>Classe de configuração da execução do framework durante os testes.
 * <p>- Browser e Drivers de execução:Chrome, Firefox e Internet explorer  
 * <p>- Status do Browser apos execução: Fechado=true, Aberto=false 
 * <p>- Tipo de do Browser execução: Local, Grid, Nuvem 
 *  
 */
public class Propriedades {
	
	/**
	 * FECHAR_BROWSER = true - Execução oficial dos testes
	 * FECHAR_BROWSER = false - Em desenvolvimento dos testes
	 */
	public static boolean FECHAR_BROWSER = true;
	
	/** Trata chaveamento do browser a ser utilizado nos testes */
	public static Browsers BROWSER = Browsers.CHROME;
	
	/** Contem o tipo de execucao que o frameword de teste vai usar */
	public static TipoExecucao TIPO_EXECUCAO = TipoExecucao.LOCAL;
	
	
	public enum Browsers {
		CHROME("chromedriver.exe"),
		FIREFOX("GeckoDriver.exe"),
		IE("IEDriverServer.exe");
		
		private String descricao;
		
		Browsers(String descricao){
			this.descricao = descricao;
		}
		
		public String getDescricao() {
			return descricao;
		}
	}
	
   /**  
    * TipoExecucao - Enum dos Tipos de execução do framework
    * <p>GRID  - Usa o Selenium GRID para rodar os testes em diversas maquinas ao mesmo tempo
	* <p>LOCAL - Executa os testes na maquina local
	* <p>NUVEM - Executa os testes na Web nos servidores da SauceLabs 
	* 
	*/
	public enum TipoExecucao {
		GRID, 
		LOCAL,
		NUVEM
	}
	

}
