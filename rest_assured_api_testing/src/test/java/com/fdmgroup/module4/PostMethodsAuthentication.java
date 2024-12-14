package com.fdmgroup.module4;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fdmgroup.pojos.Post;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PostMethodsAuthentication {

    private static String access_token = "";

    @BeforeClass
    public void authentication() {
        String credential = """
                {
                    "email": "bruno@email.com",
                    "password": "bruno"
                }
                """;

        // extract token
        access_token = given()
                .contentType(ContentType.JSON)
                .with()
                .body(credential)
                .when()
                .post("http://localhost:3000/auth/login")
                .then()
                .extract().path("access_token");

        System.out.println("Token: " + access_token);
    }

    @Test
    public void performGetPost() {
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + access_token)
                .log().all()
                .when()
                .get("http://localhost:3000/posts")
                .then()
                .log().all()
                .assertThat()
                .body("author", containsInAnyOrder("typicode", "java-author", "sql-author", "selenium-author"))
                .body("author", hasSize(4))
                .header("Content-Type", is("application/json; charset=utf-8"))
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

}
