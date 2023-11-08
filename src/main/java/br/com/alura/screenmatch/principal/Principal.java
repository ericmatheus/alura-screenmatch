package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private final String ENDERECO = "http://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=bc3d9b9c";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();

    public void exibeMenu(){
        System.out.println("DIGITE O NOME DA SERIE: ");
        String nomeSerie = leitura.nextLine();
        String apiUrl = ENDERECO + nomeSerie.replace(" ", "+") + API_KEY;
        var json = consumoAPI.obterDados(apiUrl);
        DadosSerie dadosSerie = conversor.obterDados(json, DadosSerie.class);
//        System.out.println(json);
        System.out.println(dadosSerie);

        List<DadosTemporada> temporadas = new ArrayList<>();

		for (int i = 1; i < dadosSerie.totalTemporadas(); i++){
			apiUrl = ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i + API_KEY;
			json = consumoAPI.obterDados(apiUrl);
			DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
			temporadas.add(dadosTemporada);
		}
		temporadas.forEach(System.out::println); //MESMO QUE - temporadas.forEach(t -> System.out.println(t));

//        for(int i = 0; i < dadosSerie.totalTemporadas(); i++){
//            List<DadosEpisodio> episodiosTemporada = temporadas.get(i).episodios();
//
//            for(int j = 0; j < episodiosTemporada.size()-1; j++ ){
//                System.out.println(episodiosTemporada.get(j).titulo());
//            }
//        }

        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));// -> LAMBDA
        //L
        temporadas.forEach(t -> System.out.println(t));



    }




}
