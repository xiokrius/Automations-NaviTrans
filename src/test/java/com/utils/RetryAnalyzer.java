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
    private static final int MAX_RETRY = 3;

    @Override
    public boolean retry(ITestResult result) {
        // Если тест успешен - не перезапускаем
        if (result.isSuccess()) {
            return false;
        }

        Throwable throwable = result.getThrowable();
        if (throwable == null || count >= MAX_RETRY) {
            return false;
        }

        // Проверяем тип исключения
        boolean isRetryableException = throwable instanceof WebDriverException ||
                throwable instanceof TimeoutException ||
                throwable.getCause() instanceof WebDriverException ||
                throwable.getCause() instanceof TimeoutException;

        if (isRetryableException) {
            count++;
            logger.warn("Retry #{} for test {} due to: {}",
                    count, result.getName(), throwable.getMessage());
            return true;
        }

        return false;
    }
}
// ITestResult