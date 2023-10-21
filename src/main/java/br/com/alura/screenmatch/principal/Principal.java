package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.service.ConsumoAPI;

import java.util.Scanner;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private final String ENDERECO = "http://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=bc3d9b9c";
    private ConsumoAPI consumoAPI = new ConsumoAPI();

    public void exibeMenu(){
        System.out.println("DIGITE O NOME DA SERIE: ");
        String nomeSerie = leitura.nextLine();
        String apiUrl = ENDERECO + nomeSerie.replace(" ", "+") + API_KEY;
        var json = consumoAPI.obterDados(apiUrl);

    }

}
