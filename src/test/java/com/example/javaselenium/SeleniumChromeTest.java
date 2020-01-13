package com.example.javaselenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SeleniumChromeTest {

    @Test
    public void 正常系_表示_コピーライト() {
        //指定したURLに遷移する
        webDriver.get("https://saikeblog.com");

        // 最大5秒間、ページが完全読み込まれるまで待つ
        webDriver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

        // タブの「技術」要素を取得し、クリックする
        WebElement webElement = webDriver.findElement(By.className("source-org"));

        // 現在の年を取得する
        final int year = LocalDateTime.now().getYear();

        // 検証
        assertThat(webElement.getText(), is("Copyright © " + year + " さいけの技術ブログ All Rights Reserved."));
    }

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
    public void 正常系_整合_URL() {
        //指定したURLに遷移する
        webDriver.get("https://saikeblog.com");

        // 最大5秒間、ページが完全読み込まれるまで待つ
        webDriver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

        // 検証
        assertThat(webDriver.getCurrentUrl(), is("https://saikeblog.com/"));
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
        assertThat(webDriver.getTitle(), is("技術 – さいけの技術ブログ"));
        assertThat(webDriver.getCurrentUrl(), is("https://saikeblog.com/category/%e6%8a%80%e8%a1%93/"));
    }

    @Test
    public void 正常系_動作_検索() {
        //指定したURLに遷移する
        webDriver.get("https://saikeblog.com");

        // 最大5秒間、ページが完全読み込まれるまで待つ
        webDriver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

        // 検索ボックス要素にjavaを入力し検索する
        WebElement webElement = webDriver.findElement(By.name("s"));
        webElement.sendKeys("java");
        webElement.submit();

        // 検証
        assertThat(webDriver.getTitle(), is("“java” の検索結果 – さいけの技術ブログ"));
        assertThat(webDriver.getCurrentUrl(), is("https://saikeblog.com/?s=java"));
    }


    @Test
    public void 正常系_動作_ページング_次へボタン() {
        // 指定したURLに遷移する
        webDriver.get("https://saikeblog.com");

        // 最大5秒間、ページが完全読み込まれるまで待つ
        webDriver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

        // 画面下部にスクロールするjavascriptを実行
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
        javascriptExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        // 「次のページ」要素をクリックする
        WebElement webElement = webDriver.findElement(By.className("next"));
        webElement.click();

        // 検証
        assertThat(webDriver.getCurrentUrl(), is("https://saikeblog.com/page/2/"));
    }

    @Test
    public void 正常系_動作_ページング_前へボタン() {
        // 指定したURLに遷移する
        webDriver.get("https://saikeblog.com/page/2/");

        // 最大5秒間、ページが完全読み込まれるまで待つ
        webDriver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

        // 画面下部にスクロールするjavascriptを実行
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
        javascriptExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        // 「次のページ」要素をクリックする
        WebElement webElement = webDriver.findElement(By.className("prev"));
        webElement.click();

        // 検証
        assertThat(webDriver.getCurrentUrl(), is("https://saikeblog.com/"));
    }
} 