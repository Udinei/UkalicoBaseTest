package com.my.app.tests;

import com.my.app.core.BaseTestCoreMyApp;
import com.my.app.core.MenuPageMyApp;
import com.my.app.pages.MyAppCadastroPessoaPage;

import org.junit.Test;

public class MyAppCadastroPessoaTest extends BaseTestCoreMyApp {

    MenuPageMyApp menuPageMyApp = new MenuPageMyApp();
    MyAppCadastroPessoaPage cadCompletoPessoas = new MyAppCadastroPessoaPage();


    @Test
    public void inserirPessoa() {
        menuPageMyApp.acessarHomePage("url");
        cadCompletoPessoas.pesquisarFuncao();
        cadCompletoPessoas.clickNomeDaFuncao();
        cadCompletoPessoas.clicarBotaoAdicionar();
    }


}