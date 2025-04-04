package com.example.PagesOrder;

import java.time.Duration;
import java.util.Scanner;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Praktika {

    public static int[] firstArray = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    public static int[] secondArray = new int[] { 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };
    public static int[] resultArray;

    public static void main(String[] args) {
        // Твой код здесь
        resultArray = new int[firstArray.length + secondArray.length];

        for (int i = 0; i < firstArray.length; i++) {
            if (i < firstArray.length) {
                resultArray[i] = firstArray[i];
            } else {
                resultArray[i] = secondArray[i - firstArray.length];
            }

        }

    }

}

// public class Solution {
// public static void main(String[] args) {
// // напишите тут ваш код
// Scanner lol = new Scanner(System.in);

// int sum = 0;

// while (true) {

// String v = lol.nextLine();

// if (v.equals("ENTER")) {
// System.out.println(sum);
// break;
// }

// try {
// int i = Integer.parseInt(v);
// sum += i;
// } catch (Exception e) {
// System.out.println();
// }

// }

// }
// }

// Scanner console = new Scanner(System.in);

// while(console.hasNextInt()) {
// int x = console.nextInt();

// if(x == 0) {
// System.out.println("Всё");
// break;
// } else {
// console.next();
// }
// if(x % 2 == 0) {
// System.out.println(x);
// } else {
// console.next();
// }
// }

// Scanner console = new Scanner(System.in);

// int min = Integer.MIN_VALUE;

// while(true)
// {
// if(console.hasNextInt()) {
// int x = console.nextInt();

// if(x>min) {

// x=min;

// }

// if(console.hasNext()) {
// System.out.println("Введите натурально число");

// } else {
// console.next();
// }

// System.out.println(x);
// }
// }
