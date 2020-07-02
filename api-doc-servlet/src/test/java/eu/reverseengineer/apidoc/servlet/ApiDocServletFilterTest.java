package eu.reverseengineer.apidoc.servlet;

import io.restassured.response.Response;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletHandler;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static io.restassured.RestAssured.given;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ApiDocServletFilterTest {

    @BeforeAll
    private void init() throws Exception {
        Server server = new Server(8081);

        ServletHandler sh = new ServletHandler();

        sh.addFilter(
                new FilterHolder(
                        new ApiDocServletFilter(
                                ".*/swagger.json",
                                "application/json",
                                () -> "{\"title\": \"api\"}"
                        )
                )
        );

        server.setHandler(sh);

        server.start();
        server.join();
    }

    @Test
    public void testGetJson() {
        Response r = given()
                .port(8081)
                .get("/bla/swagger.json");
    }

}
