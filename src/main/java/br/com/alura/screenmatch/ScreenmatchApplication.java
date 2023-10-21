package br.com.alura.screenmatch;

import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.alura.screenmatch.principal.Principal;
import java.util.ArrayList;
import java.util.List;

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
		System.out.println("=========================================");
		apiUrl = "http://www.omdbapi.com/?t=gilmore+girls&season=1&episode=1&apikey=bc3d9b9c";
		json = consumoAPI.obterDados(apiUrl);
		DadosEpisodio dadosEpisodio = conversor.obterDados(json, DadosEpisodio.class);
		System.out.println(dadosEpisodio);
		System.out.println("=========================================");

		List<DadosTemporada> temporadas = new ArrayList<>();

		for (int i = 1; i < dadosSerie.totalTemporadas(); i++){
			apiUrl = "http://www.omdbapi.com/?t=gilmore+girls&season="+i+"&apikey=bc3d9b9c";
			json = consumoAPI.obterDados(apiUrl);
			DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);
		}

		temporadas.forEach(System.out::println);
		Principal principal = new Principal();
		principal.exibeMenu();
	}
}
