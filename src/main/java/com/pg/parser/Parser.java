package com.pg.parser;

import com.pg.parser.beans.LotStatus;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    private static Logger logger = LoggerFactory.getLogger(Parser.class);

    private static final String PARKING_URL = "https://www.itsmarta.com/parking.aspx";

    private static final String PARKING_DIV_ID = "div#adminloginpanel";

    private static final String OPEN_IMG_URI = "images\\Green.png";

    private static final String FULL_IMG_URI = "images\\Red.png";

    public List<LotStatus> getLotStatuses() throws Exception {
        List<LotStatus> lotStatuses = new ArrayList<>();

        try {
            Document doc = getDocument();

            Element parkingDiv = getParkingDiv(doc);

            List<Element> rowElements = getParkingRows(parkingDiv);

            lotStatuses = getLotStatuses(rowElements);
        } catch (Exception e) {
            logger.error("Error parsing lot statuses", e);
        }

        return lotStatuses;
    }

    protected Document getDocument() throws Exception {
        Document doc = null;

        try {
            doc = Jsoup.connect(PARKING_URL).get();
        } catch (Exception e) {
            throw new Exception("Error getting document", e);
        }

        return doc;
    }

    protected Element getParkingDiv(Document doc) throws Exception {
        Element parkingDiv = null;

        try {
            parkingDiv = doc.select(PARKING_DIV_ID).first();
        } catch (Exception e) {
            throw new Exception("Error getting parking div from doc", e);
        }

        return parkingDiv;
    }

    protected List<Element> getParkingRows(Element parkingDiv) throws Exception {
        List<Element> rowElementList = new ArrayList<>();

        try {
            Elements rowElements = parkingDiv.getElementsByTag("table").first().getElementsByTag("tbody").first().
                getElementsByTag("tr");

            rowElementList = rowElements.subList(1, rowElements.size());
        } catch (Exception e) {
            throw new Exception("Error getting rows from parking div", e);
        }

        return rowElementList;
    }

    private List<LotStatus> getLotStatuses(List<Element> rowElements) throws Exception {
        List<LotStatus> lotStatuses = new ArrayList<>();

        try {
            for (Element row : rowElements) {
                Elements cells = row.getElementsByTag("td");

                String stationName = cells.get(0).getElementsByTag("span").first().text().trim();

                String lotName = cells.get(1).getElementsByTag("span").first().text().trim();

                String imgUri = cells.get(2).getElementsByTag("img").first().baseUri();
                Status lotStatus = FULL_IMG_URI.equals(imgUri) ? Status.FULL : Status.OPEN;

                lotStatuses.add(new LotStatus(stationName, lotName, lotStatus));
            }
        } catch (Exception e) {
            throw new Exception("Error getting lot statuses", e);
        }

        return lotStatuses;
    }

    protected String getTitle() {
        String title = null;

        try {
            Document doc = getDocument();

            title = doc.title();

            logger.info("title is {}", title);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return title;
    }
}
