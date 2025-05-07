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

        if (!result.isSuccess() && count < MAX_RETRY) {
            count++;
            logger.warn("Повторная попытка #{} для теста {} из-за ошибки: {}",
                    count,
                    result.getName(),
                    result.getThrowable().getMessage());
            return true;
        }
        logger.error("Тест {} провален после {} попыток", result.getName(), MAX_RETRY);
        return false;
    }
}
// ITestResult