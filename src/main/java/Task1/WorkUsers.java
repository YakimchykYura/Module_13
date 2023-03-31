package Task1;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class WorkUsers {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/users/";
    private static final String USERNAME_URL = "https://jsonplaceholder.typicode.com/users?username=";
    private static Gson GSON;
    public static void main(String[] args) {
        GSON = new Gson().newBuilder().setPrettyPrinting().create();
        User user = User.builder()
                .id(10)
                .name("Yura")
                .username("Java Developer")
                .email("yura@march.mus")
                .address(Address.builder()
                        .street("Шевченко")
                        .suite("Ap.24")
                        .city("Житомир")
                        .zipcode("123456789")
                        .geo((Geo.builder()
                                .lat("34.980")
                                .lnd("45.123")
                                .build()))
                        .build())
                .phone("034 285 981")
                .website("idi.cuda")
                .company(Company.builder()
                        .name("Made in Ukrain")
                        .catchPhrase("We work until two")
                        .bs("34")
                        .build())
                .build();

        User updateUser = User.builder()
                .id(10)
                .name("Max")
                .username("Developer")
                .email("yura@march.mus")
                .address(Address.builder()
                        .street("Шевченко")
                        .suite("Ap.24")
                        .city("Житомир")
                        .zipcode("123456789")
                        .geo((Geo.builder()
                                .lat("34.980")
                                .lnd("45.123")
                                .build()))
                        .build())
                .phone("034 285 981")
                .website("idi.cuda")
                .company(Company.builder()
                        .name("Made in Ukrain")
                        .catchPhrase("We work until two")
                        .bs("34")
                        .build())
                .build();

//        createUser(user);
//        updateUser(updateUser);
//        deleteUser(10);
//        getUsers(BASE_URL);
//        readUserForId(10);
//        readUserForId(10);
//        readUserForUsername("Bret");
    }

    public static User createUser(User user) {
        String userJson = GSON.toJson(user);

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(BASE_URL + user))
                    .POST(HttpRequest.BodyPublishers.ofString(userJson))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return GSON.fromJson(response.body(), User.class);

        } catch (Exception ex) {
            ex.printStackTrace();
            return new User();
        }
    }

    public static User updateUser(User user) {
        String userJson = GSON.toJson(user);

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(BASE_URL))
                    .POST(HttpRequest.BodyPublishers.ofString(userJson))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            User finalUser = GSON.fromJson(response.body(), User.class);
            System.out.println("Update status code " + response.statusCode());
            return finalUser;

        } catch (Exception ex) {
            ex.printStackTrace();
            return new User();
        }
    }
    public static void deleteUser(int id) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(BASE_URL + id))
                    .DELETE()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Delete status code " + response.statusCode());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static List<User> getUsers(String BASE_URL) {

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(BASE_URL))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return GSON.fromJson(response.body(), new TypeToken<List<User>>() {
            }.getType());

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public static User readUserForId(int id) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(BASE_URL + id))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return GSON.fromJson(response.body(), User.class);

        } catch (Exception ex) {
            ex.printStackTrace();
            return new User();
        }
    }

    public static User[] readUserForUsername(String username) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(USERNAME_URL + username))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return GSON.fromJson(response.body(), User[].class);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}