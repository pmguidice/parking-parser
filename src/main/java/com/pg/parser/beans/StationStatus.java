package com.pg.parser.beans;

import java.util.List;

public class StationStatus {
    private String stationName = null;

    private List<LotStatus> lots = null;

    public StationStatus(String stationName, List<LotStatus> lots) {
        this.stationName = stationName;
        this.lots = lots;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public List<LotStatus> getLots() {
        return lots;
    }

    public void setLots(List<LotStatus> lots) {
        this.lots = lots;
    }

    @Override
    public String toString() {
        return "StationStatus{" +
            "stationName='" + stationName + '\'' +
            ", lots=" + lots +
            '}';
    }
}
