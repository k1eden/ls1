package com.k1;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;


import java.util.ArrayList;


public class Main {

    @Option(name = "-h", usage = "human-readable")
    private boolean flagH;

    @Option(name = "-l", usage = "long")
    private boolean flagL;

    @Option(name = "-r", usage = "reverse")
    private boolean flagR;

    @Option(name = "-o", usage = "output")
    private String output;

    @Argument(required = true, metaVar = "Input Name", usage = "Input directory")
    private String foD;

    public void lParser(String[] args) {
        CmdLineParser testParser = new CmdLineParser(this);
        try {
            testParser.parseArgument(args);
            LsMethods ls = new LsMethods(foD, flagL, flagH, flagR, output);
            ArrayList<String> testFileList = ls.getListOfFile();
            if (output == null) {
                for (String FileList : testFileList)
                    System.out.println(FileList);
            } else ls.FlagOE(testFileList);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
        public static void main (String[]args) {
            new Main().lParser(args);
        }
    }

