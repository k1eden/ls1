package com.k1;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class LsMethodsTest {


    @Test
    void lastMod() {
        File test = new File("src/main/resources/1.PNG");
        assertEquals(LsMethods.LastMod(test), "11.05.2020 17:09:13");
        File test1 = new File("src/main/resources/2.PNG");
        assertEquals(LsMethods.LastMod(test1), "14.05.2020 22:35:11");
        File test2 = new File("src/main/resources/3.txt");
        assertEquals(LsMethods.LastMod(test2), "05.06.2020 21:16:10");
        File test3 = new File("src/main/resources/5.txt");
        assertEquals(LsMethods.LastMod(test3), "01.01.1970 03:00:00"); // проверку на существования
        // файла я делаю дальше, посему тут выдается дефолтное значение.
        File test4 = new File("src/main/resources/4.txt");
        assertNotEquals(LsMethods.LastMod(test4), "19.05.2020 06:39:42");
        assertNotEquals(LsMethods.LastMod(test2), "12.12.2011 12:32:12");
        assertNotEquals(LsMethods.LastMod(test), "dsd dsd");
        File test5 = new File("src/main/resources/53223.txt");
        assertEquals(LsMethods.LastMod(test3), LsMethods.LastMod(test5));
        File test6 = new File("src/main/resources/tester");
        assertEquals(LsMethods.LastMod(test6), "05.06.2020 21:25:31");
    }

    @Test
    void flagHSize() {
        File test = new File("src/main/resources/1.PNG");
        assertEquals(LsMethods.flagHSize(test), "13kb");
        File test1 = new File("src/main/resources/2.PNG");
        assertEquals(LsMethods.flagHSize(test1), "10kb");
        File test2 = new File("src/main/resources/3.txt");
        assertEquals(LsMethods.flagHSize(test2), "0b");
        File test3 = new File("src/main/resources/4.txt");
        assertNotEquals(LsMethods.flagHSize(test3), LsMethods.LastMod(test3));
        assertNotEquals(LsMethods.flagHSize(test3), "2048b");
        assertEquals(LsMethods.flagHSize(test3), "2kb");
        File test5 = new File("src/main/resources/423.txt");
        assertNotEquals(LsMethods.flagHSize(test5), "0kb");
        assertEquals(LsMethods.flagHSize(test5), "0b");
    }

    @Test
    void FlagOE() throws IOException {
        String[] test;
        File test11 = new File("src/main/resources/5.txt");
        Main.main(test = new String[]{"-h", "-l","-o", "src/main/resources/5.txt", "src/main/resources/tester"});
        assertEquals(new String(Files.readAllBytes(Paths.get("src/main/resources/test.txt"))),
                new String(Files.readAllBytes(Paths.get("src/main/resources/5.txt"))));
        test11.delete();

        File test21 = new File("src/main/resources/5.txt");
        Main.main(new String[]{"-h","-o", "src/main/resources/5.txt", "src/main/resources/tester"});
        assertEquals(new String(Files.readAllBytes(Paths.get("src/main/resources/test1.txt"))),
                new String(Files.readAllBytes(Paths.get("src/main/resources/5.txt"))));
        test21.delete();
    }

    @Test
    void getListOfFile() {
 // нет нужды в тестах, т.к работает FlagOE, а данный метод является его частью
    }
}