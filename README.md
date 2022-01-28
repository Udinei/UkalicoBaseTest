# UkalikoBaseTest
Repositorio do framework de testes UkalicoBaseTest. Sendo o Framework a abstração que serve de estrutura para o projeto visando reuso e padronização, focado na solução dos 3 maiores motivos de problemas nos testes funcionais: Localização de elementos, Sincronismo dos scripts e Massa de dados.

### O framework possui as seguintes vantagens nativas:
- Driver centralizado
- Herança de compotamentos
- Reuso do browser
- Scheenshot ao final dos testes
- Chaveamento para outros browsers
- PADRONIZAÇÃO de projeto de longo prazo
- DSL herdada nos testes
- Organização dos testes com PageObject
- Execução em GRID
- Execução na Nuvem

  

# Arquitetura framework UkBaseTest

![](img/arquitetura_C4.png)

# Stack
- Java 1.8
- Selenium-java 4.1.0
- Selenium-server-standalone 3.5.3
- Junit 4.13.2
- Maven-surefire-plugin 2.18.1
- Selenium-chrome-driver 4.1.0
- Commons-io 2.11.0
- Apache Maven 3.8.3
- PlantUML 
	
	
# Estrutura do projeto

```
UkalicoBaseTest
|_arq 
|_img
|_src
|   |_test
|      |_core
|      |  |_core
|      |  |_pages
|      |  |_testes
|      |  |_utils
|      |_suites
|      |_myapp
|      |  |_core
|      |  |_pages
|      |  |_tests
|      |_resources
|	    |_driver
|	    |_grid
|  
|_target
|   |_screanshot	 
|   
|pom.xml
|README.md   
|.gitignore
```

# Referências
* [Curso de Testes funcionais com Selenium WebDriver: Do básico ao GRID : Por Francisco Wagner Costa Aquino](https://www.udemy.com/course/testes-funcionais-com-selenium-webdriver/)
* [Selenium automates browsers](https://www.selenium.dev/)
* [DSL - Linguagens específicas de domínio](https://www.jetbrains.com/pt-br/mps/concepts/domain-specific-languages/)
* [XPath, CSS, DOM and Selenium: The Rosetta Stone](https://www.red-gate.com/simple-talk/development/dotnet-development/xpath-css-dom-and-selenium-the-rosetta-stone/)
