package br.com.ukalico.core.core;

public class Propriedades {
	
	public static boolean FECHAR_BROWSER = true;
	
	public static Browsers BROWSER = Browsers.CHROME;
	
	/** contem o tipo de execu��o que o frameword de teste vai usar*/
	public static TipoExecucao TIPO_EXECUCAO = TipoExecucao.LOCAL;
	
	public enum Browsers {
		CHROME,
		FIREFOX
	}
	
	
	public enum TipoExecucao {
		/** usa o Selenium GRID para rodar os testes em diversas maquinas ao mesmo tempo */
		GRID, 
		/** usando a maquina local */
		LOCAL 
	}
	

}
