package com.example.javaselenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SeleniumSampleTest {

    private WebDriver webDriver;

    @Before
    public void createDriver() {
        System.setProperty("webdriver.chrome.driver", "selenium/chromedriver/79/chromedriver");
        webDriver = new ChromeDriver();
    }

    @After
    public void quitDriver() {
        webDriver.close();
    }

    @Test
    public void 正常系_表示_ページタイトル() {
        //指定したURLに遷移する
        webDriver.get("https://saikeblog.com");

        // 最大5秒間、ページが完全読み込まれるまで待つ
        webDriver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

        // 検証
        assertThat(webDriver.getTitle(), is("さいけの技術ブログ"));
    }

    @Test
    public void 正常系_遷移_技術ページ() {
        //指定したURLに遷移する
        webDriver.get("https://saikeblog.com");

        // 最大5秒間、ページが完全読み込まれるまで待つ
        webDriver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

        // タブの「技術」要素を取得し、クリックする
        WebElement webElement = webDriver.findElement(By.id("menu-item-37"));
        webElement.click();

        // 検証
        assertThat(webDriver.getTitle(), is("さいけの技術ブログ"));
        assertThat(webDriver.getCurrentUrl(), is("https://saikeblog.com/category/%e6%8a%80%e8%a1%93/"));
    }
} 