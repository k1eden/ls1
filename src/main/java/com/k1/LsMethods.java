package com.k1;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;


public class LsMethods {
    private boolean flagL;
    private boolean flagH;
    private boolean flagR;
    private String foD;
    private String output;

    public LsMethods(String FoD, boolean FlagL, boolean FlagH, boolean FlagR, String output) {
        this.foD = FoD;
        this.flagH = FlagH;
        this.flagR = FlagR;
        this.flagL = FlagL;
        this.output = output;
    }

    private static long getFolderSize(File folder) {
        long length = 0;
        File[] files = folder.listFiles();

        int count = files.length;

        for (int i = 0; i < count; i++) {
            if (files[i].isFile()) {
                length += files[i].length();
            } else {
                length += getFolderSize(files[i]);
            }
        }
        return length;
    }

    private ArrayList<File> FileListMaker(File testFile) {
        ArrayList<File> testFileList = new ArrayList<>();
        if (!testFile.exists()) {
            System.out.println("File or Directory doesn't exist");
            System.out.close();
        }
        if (testFile.isDirectory() & testFile.length() == 0)
            System.out.println("The size of this folder = 0, if there is no list below, then the folder is empty");
        if (testFile.isDirectory()) Collections.addAll(testFileList, Objects.requireNonNull(testFile.listFiles()));
        else testFileList.add(testFile);
        testFileList.sort((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
        return testFileList;
    }

    public static String LastMod(File file) {
        Date testFileDate = new Date(file.lastModified());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

        return dateFormat.format(testFileDate);
    }

    private String Perm(File testFile) {
        String defPerm = "";

        if (flagH) {
            defPerm = "";
            defPerm += testFile.canRead() ? "r" : "";
            defPerm += testFile.canWrite() ? "w" : "";
            defPerm += testFile.canExecute() ? "x" : "";
        }

        if (flagL) {
            defPerm += testFile.canRead() ? 1 : 0;
            defPerm += testFile.canWrite() ? 1 : 0;
            defPerm += testFile.canExecute() ? 1 : 0;
        }

        return defPerm;
    }

    public static String flagHSize(File file) {
        long size = file.length();
        long size1;
        String testSize = "";

        if (file.isDirectory()) {
            size1 = getFolderSize(file);
            if (size1 < 1024)
                testSize += size1 + "b";
            else if (size1 < 1024 * 1024)
                testSize += size1 / 1024 + "kb";
            else if (size1 < 1024 * 1024 * 1024)
                testSize += size1 / (1024 * 1024) + "mb";
            else
                testSize += size1 / (1024 * 1024 * 1024) + "gb";

            return testSize;
        } else if (size < 1024)
            testSize += size + "b";
        else if (size < 1024 * 1024)
            testSize += size / 1024 + "kb";
        else if (size < 1024 * 1024 * 1024)
            testSize += size / (1024 * 1024) + "mb";
        else
            testSize += size / (1024 * 1024 * 1024) + "gb";

        return testSize;
    }

    private String filePrinter(File testFile) {
        String lMod = "";
        String fLen = "";

        if (flagL) {
            lMod = LastMod(testFile);
            fLen = Long.toString(testFile.length());
        }

        if (flagH) fLen = flagHSize(testFile);

        String finalFile = flagR ? String.format("%s %s %s %s", fLen, lMod, Perm(testFile), testFile.getName())
                : String.format("%s %s %s %s", testFile.getName(), Perm(testFile), lMod, fLen);
        return finalFile;
    }

    private ArrayList<String> dirPrinter(ArrayList<File> testFileList) {
        if (flagR) Collections.reverse(testFileList);
        ArrayList<String> newList = new ArrayList<>();
        for (File testFile : testFileList) newList.add(filePrinter(testFile));
        return newList;
    }

    public ArrayList<String> getListOfFile() {
        File testFile = new File(foD);
        ArrayList<File> testFileList = FileListMaker(testFile);
        return dirPrinter(testFileList);
    }

    public void FlagOE(ArrayList<String> testList) throws IOException {
        File testFile = new File(output);
        FileWriter testWr = new FileWriter(testFile);
        BufferedWriter testBw = new BufferedWriter(testWr);
        PrintWriter testWr2 = new PrintWriter(testBw);

        for (String testFileList : testList) testWr2.write(testFileList);
        testWr2.close();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        LsMethods ls = (LsMethods) o;
        return flagL == ls.flagL &&
                flagH == ls.flagH &&
                flagR == ls.flagR &&
                Objects.equals(output, ls.output);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flagL, flagH, flagR, output);
    }
}