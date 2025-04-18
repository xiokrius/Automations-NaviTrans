package com.e2e.runner;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;

public class TestNGRunner {
    public static void main(String[] args) {
        TestNG testNG = new TestNG();

        // Указываем классы тестов (можно заменить на загрузку из testng.xml)
        testNG.setTestClasses(new Class[] {
                com.e2e.CreateAndOpenVendorTest.class,
                com.e2e.CreateOrderNewTest.class,
                com.e2e.OpenContactsOrClientTest.class
        });

        // Включаем стандартный вывод результатов
        TestListenerAdapter tla = new TestListenerAdapter();
        testNG.addListener(tla);

        // Запуск
        testNG.run();

        // Статус выполнения (0 = успех, 1 = ошибка)
        System.exit(testNG.getStatus() == 0 ? 0 : 1);
    }
}
