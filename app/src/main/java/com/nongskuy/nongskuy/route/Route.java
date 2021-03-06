package com.nongskuy.nongskuy.route;

import com.nongskuy.nongskuy.model.AuthClass;
import com.nongskuy.nongskuy.model.MenuClass;
import com.nongskuy.nongskuy.model.MessageClass;
import com.nongskuy.nongskuy.model.MetodeBayarNongskuyClass;
import com.nongskuy.nongskuy.model.NongskuyClass;
import com.nongskuy.nongskuy.model.NongskuyTerdekatClass;
import com.nongskuy.nongskuy.model.PromoClass;
import com.nongskuy.nongskuy.model.ReviewClass;
import com.nongskuy.nongskuy.model.RiwayatNongskuyClass;
import com.nongskuy.nongskuy.model.PencarianClass;
import com.nongskuy.nongskuy.model.NongskuyPopulerClass;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Route {

    @FormUrlEncoded
    @POST("login")
    Call<AuthClass> login(@Field("email") String email, @Field("password") String password);

    @POST("logout")
    Call<MessageClass> logout(@Header("Authorization") String token);

    @FormUrlEncoded
    @POST("register")
    Call<MessageClass> register(@Field("email") String email, @Field("nama") String nama,
                                @Field("no_hp") String no_hp, @Field("password") String password);

    @FormUrlEncoded
    @PUT("ubahpassword")
    Call<MessageClass> ubahPassword(@Header("Authorization") String token,
                                    @Field("password") String password);

    @FormUrlEncoded
    @PUT("ubahprofil")
    Call<MessageClass> ubahProfil(@Header("Authorization") String token,
                                  @Field("nama") String nama,
                                  @Field("no_hp") String no_hp);

    @GET("promo")
    Call<PromoClass> promo(@Header("Authorization") String token);

    @GET("pesan/riwayat")
    Call<RiwayatNongskuyClass> riwayat(@Header("Authorization") String token);

    @GET("nongskuy/populer")
    Call<NongskuyPopulerClass> tokoPopuler(@Query("latitude") Double latitude,
                                           @Query("longitude") Double longitude);

    @FormUrlEncoded
    @POST("nongskuy/search")
    Call<PencarianClass> search(@Field("keyword") String keyword,
                                @Query("latitude") Double latitude,
                                @Query("longitude") Double longitude);

    @GET("nongskuy/terdekat")
    Call<NongskuyTerdekatClass> terdekat(@Query("latitude") Double latitude,
                                         @Query("longitude") Double longitude);

    @GET("nongskuy/{id}")
    Call<NongskuyClass> show(@Path("id") Integer idToko);

    @GET("nongskuy/{id}/review")
    Call<ReviewClass> review(@Path("id") Integer idToko);

    @GET("nongskuy/{id}/menu")
    Call<MenuClass> menu(@Path("id") Integer idToko);

    @GET("nongskuy/{id}/menu")
    Call<MenuClass> menu(@Path("id") Integer idToko, @Query("guest") boolean guest);

    @GET("nongskuy/{id}.metodebayar")
    Call<MetodeBayarNongskuyClass> metodeBayarNongskuy(@Path("id") Integer id, @Header("Authorization") String token);

    @FormUrlEncoded
    @POST("nongskuy/{id}/review")
    Call<MessageClass> addReview(@Path("id") Integer idToko, @Field("rating") Double rating,
                                 @Field("komentar") String komentar,
                                 @Header("Authorization") String token);

}
