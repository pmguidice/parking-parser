package com.pg.parser;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CacheClientTest {
    private static CacheClient client = null;

    private static Processor processor = null;

    @BeforeSuite
    public void init() {
        client = new CacheClient();

        processor = new Processor();
    }

    @Test
    public void testSet() {
        String result = client.set("test", "apple");

        assertEquals(result, "OK");

        System.out.println("result is " + result);
    }

    @Test (dependsOnMethods={"testSet"})
    public void testGet() {
        String result = client.get("test");
        assertEquals(result, "apple");
        System.out.println("result is " + result);

        result = client.get("test2");
        assertNull(result);
        System.out.println("result is " + result);
    }

    @Test
    public void testProcess() {
        processor.process();
    }

    @Test (dependsOnMethods={"testProcess"})
    public void testGetStations() {
        String dunwoodyJson = client.get("Dunwoody");
        System.out.println(dunwoodyJson);

        String northspringsJson = client.get("North Springs");
        System.out.println(northspringsJson);

    }
}
