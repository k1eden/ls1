package com.k1;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class LsMethodsTest {


    @Test
    void lastMod() {
        File test = new File("src/main/resources/1.PNG");
        assertEquals(LsMethods.LastMod(test), "11.05.2020 17:09:13");
        File test1 = new File("src/main/resources/2.PNG");
        assertEquals(LsMethods.LastMod(test1), "14.05.2020 22:35:11");
        File test2 = new File("src/main/resources/3.txt");
        assertEquals(LsMethods.LastMod(test2), "19.05.2020 14:49:43");
    }

    @Test
    void flagHSize() {
        File test = new File("src/main/resources/1.PNG");
        assertEquals(LsMethods.flagHSize(test), "13kb");
        File test1 = new File("src/main/resources/2.PNG");
        assertEquals(LsMethods.flagHSize(test1), "10kb");
        File test2 = new File("src/main/resources/3.txt");
        assertEquals(LsMethods.flagHSize(test2), "0b");
    }
}