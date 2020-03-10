package com.example.lastfmtest.network;

import com.example.lastfmtest.model.BaseArtistAlbum;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Webservice {
    @GET("2.0/")
    Single<BaseArtistAlbum> getArtist(@Query("method") String method, @Query("artist") String artist, @Query("api_key") String apikey, @Query("format") String format);
}
