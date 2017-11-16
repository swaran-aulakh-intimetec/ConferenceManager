package com.intimetec.conferencemanager.api;

import com.intimetec.conferencemanager.model.conference.ConferenceRooms;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * @author Swarn Singh.
 */

public interface ConferenceAPI {

    @Headers({"Content-Type:application/json"})
    @GET("api/v1/conferences")
    Observable<ConferenceRooms> getConferenceRooms();
}
