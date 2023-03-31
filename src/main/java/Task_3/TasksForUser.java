package Task_3;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Scanner;

public class TasksForUser {
    private static final String USER_URI = "https://jsonplaceholder.typicode.com/users/";
    private static final Gson GSON = new Gson().newBuilder().setPrettyPrinting().create();

    public static void main(String[] args) {
        System.out.println("Pleas enter the user ID");
        int userID = new Scanner(System.in).nextInt();
        List<Tasks> tasks = getTasks(USER_URI + userID + "/todos?completed=false");

        if (tasks.isEmpty()) {
            System.out.println("There are no tasks");
        } else {
            for (Tasks task : tasks) {
                String result = GSON.toJson(task);
                System.out.println(result);
            }
        }

    }

    public static List<Tasks> getTasks(String USER_URI) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(USER_URI))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return GSON.fromJson(response.body(), new TypeToken<List<Tasks>>() {
            }.getType());
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
