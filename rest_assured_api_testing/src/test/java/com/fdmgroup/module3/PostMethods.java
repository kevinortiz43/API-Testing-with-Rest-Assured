package com.fdmgroup.module3;

import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class PostMethods {

    public static void performGETPost() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:3000/posts")
                .then()
                .log().all()
                .assertThat()
                .body("author", containsInAnyOrder("typicode", "java-author", "sql-author", "selenium-author"))
                .body("author", hasSize(4))
                .header("Content-Type", is("application/json"))
                .statusCode(200);

    }

    public static void performGETWithId(String id) {
        given()
                .contentType(ContentType.JSON)
                .with()
                .pathParam("postId", id)
                .when()
                .get("http://localhost:3000/posts/{postId}")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200);

    }

    public static void performGETPostQueryParameter(String id, String title, String author) {
        var response = given()
                .contentType(ContentType.JSON)
                .with()
                .queryParam("id", id)
                .queryParam("title", title)
                .when()
                .get("http://localhost:3000/posts/")
                .then()
                .assertThat()
                .body("author", hasItem(author));

        System.out.println(response.log().body());

    }

    public static void performPOSTPost(String id, String title, String author) {

        HashMap<String, String> postContent = new HashMap<>();

        postContent.put("id", id);
        postContent.put("title", title);
        postContent.put("author", author);

        var response = given()
                .contentType(ContentType.JSON)
                .with()
                .body(postContent)
                .when()
                .post("http://localhost:3000/posts/")
                .then()
                .assertThat()
                .statusCode(201);

        System.out.println(response.log().body());

    }

    public static void performPUTPost(String id) {
        String body = """
                {
                "title": "your title",
                "author":"your author"
                }
                """;

        var response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .put("http://localhost:3000/posts/" + id)
                .then()
                .assertThat()
                .statusCode(200); // Change to 200 if expected status is 200

        System.out.println(response.log().body()); // Print the response body as a string
    }

    public static void performDeletePost(String id) {

        var response = given()
                .contentType(ContentType.JSON)

                .when()
                .delete("http://localhost:3000/posts/" + id)
                .then()
                .assertThat()
                .statusCode(200); // Change to 200 if expected status is 200

        System.out.println(response.log().body()); // Print the response body as a string
    }

}
