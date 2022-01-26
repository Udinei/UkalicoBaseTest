package br.com.ukalico.core.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataUtils {
	
	public static String obterDataFormatada(LocalDate data) {
		DateTimeFormatter formatadorBarra = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatadorBarra);
	}

}
