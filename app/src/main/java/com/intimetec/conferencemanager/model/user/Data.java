package com.intimetec.conferencemanager.model.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Swarn Singh.
 */

public class Data {

    @SerializedName("fname")
    @Expose
    private String fname;
    @SerializedName("date_created")
    @Expose
    private String dateCreated;
    @SerializedName("lastlogin")
    @Expose
    private String lastlogin;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("public_id")
    @Expose
    private String publicId;
    @SerializedName("lname")
    @Expose
    private String lname;
    @SerializedName("status_id")
    @Expose
    private String statusId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("phone")
    @Expose
    private Object phone;
    @SerializedName("organization")
    @Expose
    private String organization;
    @SerializedName("legacyid")
    @Expose
    private Object legacyid;
    @SerializedName("position")
    @Expose
    private String position;
    @SerializedName("last_modified")
    @Expose
    private String lastModified;
    @SerializedName("email")
    @Expose
    private String email;

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getLastlogin() {
        return lastlogin;
    }

    public void setLastlogin(String lastlogin) {
        this.lastlogin = lastlogin;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Object getPhone() {
        return phone;
    }

    public void setPhone(Object phone) {
        this.phone = phone;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public Object getLegacyid() {
        return legacyid;
    }

    public void setLegacyid(Object legacyid) {
        this.legacyid = legacyid;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}