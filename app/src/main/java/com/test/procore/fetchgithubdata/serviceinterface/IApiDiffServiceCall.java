package com.test.procore.fetchgithubdata.serviceinterface;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IApiDiffServiceCall {

    // TODO Look for better Method
    String GITHUB_DIFF_BASEURL = "https://github.com/";
    @GET("bitcoin/bitcoin/pull/{repoStr}")
    Call<String> getStringResponse(
    @Path("repoStr") String repoPath
    );
}
