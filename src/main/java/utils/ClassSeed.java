package utils;

import Network.NetworkSeed;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public abstract class ClassSeed implements SeedInterface {

    public ClassSeed() {
        this.init();
    }

    public ClassSeed(String filename) {
        this.init();
        File seed = new File(getPath() + filename + ".txt");
        Scanner scanner;
        try {
            scanner = new Scanner(seed).useDelimiter("([ \n])+");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        this.scanSeed(scanner);
    }

    public void saveSeed() {
        String filename = getStandardFilename();
        this.saveSeed(filename);
    }


    public void saveSeed(String filename) {
        String filepath = this.getPath() + filename + ".txt";
        PrintWriter writer;
        File file = new File(filepath);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            writer = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        writer.print(this.serialize());
        writer.close();
    }

    public static String getStandardFilename() {
        return ClassSeed.getDate() + "_seed";
    }

    private static String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        Date date = new Date();
        return formatter.format(date);
    }

}
