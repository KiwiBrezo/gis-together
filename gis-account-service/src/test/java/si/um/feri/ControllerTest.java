package si.um.feri;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ControllerTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/api/v1/ping")
          .then()
             .statusCode(200)
             .body(is("Pong"));
    }

}
