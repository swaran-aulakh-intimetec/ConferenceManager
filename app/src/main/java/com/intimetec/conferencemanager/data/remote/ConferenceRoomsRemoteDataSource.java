package com.intimetec.conferencemanager.data.remote;

import com.intimetec.conferencemanager.api.APIClient;
import com.intimetec.conferencemanager.api.ConferenceAPI;
import com.intimetec.conferencemanager.data.ConferenceRoomsDataSource;
import com.intimetec.conferencemanager.model.conference.ConferenceRooms;

import io.reactivex.Observable;

/**
 * @author Swarn Singh.
 */

public class ConferenceRoomsRemoteDataSource implements ConferenceRoomsDataSource {

    private static ConferenceRoomsRemoteDataSource sInstance;

    private ConferenceAPI mConferenceAPI;

    public static ConferenceRoomsRemoteDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new ConferenceRoomsRemoteDataSource(ConferenceAPI.class);
        }
        return sInstance;
    }

    private  ConferenceRoomsRemoteDataSource(Class<ConferenceAPI> serviceClass) {
        mConferenceAPI = new APIClient().createService(serviceClass);
    }

    @Override
    public Observable<ConferenceRooms> getConferenceRooms() {
        return mConferenceAPI.getConferenceRooms();
    }
}
