package cloud.autotests.tests;

import cloud.autotests.config.Project;
import cloud.autotests.config.ProjectConfig;
import cloud.autotests.helpers.Attach;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.junit5.AllureJunit5;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import cloud.autotests.helpers.DriverSettings;
import static com.codeborne.selenide.Selenide.closeWebDriver;


@ExtendWith({AllureJunit5.class})
public class TestBase {
    static String login,
            password;
    static ProjectConfig config = ConfigFactory.create(ProjectConfig.class);

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        DriverSettings.configure();
        login = Project.config.Login();
        password = Project.config.Password();

    }

    @BeforeEach
    public void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }


    @AfterEach
    public void afterEach() {
        Attach.takeScreenshot("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }
}
