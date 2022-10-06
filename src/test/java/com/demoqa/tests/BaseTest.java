package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.demoqa.helpers.Attach;
import com.demoqa.pages.FormPage;
import com.demoqa.pages.TestData;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BaseTest {

    FormPage formPage = new FormPage();
    TestData testData = new TestData();

    @BeforeAll
    static public void config() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        Configuration.browserCapabilities = capabilities;
        Configuration.baseUrl = "https://demoqa.com/";

        if (System.getProperty("remote").equals("true")) {
            Configuration.browser = System.getProperty("browser_name");
            Configuration.browserVersion = System.getProperty("browser_version");
            Configuration.browserSize = System.getProperty("browser_size");
            Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
        }
        else {
            Configuration.browser ="chrome";
            Configuration.browserSize ="1920x1080";
        }
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}
