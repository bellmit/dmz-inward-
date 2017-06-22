package com.dmz.service.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by dmz on 2016/11/1.
 */
public class CsvRD {
    public static void readCSV(String csv) {
        try (Scanner scanner = new Scanner(new File(csv))) {
            scanner.useDelimiter(",");
            while (scanner.hasNext()) {
                System.out.println(scanner.next() + "|");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
