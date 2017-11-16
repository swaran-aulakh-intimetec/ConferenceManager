package com.intimetec.conferencemanager.api;

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Swarn Singh.
 */

public class APIClient {

    public static final String BASE_URL = "http://10.0.2.2:8080/HRMRest/";

    private retrofit2.Retrofit.Builder adapterBuilder;

    public APIClient() {
        createDefaultAdapter();
    }

    public void createDefaultAdapter() {
        adapterBuilder = (new retrofit2.Retrofit.Builder())
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());
    }

    public <S> S createService(Class<S> serviceClass) {
        return this.adapterBuilder.build().create(serviceClass);
    }
}
