package com.example.PagesOrder;

import java.io.ObjectInputFilter.Config;
import java.time.Duration;
import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.ConfigManager;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class Praktika {

    public static void main(String[] args) {
        String[] inputValues = readInput();
        String message = inputValues[0];
        int score = Integer.parseInt(inputValues[1]);
        int vernScore = score * 2;
        String result = message + " " + vernScore;

        System.out.println(result);
    }

    public static String[] readInput() {
        Scanner scanner = new Scanner(System.in);
        String inputLine = scanner.nextLine();
        // scanner.close();  // лучше не закрывать System.in
        return inputLine.split(" ");
    }
}

// public static void main(String[] args) {
// ArrayList<String> words = new ArrayList<>();
// words.add("Hello world!");
// words.add("Amigo");
// words.add("Elly");
// words.add("Kerry");
// words.add("Bug");
// words.add("bug");
// words.add("Easy ug");
// words.add("Risha");
// ArrayList<String> copyWordsFirst = new ArrayList<>(words);
// ArrayList<String> copyWordsSecond = new ArrayList<>(words);
// ArrayList<String> copyWordsThird = new ArrayList<>(words);
// removeBugWithFor(copyWordsFirst);
// removeBugWithWhile(copyWordsSecond);
// removeBugWithCopy(copyWordsThird);
// copyWordsFirst.forEach(System.out::println);
// String line = "_________________________";
// System.out.println(line);
// copyWordsSecond.forEach(System.out::println);
// System.out.println(line);
// copyWordsThird.forEach(System.out::println);
// System.out.println(line);
// }
//
// public static void removeBugWithFor(ArrayList<String> list) {
// for (int i = 0; i < list.size(); i++) {
// String lol = list.get(i);
// if (lol.equalsIgnoreCase("bug")) list.remove(lol);
// }
// }
//
// public static void removeBugWithWhile(ArrayList<String> list) {
// Iterator<String> it = list.iterator();
// while (it.hasNext()) {
// String str = it.next();
// if (str.equalsIgnoreCase("bug")) it.remove();
// }
// }
//
// public static void removeBugWithCopy(ArrayList<String> list) {
// ArrayList<String> copy = new ArrayList<>(list);
// for (String cop : copy) {
// if (cop.equalsIgnoreCase("bug")) list.remove(cop);
// }
// }
