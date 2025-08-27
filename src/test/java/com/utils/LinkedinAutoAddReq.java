package com.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import java.time.Duration;
import org.testng.annotations.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LinkedinAutoAddReq {

    String url = "https://www.linkedin.com/search/results/people/?keywords=rekruter&origin=CLUSTER_EXPANSION&page=6&sid=!wlhttps://www.linkedin.com/in/вавина-марина-47b324158/";

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
        driver.get(url);
    }

    @Test
    public void test() {
        // Loop through pages until no next page is available
        while (true) {
            // Find all target buttons on the page
            List<WebElement> elements = driver.findElements(
                    By.className("artdeco-button artdeco-button--2 artdeco-button--secondary ember-view"));

            if (elements.isEmpty()) {
                // Try to paginate if nothing is found
                if (!goToNextPage()) {
                    break;
                }
                continue;
            }

            for (WebElement element : elements) {
                try {

                    wait.until(driver1 -> element.isDisplayed() || element.isEnabled());

                    if (element.isDisplayed()) {
                        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
                    } else {

                        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
                        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
                        // ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 300);");
                    }
                } catch (Exception ignore) {

                    try {
                        js.executeScript("window.scrollBy(0, 300);");
                        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
                    } catch (Exception ignoredAgain) {
                        // Skip this element if still failing
                    }
                }
            }

            // After processing current list, go to next page if possible
            if (!goToNextPage()) {
                break;
            }
        }
    }

    private boolean goToNextPage() {
        try {
            // LinkedIn pagination next button selector may differ; adjust if needed
            // Attempt common aria-label based selector
            List<WebElement> nextButtons = driver.findElements(By.cssSelector("button[aria-label='Next']"));
            WebElement next = nextButtons.stream().filter(WebElement::isDisplayed).findFirst().orElse(null);
            if (next != null && next.isEnabled()) {
                js.executeScript("arguments[0].scrollIntoView({block: 'center'});", next);
                wait.until(ExpectedConditions.elementToBeClickable(next)).click();
                // Wait for results to refresh
                wait.until(
                        d -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));
                // small extra wait for ajax
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ignored) {
                }
                return true;
            }
        } catch (Exception e) {
            // Fallback: try a CSS selector for the pagination list next link
            try {
                WebElement altNext = driver.findElement(By.cssSelector("li.artdeco-pagination__button--next button"));
                if (altNext.isDisplayed() && altNext.isEnabled()) {
                    js.executeScript("arguments[0].scrollIntoView({block: 'center'});", altNext);
                    wait.until(ExpectedConditions.elementToBeClickable(altNext)).click();
                    wait.until(d -> ((JavascriptExecutor) d).executeScript("return document.readyState")
                            .equals("complete"));
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ignored) {
                    }
                    return true;
                }
            } catch (Exception ignore) {
                // No next button found
            }
        }
        return false;
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }

}
