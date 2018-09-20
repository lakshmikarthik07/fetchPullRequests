package com.test.procore.fetchgithubdata.serviceinterface;
import com.test.procore.fetchgithubdata.utils.JsonPojoClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IApiService {

    String GITHUB_BASE_URL = "https://api.github.com/repos/bitcoin/bitcoin/";
    @GET("pulls")
    Call<List<JsonPojoClass>> getPullRequestList();
}
