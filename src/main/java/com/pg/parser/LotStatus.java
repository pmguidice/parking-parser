package com.pg.parser;

public class LotStatus {
    private String stationName = null;

    private String lotName = null;

    private Status status = null;

    public LotStatus(String stationName, String lotName, Status status) {
        this.stationName = stationName;
        this.lotName = lotName;
        this.status = status;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getLotName() {
        return lotName;
    }

    public void setLotName(String lotName) {
        this.lotName = lotName;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "LotStatus{" +
            "stationName='" + stationName + '\'' +
            ", lotName='" + lotName + '\'' +
            ", status=" + status +
            '}';
    }
}
