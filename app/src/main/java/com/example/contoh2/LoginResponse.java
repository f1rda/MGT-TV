package com.example.contoh2;

import com.google.gson.annotations.SerializedName;

public class LoginResponse{

    @SerializedName("user_id")
    private String userId;

    @SerializedName("origin")
    private String origin;

    @SerializedName("zone_name")
    private String zoneName;

    @SerializedName("branch")
    private String branch;

    @SerializedName("status")
    private boolean status;

    @SerializedName("zone_code")
    private String zoneCode;

    @SerializedName("error_reason")
    private String errorReason;

    public String getErrorReason() {
        return errorReason;
    }

    public String getUserId(){
        return userId;
    }

    public String getOrigin(){
        return origin;
    }

    public String getZoneName(){
        return zoneName;
    }

    public String getBranch(){
        return branch;
    }

    public boolean isStatus(){
        return status;
    }

    public String getZoneCode(){
        return zoneCode;
    }
}