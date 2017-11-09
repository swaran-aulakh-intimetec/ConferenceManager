package com.intimetec.conferencemanager.data;

import com.intimetec.conferencemanager.model.user.User;

import io.reactivex.Observable;

/**
 * @author Swarn Singh.
 */

public interface UserDataSource {

    public Observable<User> getUser(String email, String password);

}
