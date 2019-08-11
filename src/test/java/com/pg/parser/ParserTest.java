package com.pg.parser;

import com.pg.parser.beans.LotStatus;
import com.pg.parser.beans.StationStatus;
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

    @Test
    public void testSerialize() throws Exception {
        Parser parser = new Parser();
        Processor processor = new Processor();

        List<LotStatus> statuses = parser.getLotStatuses();

        for (LotStatus status : statuses) {
            String json = processor.serialize(status);
            System.out.println(json);
        }
    }

    @Test
    public void testBuildStationStatuses() throws Exception {
        Parser parser = new Parser();
        Processor processor = new Processor();

        List<LotStatus> lotStatuses = parser.getLotStatuses();

        List<StationStatus> stationStatuses = processor.buildStationStatuses(lotStatuses);

        for (StationStatus status : stationStatuses) {
            String json = processor.serialize(status);
            System.out.println(json);
        }
    }
}
