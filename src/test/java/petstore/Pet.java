// 1 - Pacote
package petstore;

// 2- Bibliotecas
import org.testng.annotations.Test;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;

// 3 - Classe
public class Pet {
    // 3.1 - Atributos
    String uri = "https://petstore.swagger.io/v2/pet"; // endereço da entidade Pet

    // 3.2 - Métodos e Funções
    public String lerJson(String caminhoJson) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    // Incluir - Creat - API é chamado de Post
    @Test // Identifica o método ou função como um teste para o TestNG
    public void incluirPet() throws IOException{
        String jsonBody = lerJson( "db/pet1.json");

        // Sintexa Gherkin
        // Dado - Quando - Então
        // Given - When - Then

        given() // Dado
                .contentType("application/json") // Comum em API REST - antigas era "text/xml"
                .log().all() // ida
                .body(jsonBody)

        .when() // Quando
                .post(uri)

        .then() // Então
                .log().all() // volta
                .statusCode(200)
                .body("name", is("Atena"))
                .body("status", is("available"))
                .body("category.name", is ("dog"))
                .body("tags.name", contains("sta"))

        ;
    }

}
