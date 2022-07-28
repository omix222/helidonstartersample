
package com.example.myproject;

import org.eclipse.microprofile.metrics.Counter;
import org.eclipse.microprofile.metrics.MetricRegistry;
import jakarta.json.JsonArray;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.client.Entity;
import jakarta.inject.Inject;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;

import io.helidon.microprofile.tests.junit5.HelidonTest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.MethodOrderer.MethodName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@HelidonTest
@TestMethodOrder(MethodOrderer.MethodName.class)
public class MainTest {

    @Inject
    private MetricRegistry registry;

    @Inject
    private WebTarget target;


    @Test
    public void testMicroprofileMetrics() {
        String message = target.path("simple-greet/Joe")
                .request()
                .get(String.class);

        assertThat(message, is("Hello Joe"));
        Counter counter = registry.counter("personalizedGets");
        double before = counter.getCount();

        message = target.path("simple-greet/Eric")
                .request()
                .get(String.class);

        assertThat(message, is("Hello Eric"));
        double after = counter.getCount();
        assertEquals(1d, after - before, "Difference in personalized greeting counter between successive calls");
    }

    @Test
    public void testMetrics() throws Exception {
        Response response = target
                .path("metrics")
                .request()
                .get();
        assertThat(response.getStatus(), is(200));
    }

    @Test
    public void testHealth() throws Exception {
        Response response = target
                .path("health")
                .request()
                .get();
        assertThat(response.getStatus(), is(200));
    }

    @Test
    void testPokemonTypes() {
        JsonArray types = target
                .path("type")
                .request()
                .get(JsonArray.class);
        assertThat(types.size(), is(18));
    }

    @Test
    void testPokemon() {
        assertThat(getPokemonCount(), is(6));

        Pokemon pokemon = target
                .path("pokemon/1")
                .request()
                .get(Pokemon.class);
        assertThat(pokemon.getName(), is("Bulbasaur"));

        pokemon = target
                .path("pokemon/name/Charmander")
                .request()
                .get(Pokemon.class);
        assertThat(pokemon.getType(), is(10));

        Response response = target
                .path("pokemon/1")
                .request()
                .get();
        assertThat(response.getStatus(), is(200));

        Pokemon test = new Pokemon();
        test.setType(1);
        test.setId(100);
        test.setName("Test");
        response = target
                .path("pokemon")
                .request()
                .post(Entity.entity(test, MediaType.APPLICATION_JSON));
        assertThat(response.getStatus(), is(204));
        assertThat(getPokemonCount(), is(7));

        response = target
                .path("pokemon/100")
                .request()
                .delete();
        assertThat(response.getStatus(), is(204));
        assertThat(getPokemonCount(), is(6));
    }

    private int getPokemonCount() {
        JsonArray pokemons = target
                .path("pokemon")
                .request()
                .get(JsonArray.class);
        return pokemons.size();
    }


    @Test
    public void testGreet() throws Exception {
        Message message = target
                .path("simple-greet")
                .request()
                .get(Message.class);
        assertThat(message.getMessage(), is("Hello World!"));
    }
                
}
