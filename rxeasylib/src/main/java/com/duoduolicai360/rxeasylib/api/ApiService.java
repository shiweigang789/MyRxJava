package com.duoduolicai360.rxeasylib.api;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by swg on 2017/9/7.
 */

public interface ApiService {

    @POST()
    @FormUrlEncoded
    Observable<RequestBody> post(@Url String url, @FieldMap Map<String, String> map);

    @POST()
    Observable<RequestBody> postBody(@Url String url, @Body Object object);

    @GET()
    Observable<ResponseBody> get(@Url String url, @QueryMap Map<String, String> map);

    @DELETE()
    Observable<ResponseBody> delete(@Url String url, @QueryMap Map<String, String> map);

    @PUT()
    Observable<RequestBody> put(@Url String url, @QueryMap Map<String, String > map);

    @POST()
    Observable<RequestBody> putBody(@Url String url, @Body Object object);

    @Multipart
    @POST()
    Observable<RequestBody> uploadFile(@Url String url, @Part("description") RequestBody description, @Part("files") MultipartBody.Part file);

    @Multipart
    @POST()
    Observable<RequestBody> uploadFiles(@Url String url, @PartMap Map<String, RequestBody> map);

    @Multipart
    @POST()
    Observable<RequestBody> uploadFiles(@Url String url, @Part List<MultipartBody.Part> parts);

    @Streaming
    @GET
    Observable<ResponseBody> downloadFile(@Url String fileUrl);

    @POST()
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    Observable<RequestBody> postJson(@Url String url, @Body RequestBody jsonBody);

    @POST()
    Observable<RequestBody> postBody(@Url String url, @Body RequestBody body);


}
