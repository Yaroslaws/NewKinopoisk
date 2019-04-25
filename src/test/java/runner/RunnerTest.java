package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


    @RunWith(Cucumber.class)
    @CucumberOptions(
            features = {"src/test/java/features/checkLogin.feature", "src/test/java/features/checkTitle.feature",
                        "src/test/java/features/assertReatingFilms.feature", "src/test/java/features/checkTitleNavigator.feature",
                        "src/test/java/features/navigatorFilms.feature", "src/test/java/features/openKinopoisk.feature",
                        "src/test/java/features/searchFIlms.feature", "src/test/java/features/searchFilmsRating.feature",
                        "src/test/java/features/login.feature"
                         },
            glue = "step_definitions",
            tags = "@withdrawal",
            snippets = SnippetType.CAMELCASE


    )

    public class RunnerTest {
        }


