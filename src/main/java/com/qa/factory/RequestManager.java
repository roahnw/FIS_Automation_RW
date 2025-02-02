package com.qa.factory;

import com.qa.enums.ApiRequest;
import com.qa.enums.ConfigKeys;
import com.qa.pages.BasePage;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.Map;
import java.util.Objects;

public class RequestManager extends BasePage {

    StringBuilder requestLog = new StringBuilder();

    private RequestSpecification requestSpecification;

    public RequestManager(ConfigKeys key) {

        String baseURI = PropertiesReader.getProperty(key);

        requestSpecification = RestAssured.given()
                .baseUri(baseURI);

        requestLog.append("REQUEST:\n" +
                "Request url: " + baseURI+"\n");
    }

    private static ResponseSpecification getResponseSpec() {
        return RestAssured.expect()
                .statusCode(200)
                .contentType(ContentType.JSON);
    }

    public RequestManager setBasePath(String basePath) {
        requestSpecification.basePath(basePath);

        requestLog.append("Request base path: " + basePath+"\n");
        return this;
    }

    public RequestManager setHeaders(Map<String, String> headers) {

        if (!headers.isEmpty()) {
            requestSpecification.headers(headers);
            requestLog.append("Request headers: " + headers+"\n");
        }
        return this;
    }

    public RequestManager setBody(Object body) {
        if (Objects.nonNull(body)) {
            requestSpecification.body(body);
            requestLog.append("Request body:\n" + body+"\n");
        }
        return this;
    }

    public Response createRequest(ApiRequest requestType) {

        Response response;
        switch (requestType.toString().toUpperCase()) {
            case "GET":
                response = requestSpecification.get().then().spec(getResponseSpec()).extract().response();
                break;
            case "POST":
                response = requestSpecification.post().then().spec(getResponseSpec()).extract().response();
                break;
            case "PUT":
                response = requestSpecification.put().then().spec(getResponseSpec()).extract().response();
                break;
            case "PATCH":
                response = requestSpecification.patch().then().spec(getResponseSpec()).extract().response();
                break;
            case "DELETE":
                response = requestSpecification.delete().then().spec(getResponseSpec()).extract().response();
                break;
            default:
                throw new IllegalArgumentException("Request not implemented");
        }
        test.get().info(String.valueOf(requestLog));

        System.out.println(requestLog);

        test.get().info("RESPONSE:\n" +
        "Status Code: " + response.getStatusCode() + "\n");
        test.get().info("RESPONSE:\n" +
                "Headers: " + response.getHeaders() + "\n");
        test.get().info("RESPONSE:\n" +
                "Body: " + response.getBody().asPrettyString());
        System.out.println("RESPONSE:\n" +
                "Status Code: " + response.getStatusCode() + "\n"+
                "Headers: " + response.getHeaders() + "\n"+
                "Body: " + response.getBody().asPrettyString());
        return response;
    }
}
