
package com.example.projectxbloodbank.model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DonationInfoResponse implements Serializable
{

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("blood_group")
    @Expose
    private String bloodGroup;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("last_donated_at")
    @Expose
    private String lastDonatedAt;
    @SerializedName("total_danate")
    @Expose
    private String totalDonate;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLastDonatedAt() {
        return lastDonatedAt;
    }

    public void setLastDonatedAt(String lastDonatedAt) {
        this.lastDonatedAt = lastDonatedAt;
    }

    public String getTotalDanate() {
        return totalDonate;
    }

    public void setTotalDanate(String totalDanate) {
        this.totalDonate = totalDanate;
    }

}
