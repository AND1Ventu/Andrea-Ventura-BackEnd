import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.nio.charset.StandardCharsets;

public class KaraokeSyncExample {
    private static final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private static int currentLyricsIndex = 0;

    public static void main(String[] args) {
        try {
            String artist = "John Lennon";
            String title = "Imagine";
            String lyricsInfo = getLyricsInfo(artist, title);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(lyricsInfo);
            JsonNode lyricsNode = rootNode.get("lyrics");

            String lyrics = lyricsNode.asText();
            String[] lyricLines = lyrics.split("\n");

            JsonNode timingsNode = rootNode.get("timings");
            int[] timings = (timingsNode != null) ? objectMapper.treeToValue(timingsNode, int[].class) : new int[0];


            startKaraoke(lyricLines, timings);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getLyricsInfo(String artist, String title) throws Exception {
        String apiUrl = "https://api.lyrics.ovh/v1/" +
                URLEncoder.encode(artist, StandardCharsets.UTF_8) + "/" +
                URLEncoder.encode(title, StandardCharsets.UTF_8);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return response.body();
        } else {
            throw new RuntimeException("Errore nella chiamata API: " + response.statusCode());
        }
    }

    private static void startKaraoke(String[] lyricLines, int[] timings) {
        scheduler.scheduleAtFixedRate(() -> {
            // Pulisci la console prima di visualizzare la nuova riga
            clearConsole();

            // Visualizza il testo corrente
            System.out.println(lyricLines[currentLyricsIndex]);

            // Passa alla riga successiva del testo
            currentLyricsIndex += 1;

            // Se la canzone è finita, chiudi l'executor
            if (currentLyricsIndex >= lyricLines.length) {
                scheduler.shutdown();
            }
        }, 0, timings[currentLyricsIndex], TimeUnit.MILLISECONDS);
    }

    private static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
