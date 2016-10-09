package org.baltic.retrofit;

import jsonbean.JsonDataClass;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Baltic on 2016/10/8.
 */

public interface ResponseCallBackServer {
    @GET("HttpServer/GsonServerServlet")
    Call<JsonDataClass> getTopMovie();
}
