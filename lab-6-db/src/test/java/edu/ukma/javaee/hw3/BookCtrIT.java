package edu.ukma.javaee.hw3;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.text.IsBlankString.blankOrNullString;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class BookCtrIT {

    @LocalServerPort
    void setPort(int port) {
        RestAssured.port = port;
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Test
    void booksAreAddedSuccessfully() {
        RestAssured.given()
                .multiPart("isbn", "12345")
                .multiPart("title", "The picture of Dorian Gray")
                .multiPart("author", "Oscar Wilde")
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                .when()
                .post("/book")
        .andReturn()
        .then()
        .statusCode(200);
    }

    @Test
    void duplicateBookIsbnThrowsError() {
        RestAssured.given()
                .multiPart("isbn", "12345")
                .multiPart("title", "The picture of Dorian Gray")
                .multiPart("author", "Oscar Wilde")
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                .when()
                .post("/book");

        RestAssured.given()
                .multiPart("isbn", "12345")
                .multiPart("title", "The picture of Dorian Gray")
                .multiPart("author", "Oscar Wilde")
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                .when()
                .post("/book")
                .andReturn()
                .then()
                .statusCode(400);
    }

    @Test
    void allPresentBooksAreReturned() {
        RestAssured.given()
                .multiPart("isbn", "12345")
                .multiPart("title", "The picture of Dorian Gray")
                .multiPart("author", "Oscar Wilde")
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                .when()
                .post("/book");
        RestAssured.given()
                .multiPart("isbn", "23456")
                .multiPart("title", "Zapovit")
                .multiPart("author", "Taras Shevchenko")
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                .when()
                .post("/book");

        List<Book> books = Arrays.asList(RestAssured.given().when()
                .get("/books")
        .as(Book[].class));
        assertThat(books.size() == 2);
        assertThat(books.contains(new Book("12345", "The picture of Dorian Gray", "Oscar Wilde")));
        assertThat(books.contains(new Book("23456", "Zapovit", "Taras Shevchenko")));
    }

    @Test
    void booksFoundByTitle() {
        RestAssured.given()
                .multiPart("isbn", "12345")
                .multiPart("title", "The picture of Dorian Gray")
                .multiPart("author", "Oscar Wilde")
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                .when()
                .post("/book");

        Book[] dorianBook = RestAssured.given()
                .param("title", "The pic")
                .when()
                .get("/search/title")
                .as(Book[].class);

        assertThat(dorianBook.length == 1);
        assertThat(dorianBook[0].equals(new Book("12345", "The picture of Dorian Gray", "Oscar Wilde")));
    }

    @Test
    void bookNotFoundByWrongTitle() {
        RestAssured.given()
                .multiPart("isbn", "12345")
                .multiPart("title", "The picture of Dorian Gray")
                .multiPart("author", "Oscar Wilde")
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                .when()
                .post("/book");

        Book[] noBook = RestAssured.given()
                .param("title", "Zapov")
                .when()
                .get("/search/title")
                .as(Book[].class);

        assertThat(noBook.length == 0);
    }

    @Test
    void bookFoundByIsbn() {
        RestAssured.given()
                .multiPart("isbn", "12345")
                .multiPart("title", "The picture of Dorian Gray")
                .multiPart("author", "Oscar Wilde")
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                .when()
                .post("/book");

        RestAssured.given()
                .param("isbn", "12345")
                .when()
                .get("/search/isbn")
                .then()
                .body("isbn", CoreMatchers.is("12345"))
                .body("title", CoreMatchers.is("The picture of Dorian Gray"))
                .body("author", CoreMatchers.is("Oscar Wilde"));
    }

    @Test
    void bookNotFoundByWrongIsbn() {
        RestAssured.given()
                .multiPart("isbn", "12345")
                .multiPart("title", "The picture of Dorian Gray")
                .multiPart("author", "Oscar Wilde")
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                .when()
                .post("/book");

        Response resp = RestAssured.given()
                .param("isbn", "00000")
                .when()
                .get("/search/isbn")
                .then()
        .extract()
        .response();

        String respStr = resp.getBody().asString();
        assertThat(respStr == null || respStr.isEmpty());
    }
}
