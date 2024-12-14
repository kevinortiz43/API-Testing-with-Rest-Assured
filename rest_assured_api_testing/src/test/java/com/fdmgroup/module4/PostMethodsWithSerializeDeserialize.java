package com.fdmgroup.module4;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fdmgroup.pojos.Post;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PostMethodsWithSerializeDeserialize {

    public static void performGETPost() {
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("http://localhost:3000/posts")
                .then()
                .log().all()
                .assertThat()
                .body("author", containsInAnyOrder("typicode", "java-author", "sql-author", "selenium-author"))
                .body("author", hasSize(4))
                .header("Content-Type", is("application/json"))
                .statusCode(200)
                .extract().response();

        // Deserialize JSON response to List of Post objects
        List<Post> posts = response.jsonPath().getList("", Post.class);

        assertThat(posts, hasSize(4));

        List<String> authors = new ArrayList<>();
        for (Post post : posts) {
            authors.add(post.getAuthor());
        }

        assertThat(authors, containsInAnyOrder("typicode", "java-author", "sql-author", "selenium-author"));

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
        Post[] posts = given()
                .contentType(ContentType.JSON)
                .with()
                .queryParam("id", id)
                .queryParam("title", title)
                .when()
                .get("http://localhost:3000/posts/")
                .then()
                .assertThat()
                .body("author", hasItem(author))
                .extract().as(Post[].class);

                assertThat(posts.length,is(1));

                List<String> authors = new ArrayList<>();

                for(Post post:posts){
                    authors.add(post.getAuthor());
                }

                assertThat(authors, hasItem(author));


    }

    public static void performGETPostPathParameter(String id, String author) {
        Post post = given()
                .contentType(ContentType.JSON)
                .with()
                .pathParam("postId", id)
                .when()
                .get("http://localhost:3000/posts/{postId}")
                .then()
                .assertThat()
                .body("author", containsString(author))

                .extract().as(Post.class);

                //Perform assertion on the deserialized JSON object
                assertThat(post.getAuthor(), is(author));

    }

    // public static void performPOSTPost(String id, String title, String author) {

    //     HashMap<String, String> postContent = new HashMap<>();

    //     postContent.put("id", id);
    //     postContent.put("title", title);
    //     postContent.put("author", author);

    //     var response = given()
    //             .contentType(ContentType.JSON)
    //             .with()
    //             .body(postContent)
    //             .when()
    //             .post("http://localhost:3000/posts/")
    //             .then()
    //             .assertThat()
    //             .statusCode(201);

    //     System.out.println(response.log().body());

    // }

    
    public static void performPOSTPost(String id, String title, String author) {

        Post postContent = new Post(id, title, author);



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

        
        Post postContent = new Post(id, "your title","your author");

        var response = given()
                .contentType(ContentType.JSON)
                .body(postContent)
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
