package br.com.alura.screenmatch;

import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {

		var consumoAPI = new ConsumoAPI();

		String apiUrl = "http://www.omdbapi.com/?t=gilmore+girls&apikey=bc3d9b9c";
		var json = consumoAPI.obterDados(apiUrl);
		System.out.println("=========================================");
		System.out.println(json);
		ConverteDados conversor;
		conversor = new ConverteDados();
		System.out.println("=========================================");
		DadosSerie dadosSerie = conversor.obterDados(json, DadosSerie.class);
		System.out.println(dadosSerie);
	}
}
