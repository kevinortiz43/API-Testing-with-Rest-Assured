package com.fdmgroup.module3;

public class RunnerModule {

    public static void main(String[] args) {
        // Get method
        // PostMethods.performGETPost();
        // PostMethods.performGETPostPathParameter("1", "typicode");
        // PostMethods.performGETPostQueryParameter("1", "json-server", "typicode");
        PostMethods.performGETWithId("2");

        // Post Method
        // PostMethods.performPOSTPost("5", "new title", "new author");
        // PostMethods.performGETPostPathParameter("5", "new author");

        // Put Method
        // PostMethods.performPUTPost("5");

        // Delete
        // PostMethods.performDeletePost("5");
    }

}
