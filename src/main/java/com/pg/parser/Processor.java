package com.pg.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pg.parser.beans.LotStatus;
import com.pg.parser.beans.StationStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Processor {
    Logger logger = LoggerFactory.getLogger(Processor.class);

    public void process() {
        try {
            Parser parser = new Parser();
            List<LotStatus> lotStatuses = parser.getLotStatuses();

            List<StationStatus> stationStatuses = buildStationStatuses(lotStatuses);

            CacheClient cacheClient = new CacheClient();
            for (StationStatus stationStatus : stationStatuses) {
                cacheClient.set(stationStatus.getStationName(), serialize(stationStatus.getLots()));
            }
        } catch (Exception e) {
            logger.error("Error processing", e);
        }
    }

    protected List<StationStatus> buildStationStatuses(List<LotStatus> lotStatuses) throws Exception {
        List<StationStatus> stationStatuses = new ArrayList<>();

        Map<String, List<LotStatus>> stationMap = new HashMap<>();

        try {
            for (LotStatus lotStatus : lotStatuses) {
                if (stationMap.get(lotStatus.getStationName()) == null) {
                    ArrayList<LotStatus> tempList = new ArrayList<>();
                    tempList.add(lotStatus);

                    stationMap.put(lotStatus.getStationName(), tempList);
                } else {
                    List<LotStatus> tempList = stationMap.get(lotStatus.getStationName());
                    tempList.add(lotStatus);

                    stationMap.put(lotStatus.getStationName(), tempList);
                }
            }

            for (Map.Entry<String, List<LotStatus>> entry : stationMap.entrySet()) {
                stationStatuses.add(new StationStatus(entry.getKey(), entry.getValue()));
            }
        } catch (Exception e) {
            throw new Exception("Error build station statuses", e);
        }

        return stationStatuses;
    }

    public String serialize(Object obj) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
}
