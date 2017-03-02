package com.its.test.drop.integration;
import com.its.test.drop.HelloWorldApplication;
import com.its.test.drop.HelloWorldConfiguration;
import com.its.test.drop.api.Saying;

import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;

import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.Rule;
import org.junit.Test;

import javax.ws.rs.client.Client;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloWorldIntegrationTest {
    @Rule
    public final DropwizardAppRule<HelloWorldConfiguration> RULE =
        new DropwizardAppRule<HelloWorldConfiguration>(HelloWorldApplication.class,
           /* ResourceHelpers.resourceFilePath(*/"hello-world.yml");

    @Test
    public void runServerTest() {
        Client client = new JerseyClientBuilder().build();
        Saying result = client.target(
            String.format("http://localhost:%d/api/hello-world", RULE.getLocalPort())
        ).queryParam("name", "dropwizard").request().get(Saying.class);
        assertThat(result.getContent()).isEqualTo("Hello, dropwizard!");
    }
}