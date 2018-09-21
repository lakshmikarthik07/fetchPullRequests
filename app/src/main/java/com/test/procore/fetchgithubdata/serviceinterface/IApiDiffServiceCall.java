package com.test.procore.fetchgithubdata.serviceinterface;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface IApiDiffServiceCall {
    @GET()
    Call<String> getStringResponse(@Url String url);
}
