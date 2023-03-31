package Task_2;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Scanner;

public class Task_2 {
    private static final String RELATIVE_PATH = "src/main/java/Task_2";
    private static final Gson GSON = new Gson().newBuilder().setPrettyPrinting().create();
    private static final String POST_URL = "https://jsonplaceholder.typicode.com/users/";
    private static final String POST = "/posts";
    private static final String COMMENT_URL = "https://jsonplaceholder.typicode.com/posts/";
    private static final String COMMENT = "/comments";

    public static void main(String[] args) {

        System.out.println("Pleas enter a user ID to search for comments");
        int searchUserID = new Scanner(System.in).nextInt();

        List<Post> listPosts = getPosts(POST_URL + searchUserID + POST);

         Comments[] comments = getComment(COMMENT_URL + listPosts.size() + COMMENT);

         createFile (listPosts, comments, searchUserID);
    }

    private static void createFile(List<Post> listPosts,Comments[] comments, int searchUserID) {
        if (comments.length == 0) {
            System.out.println("There are no comments for this ID");
        } else {
            Gson gson = new Gson().newBuilder().create();
            String json = gson.toJson(comments);

            File file = new File(RELATIVE_PATH, String.format("user-%d-post-%d-comments.json",searchUserID, listPosts.size()));

            if (!file.exists()) {
                try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
                    bufferedWriter.write(json);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
    public static List<Post> getPosts(String POST_URL) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(POST_URL))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return GSON.fromJson(response.body(), new TypeToken<List<Post>>() {
            }.getType() );

        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static Comments[] getComment(String COMMENT_URL) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(COMMENT_URL))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return GSON.fromJson(response.body(), Comments[].class);

        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
