import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        String uri = "https://www.omdbapi.com/";
        String apiKey = "fddc0e0e";
        List<Movie> movies = new ArrayList<>();
        boolean i = true;

        while(i == true) { //.equalsIgnoreCase() se usa para ignorar o upper case de uma palavra para a validação
            System.out.println("\nEscreva um novo título de filme para se ter as informações dele ou feche o programa escrevendo 'sair'");
            String surch = scanner.nextLine();
            if(surch.equalsIgnoreCase("sair")) {
                break;
            }

            try {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(uri + "?t=" + surch.replace(" ", "+") + "&apikey=" + apiKey)) //.replace substitui o espaço de uma uri pelo sinal de + para juntar as palavras dgitadas pelo usuário
                        .build();
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());

                String json = response.body();
                System.out.println(json);
                Gson gson = new GsonBuilder()
                        .setFieldNamingPolicy(FieldNamingPolicy. UPPER_CAMEL_CASE) //"Muda o nome da variável sem alterar ela no código fonte" para a primeira letra maiúscula para receber o valor vindo do json
                        .create();
                //Movie movie = gson.fromJson(json, Movie.class);
                OmdbInfosTitles omdbInfos = gson.fromJson(json, OmdbInfosTitles.class);
                Movie movie = new Movie(omdbInfos);
                movies.add(movie);
                System.out.println("\nFilme covertido");
                System.out.println(movie);
            } catch(Exception ex) {
                System.out.println("Aconteceu um erro: " + ex.getMessage());
            }
            System.out.println(movies);
            System.out.println("Finalizou");
        }
    }
}