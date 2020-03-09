package com.example.lastfmtest.presenter.artist;

import android.util.Log;

import com.example.lastfmtest.Constant.Constant;
import com.example.lastfmtest.model.BaseArtistAlbum;
import com.example.lastfmtest.network.RetrofitInstance;
import com.example.lastfmtest.network.Webservice;
import com.example.lastfmtest.ui.artistalbum.ArtistContract;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ArtistPresenter implements ArtistContract.Presenter {

    private Webservice webservice = RetrofitInstance.getRetrofitInstance().create(Webservice.class);
    private ArtistContract.View view;
    private CompositeDisposable disposable;

    public ArtistPresenter(ArtistContract.View view) {
        this.view = view;
    }

    @Override
    public void getArtist(String artist) {
        getObservable(artist).subscribe(getObserver());
    }

    private Single<BaseArtistAlbum> getObservable(String artist) {
        return webservice.getArtist(Constant.GET_TOP_ALBUM, artist, Constant.API_KEY, Constant.FORMAT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private SingleObserver<BaseArtistAlbum> getObserver() {
        return new SingleObserver<BaseArtistAlbum>() {
            @Override
            public void onSubscribe(Disposable d) {
                d.isDisposed();
            }

            @Override
            public void onSuccess(BaseArtistAlbum baseArtistAlbum) {
                Log.d("artistresponse", baseArtistAlbum.getTopalbums().getAlbum().get(0).getName());
                view.displayArtist(baseArtistAlbum);
            }

            @Override
            public void onError(Throwable e) {
                view.displayError(e.getLocalizedMessage());
            }
        };
    }
}
