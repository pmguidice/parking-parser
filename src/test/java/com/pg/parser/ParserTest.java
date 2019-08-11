package com.pg.parser;

import org.jsoup.nodes.Document;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class ParserTest {
    private static final String TITLE = "MARTA";

    private static final int NUM_LOTS = 17;

    @Test
    public void testGetTitle() throws Exception {
        Parser parser = new Parser();

        String docTitle = parser.getTitle();

        assertEquals(docTitle, TITLE);

        System.out.println("title is " + docTitle);
    }

    @Test
    public void testGetDocument() throws Exception {
        Parser parser = new Parser();

        Document doc = parser.getDocument();

        assertNotNull(doc);
    }

    @Test
    public void testGetLotStatuses() throws Exception {
        Parser parser = new Parser();

        List<LotStatus> statuses = parser.getLotStatuses();

        for (LotStatus status : statuses) {
            System.out.println(status);
        }

        assertEquals(statuses.size(), NUM_LOTS);
    }
}
