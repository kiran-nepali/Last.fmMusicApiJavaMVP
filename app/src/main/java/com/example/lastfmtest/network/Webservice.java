package com.example.lastfmtest.network;

import com.example.lastfmtest.model.BaseArtistAlbum;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Webservice {
    @GET("2.0/{artist}")
    Single<BaseArtistAlbum> getArtist(@Path("artist") String artist,@Query("method") String method, @Query("api_key") String apikey);
}
