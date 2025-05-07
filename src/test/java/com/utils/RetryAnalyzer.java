package com.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RetryAnalyzer implements IRetryAnalyzer {
    private static final Logger logger = LogManager.getLogger(RetryAnalyzer.class);
    private int count = 0;
    private static final int MAX_RETRY = 3;

    @Override
    public boolean retry(ITestResult result) {
        // Добавьте логирование статуса
        logger.debug("Checking retry for test: {}, Status: {}, Throwable: {}",
                result.getName(),
                result.getStatus(),
                result.getThrowable());

        if (result.getThrowable() != null && count < MAX_RETRY) {
            count++;
            logger.warn("Retry #{} for test {} due to: {}",
                    count,
                    result.getName(),
                    result.getThrowable().getMessage());
            return true;
        }
        return false;
    }
}
// ITestResult