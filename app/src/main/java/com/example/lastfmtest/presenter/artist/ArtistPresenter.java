package com.example.lastfmtest.presenter.artist;

import com.example.lastfmtest.Constant.Constant;
import com.example.lastfmtest.model.BaseArtistAlbum;
import com.example.lastfmtest.network.ArtistAlbumWebservice;
import com.example.lastfmtest.ui.artistalbum.ArtistContract;

import java.net.UnknownHostException;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class ArtistPresenter implements ArtistContract.Presenter {

    private ArtistAlbumWebservice artistAlbumWebservice;
    private ArtistContract.View view;
    private CompositeDisposable disposable;

    public ArtistPresenter(ArtistContract.View view, ArtistAlbumWebservice artistAlbumWebservice) {
        this.view = view;
        this.artistAlbumWebservice = artistAlbumWebservice;
    }

    @Override
    public void getArtist(String artist) {
        artistAlbumWebservice.searchArtist(Constant.GET_TOP_ALBUM, artist, Constant.API_KEY, Constant.FORMAT).subscribe(getObserver());
    }

    private SingleObserver<BaseArtistAlbum> getObserver() {
        return new SingleObserver<BaseArtistAlbum>() {
            @Override
            public void onSubscribe(Disposable d) {
                d.isDisposed();
            }

            @Override
            public void onSuccess(BaseArtistAlbum baseArtistAlbum) {
                view.displayArtist(baseArtistAlbum);
            }

            @Override
            public void onError(Throwable e) {
                if (e == new UnknownHostException()) {
                    view.displayNoNetworkFound();
                } else {
                    view.displayError(e.getLocalizedMessage());
                }
            }
        };
    }
}
