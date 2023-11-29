package com.base.skillbuilderapi.model.elementProgress;

import com.base.skillbuilderapi.JsonInfo;
import com.base.skillbuilderapi.MockJson;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class GetElementProgressOutput {
    @SerializedName("status")
    private int status;
    @SerializedName("message")
    private String message;
    @SerializedName("last_updated")
    private long lastUpdated;
    @SerializedName("data")
    private GetElementProgressOutputData data;

    public GetElementProgressOutput(int status, String message, long lastUpdated, GetElementProgressOutputData data) {
        this.status = status;
        this.message = message;
        this.lastUpdated = lastUpdated;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public GetElementProgressOutputData getData() {
        return data;
    }

    public void setData(GetElementProgressOutputData data) {
        this.data = data;
    }
    String jsonString = MockJson.JSON_DATA;
    public static GetElementProgressOutput fromJson(String jsonString) {
        return new Gson().fromJson(jsonString, GetElementProgressOutput.class);
    }
}
