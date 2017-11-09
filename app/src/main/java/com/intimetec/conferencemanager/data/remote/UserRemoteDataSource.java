package com.intimetec.conferencemanager.data.remote;

import com.intimetec.conferencemanager.api.APIClient;
import com.intimetec.conferencemanager.api.UserAPI;
import com.intimetec.conferencemanager.data.UserDataSource;
import com.intimetec.conferencemanager.model.user.User;
import com.intimetec.conferencemanager.request.login.CreateLoginRequest;

import io.reactivex.Observable;

/**
 * @author Swarn Singh.
 */

public class UserRemoteDataSource implements UserDataSource {

    private static UserRemoteDataSource sInstance;

    private UserAPI mUserAPI;


    public static UserRemoteDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new UserRemoteDataSource(UserAPI.class);
        }
        return sInstance;
    }

    private  UserRemoteDataSource(Class<UserAPI> serviceClass) {
        mUserAPI = new APIClient().createService(serviceClass);
    }

    @Override
    public Observable<User> getUser(String email, String password) {
        CreateLoginRequest createLoginRequest = new CreateLoginRequest();
        createLoginRequest.setEmail(email);
        createLoginRequest.setPassword(password);

        return mUserAPI.getUser(createLoginRequest);
    }
}
