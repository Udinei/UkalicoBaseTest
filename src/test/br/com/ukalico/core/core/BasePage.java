package br.com.ukalico.core.core;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static br.com.ukalico.core.core.DriverFactory.getDriver;

public class BasePage {

	public WebDriverWait wait = new WebDriverWait(getDriver(), 20);

/********* TextField e TextArea ************/
	
	public void escrever(By by, String texto) {
        //getDriver().manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		getDriver().findElement(by).clear();
		getDriver().findElement(by).sendKeys(texto);


	}

	public void escrever(String id_campo, String texto) {
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id_campo)));
		escrever(By.id(id_campo), texto);

	}
	
	public String obterValorCampo(String id_campo) {
		return getDriver().findElement(By.id(id_campo)).getAttribute("value");
	}
	
	/********* Radio e Check ************/
	
	public void clicarRadio(By by) {
		getDriver().findElement(by).click();
	}
	
	public void clicarRadio(String id) {
		clicarRadio(By.id(id));
	}
	
	public boolean isRadioMarcado(String id){
		return getDriver().findElement(By.id(id)).isSelected();
	}
	
	public void clicarCheck(String id) {
		getDriver().findElement(By.id(id)).click();
	}
	
	public boolean isCheckMarcado(String id){
		return getDriver().findElement(By.id(id)).isSelected();
	}
	
	/********* Combo ************/
	
	public void selecionarCombo(String id, String valor) {
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		combo.selectByVisibleText(valor);
	}
	
	public void deselecionarCombo(String id, String valor) {
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		combo.deselectByVisibleText(valor);
	}

	public String obterValorCombo(String id) {
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		return combo.getFirstSelectedOption().getText();
	}
	
	public List<String> obterValoresCombo(String id) {
		WebElement element = getDriver().findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		List<String> valores = new ArrayList<String>();
		for(WebElement opcao: allSelectedOptions) {
			valores.add(opcao.getText());
		}
		return valores;
	}
	
	public int obterQuantidadeOpcoesCombo(String id){
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		return options.size();
	}
	
	public boolean verificarOpcaoCombo(String id, String opcao){
		WebElement element = getDriver().findElement(By.id(id));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		for(WebElement option: options) {
			if(option.getText().equals(opcao)){
				return true;
			}
		}
		return false;
	}
	
	public void selecionarComboPrime(String radical, String valor) {
		clicarRadio(By.xpath("//*[@id='"+radical+"_input']/../..//span"));
		clicarRadio(By.xpath("//*[@id='"+radical+"_items']//li[.='"+valor+"']"));

	}

	//
	/********* Botao ************/
	
	public void clicarBotao(By by) {
		getDriver().findElement(by).click();
	}
	public void clicarBotao(String id) {
		clicarBotao(By.id(id));
	}
	
	public void clicarBotaoPorTexto(String texto){
		clicarBotao(By.xpath("//button[.='"+texto+"']"));
	}

	public String obterValueElemento(String id) {
		return getDriver().findElement(By.id(id)).getAttribute("value");
	}

	/********* Link ************/
	
	public void clicarLink(String link) {
		getDriver().findElement(By.linkText(link)).click();
	}

	public void clicarLinkPorId(String link){
	        getDriver().findElement(By.xpath("//*[@id='"+link+"']")).click();
   }

   public void clicarBotaoInput(String type, String value) {
	   getDriver().findElement(By.xpath(".//input[@type='"+type+"'][@value='"+value+"']")).click();
   }

	//   //button[@ng-if='ok_'][@type='button']

	/********* Textos ************/
	
	public String obterTexto(By by) {
		return getDriver().findElement(by).getText();
	}
	
	public String obterTexto(String id) {
		return obterTexto(By.id(id));
	}
	
	/********* Alerts ************/
	
	public String alertaObterTexto(){
		Alert alert = getDriver().switchTo().alert();
		return alert.getText();
	}
	
	public String alertaObterTextoEAceita(){
		Alert alert = getDriver().switchTo().alert();
		String valor = alert.getText();
		alert.accept();
		return valor;
		
	}
	
	public String alertaObterTextoENega(){
		Alert alert = getDriver().switchTo().alert();
		String valor = alert.getText();
		alert.dismiss();
		return valor;
		
	}
	
	public void alertaEscrever(String valor) {
		Alert alert = getDriver().switchTo().alert();
		alert.sendKeys(valor);
		alert.accept();
	}


	public void fechaAlertElementosInvalidos(){
		WebElement dialog_content = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ngdialog-content']")));
		WebElement dialog_content_button_ok = getDriver().findElement(By.xpath(".//div[@class='ngdialog-content']//div[@class='dialog-footer']//button[@ng-if='ok_']"));
		dialog_content_button_ok.click();

	}

	public void fechaAlertBoasVindas(){

		try {
			WebElement dialog_content = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='ngdialog-content']")));

			if (dialog_content.isDisplayed()) {
				Actions action = new Actions(getDriver());
				action.sendKeys(Keys.ESCAPE).build().perform();
			}
		}catch (TimeoutException e){
			System.out.println("Tela de boas vindas não habilitada! Seguindo com a execução dos testes....");
			//e.printStackTrace();
		}

	}


	/********* Frames e Janelas ************/
	
	public void entrarFrame(String id) {
		getDriver().switchTo().frame(id);
	}
	
	public void sairFrame(){
		getDriver().switchTo().defaultContent();
	}
	
	public void trocarJanela(String id) {
		getDriver().switchTo().window(id);
	}
	
	/************** JS *********************/
	
	public Object executarJS(String cmd, Object... param) {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		return js.executeScript(cmd, param);
	}
	
	/************** Tabela *********************/
	
	public WebElement obterCelula(String colunaBusca, String valor, String colunaBotao, String idTabela){
		//procurar coluna do registro
		WebElement tabela = getDriver().findElement(By.xpath("//*[@id='"+idTabela+"']"));
		int idColuna = obterIndiceColuna(colunaBusca, tabela);
		
		//encontrar a linha do registro
		int idLinha = obterIndiceLinha(valor, tabela, idColuna);
		
		//procurar coluna do botao
		int idColunaBotao = obterIndiceColuna(colunaBotao, tabela);
		
		//clicar no botao da celula encontrada
		WebElement celula = tabela.findElement(By.xpath(".//tr["+idLinha+"]/td["+idColunaBotao+"]"));
		return celula;
	}
	
	public void clicarBotaoTabela(String colunaBusca, String valor, String colunaBotao, String idTabela){
		WebElement celula = obterCelula(colunaBusca, valor, colunaBotao, idTabela);
		celula.findElement(By.xpath(".//input")).click();
		
	}

	protected int obterIndiceLinha(String valor, WebElement tabela, int idColuna) {
		List<WebElement> linhas = tabela.findElements(By.xpath("./tbody/tr/td["+idColuna+"]"));
		int idLinha = -1;
		for(int i = 0; i < linhas.size(); i++) {
			if(linhas.get(i).getText().equals(valor)) {
				idLinha = i+1;
				break;
			}
		}
		return idLinha;
	}

	protected int obterIndiceColuna(String coluna, WebElement tabela) {
		List<WebElement> colunas = tabela.findElements(By.xpath(".//th"));
		int idColuna = -1;
		for(int i = 0; i < colunas.size(); i++) {
			if(colunas.get(i).getText().equals(coluna)) {
				idColuna = i+1;
				break;
			}
		}
		return idColuna;
	}


	/***** Implementações elementos de tela MyApp  ******/

	public void buscarFuncao(String nomeFuncao){
		WebElement input = getDriver().findElement(By.xpath("//div/input[@ng-model='search']"));
		input.click();
		input.clear();
		input.sendKeys(nomeFuncao);
		input.sendKeys(Keys.ENTER);
	}


    public void clickBotaoCRUD(String nomeButton){
		//getDriver().findElement(By.xpath("//button[@class='ng-scope handlebar-button']/span[text()='"+ nomeButton +"']")).click();
		wait.withTimeout(Duration.ofSeconds(10));
		WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='ng-scope handlebar-button']//span[@class='handlebar-button-label truncate'][text()='"+ nomeButton +"']")));
		button.click();

	}

}
