package com.odde.atddv2.page;

import lombok.SneakyThrows;
import org.assertj.core.api.Assertions;
import org.awaitility.Awaitility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Objects;

@Component
public class Browser {
    WebDriver webDriver = null;

    public Browser() {
    }

    void clickByText(String text) {
        Awaitility.await().ignoreExceptions().until(() -> getWebDriver().findElement(By.xpath("//*[text()='" + text + "']")), Objects::nonNull).click();
    }

    void inputByPlaceholder(String text, String placeholder) {
        Awaitility.await().ignoreExceptions().until(() -> getWebDriver().findElement(By.xpath("//*[@placeholder='" + placeholder + "']")), Objects::nonNull).sendKeys(text);
    }

    public void shouldHaveText(String text) {
        Awaitility.await().ignoreExceptions().untilAsserted(() -> Assertions.assertThat(getWebDriver().findElements(By.xpath("//*[text()='" + text + "']"))).isNotEmpty());
    }

    public WebDriver getWebDriver() {
        if (webDriver == null)
            webDriver = createWebDriver();
        return webDriver;
    }

    @SneakyThrows
    public WebDriver createWebDriver() {
        return new RemoteWebDriver(new URL("http://web-driver.tool.net:4444"), DesiredCapabilities.chrome());
    }

    public void quitWebDriver() {
        if (webDriver != null) {
            webDriver.quit();
            webDriver = null;
        }
    }

    public void lunch() {
        getWebDriver().get("http://host.docker.internal:10081");
    }
}