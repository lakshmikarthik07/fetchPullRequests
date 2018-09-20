package com.test.procore.fetchgithubdata.ServiceInterface;
import com.test.procore.fetchgithubdata.utils.JsonPojoClass;

import java.util.List;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IApiService {

    String GITHUB_BASE_URL = "https://api.github.com/repos/zcoinofficial/zcoin/";
    @GET("pulls")
    Call<List<JsonPojoClass>> getPullRequestList();
}
