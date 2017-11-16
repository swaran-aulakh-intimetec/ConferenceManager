package com.intimetec.conferencemanager.data;

import com.intimetec.conferencemanager.model.conference.ConferenceRooms;

import io.reactivex.Observable;

/**
 * @author Swarn Singh.
 */

public interface ConferenceRoomsDataSource {

    public Observable<ConferenceRooms> getConferenceRooms();

}
