package com.intimetec.conferencemanager.api;

import com.intimetec.conferencemanager.model.user.User;
import com.intimetec.conferencemanager.request.login.CreateLoginRequest;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * @author Swarn Singh.
 */

public interface UserAPI {

    @Headers({"Content-Type:application/json"})
    @POST("api/v1/login")
    Observable<User> getUser(@Body CreateLoginRequest createLoginRequest);
}
