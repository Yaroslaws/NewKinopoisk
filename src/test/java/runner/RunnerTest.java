package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


    @RunWith(Cucumber.class)
    @CucumberOptions(
            features = "src/test/java/features/assertReatingFilms.feature",
            glue = "step_definitions",
            tags = "@withdrawal",
            snippets = SnippetType.CAMELCASE
//            plugin = {
//                    "com.github.kirlionik.cucumberallure.AllureReporter"
//            }
    )
    public class RunnerTest {
    }

