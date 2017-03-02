

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
 
@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features/rest.feature",
				 dryRun = false,
				 snippets = SnippetType.CAMELCASE, 
				 plugin = { "pretty",
						 	"html:target/cucumber-html-report" },
				 tags = {})
public class RestRunner {
 
}