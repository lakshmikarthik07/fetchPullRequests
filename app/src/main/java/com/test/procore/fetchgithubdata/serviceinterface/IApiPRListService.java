package com.test.procore.fetchgithubdata.serviceinterface;

import com.test.procore.fetchgithubdata.utils.JsonPojoClass;

import java.util.List;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IApiPRListService {

    String GITHUB_BASE_URL = "https://api.github.com/repos/bitcoin/bitcoin/";

    @GET("pulls")
    Call<List<JsonPojoClass>> getPullRequestList();
}

// THIS PROJECT ##
//https://api.github.com/repos/lakshmikarthik07/fetchPullRequests