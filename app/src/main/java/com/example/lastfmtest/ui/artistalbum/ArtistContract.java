package com.example.lastfmtest.ui.artistalbum;

import com.example.lastfmtest.model.BaseArtistAlbum;

public interface ArtistContract {
    interface View{
        public void displayArtist(BaseArtistAlbum artist);
        public void displayNoNetworkFound();
        public void displayError(String error);
    }

    interface Presenter{
        public void getArtist(String artist);
    }
}
