package com.arthur.screenmatch;

import com.arthur.screenmatch.model.DadosSeries;
import com.arthur.screenmatch.servicis.ComsumoApi;
import com.arthur.screenmatch.servicis.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@SpringBootApplication
public class ScreenmatchApplication  implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication.run(ScreenmatchApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		var comsumoApi = new ComsumoApi();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Qual e o filme que deseja buscar?  ");
		var Input = scanner.nextLine();
		String buscaCodificada = URLEncoder.encode(Input, StandardCharsets.UTF_8);
		String endereco = "https://www.omdbapi.com/?t=" + buscaCodificada + "&apikey=e60dbe";
		var dadosDeBusca = comsumoApi.obterDados(endereco);
		System.out.println(dadosDeBusca);
		ConverteDados conversor = new ConverteDados();
		DadosSeries dados = conversor.obterDados(dadosDeBusca ,DadosSeries.class);
		System.out.println(dados);
	}
}
