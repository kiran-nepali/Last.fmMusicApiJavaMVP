package com.example.lastfmtest.ui.artistalbum;

import com.example.lastfmtest.model.BaseArtistAlbum;

public interface ArtistContract {
    interface View {
        void displayArtist(BaseArtistAlbum artist);

        void displayNoNetworkFound();

        void displayError(String error);
    }

    interface Presenter {
        void getArtist(String artist);
    }
}
