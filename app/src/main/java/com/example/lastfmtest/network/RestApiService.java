package com.example.lastfmtest.network;

import com.example.lastfmtest.model.BaseArtistAlbum;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RestApiService implements ArtistAlbumWebservice {
    private Webservice webservice;

    public RestApiService(Webservice webservice) {
        this.webservice = webservice;
    }

    @Override
    public Single<BaseArtistAlbum> searchArtist(String method, String artist, String apikey, String format) {
        return webservice.getArtist(method, artist, apikey, format).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
    }
}
