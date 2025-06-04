package com.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import java.util.concurrent.TimeoutException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriverException;

public class RetryAnalyzer implements IRetryAnalyzer {
    private static final Logger logger = LogManager.getLogger(RetryAnalyzer.class);
    private int count = 0;
    private static final int MAX_RETRY = 1;

    @Override
    public boolean retry(ITestResult result) {
        // Не перезапускать, если тест уже успешен
        if (result.isSuccess()) {
            return false;
        }

        // Перезапускать только для определённых исключений
        Throwable throwable = result.getThrowable();
        if (throwable == null || count >= MAX_RETRY) {
            return false;
        }

        // Фильтр по типам исключений, которые стоит перезапускать
        if (throwable instanceof WebDriverException ||
                throwable instanceof TimeoutException) {
            count++;
            logger.warn("Retry #{} for test {} due to: {}",
                    count, result.getName(), throwable.getMessage());
            return true;
        }

        return false;
    }
}
// ITestResult