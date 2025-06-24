package com.example.PagesOrder;

import java.io.ObjectInputFilter.Config;
import java.time.Duration;
import java.util.List;



import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.ConfigManager;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class Praktika {

    private WebDriver driver; 
    private WebDriverWait wait;
    private JavascriptExecutor js;

    public Praktika(WebDriver driver) {
        this.driver = driver; 
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 
        this.js = (JavascriptExecutor) driver;

    }

    public static void main(String[] args) {
        
        WebDriver driver = new ChromeDriver(); 

        driver.manage().window().maximize();

        driver.get(ConfigManager.getProperty("fsdaf")); 

        List <WebElement> lol = driver.findElements(By.id("fdsa"));  

        for(WebElement lo: lol) { 
            String text = lo.getText(); 
            lo.click(); 
            
            WebElement updateElement = driver.findElement(By.id("fdsa"));

            String text2 = updateElement.getText(); 

            int pop = Integer.parseInt(text2); 
            
            int pop2 = Integer.parseInt(text);
            
            int pop3 = pop2 - pop;

            int i; 
            int p; 
            
            assertEquals(300, pop3); 

                

            


        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    private static void assertEquals(int i, int pop3) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'assertEquals'");
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
