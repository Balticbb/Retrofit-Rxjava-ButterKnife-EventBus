package org.baltic.retrofit;

import jsonbean.JsonDataClass;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Baltic on 2016/10/8.
 */

public interface RxCallbackServer {
    @GET("HttpServer/GsonServerServlet")
    Observable<JsonDataClass> getTopMovie();
}
