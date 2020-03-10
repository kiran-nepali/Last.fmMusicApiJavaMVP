package com.example.lastfmtest.network;

import com.example.lastfmtest.model.BaseArtistAlbum;

import io.reactivex.Single;

public interface ArtistAlbumWebservice {
    Single<BaseArtistAlbum> searchArtist(String method, String artist, String apikey, String format);
}
