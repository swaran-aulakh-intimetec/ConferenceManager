package com.intimetec.conferencemanager.model.conference;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Swarn Singh.
 */

public class ConferenceRooms {

    @SerializedName("data")
    @Expose
    private List<Data> data = null;

    @SerializedName("message")
    @Expose
    private String message;

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class Data {

        @SerializedName("resource_id")
        @Expose
        private String resourceId;

        @SerializedName("name")
        @Expose
        private String name;

        @SerializedName("location")
        @Expose
        private String location;

        @SerializedName("max_participants")
        @Expose
        private String maxParticipants;

        public String getResourceId() {
            return resourceId;
        }

        public void setResourceId(String resourceId) {
            this.resourceId = resourceId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getMaxParticipants() {
            return maxParticipants;
        }

        public void setMaxParticipants(String maxParticipants) {
            this.maxParticipants = maxParticipants;
        }
    }
}
