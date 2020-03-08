package com.example.lastfmtest.presenter;

import com.example.lastfmtest.Constant.Constant;
import com.example.lastfmtest.base.BasePresenter;
import com.example.lastfmtest.model.BaseArtistAlbum;
import com.example.lastfmtest.network.RetrofitInstance;
import com.example.lastfmtest.network.Webservice;
import com.example.lastfmtest.ui.ArtistContract;

import java.net.UnknownHostException;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ArtistPresenter extends BasePresenter<ArtistContract.View> implements ArtistContract.Presenter{

    Webservice webservice = RetrofitInstance.getRetrofitInstance().create(Webservice.class);
    ArtistContract.View view;

    public ArtistPresenter(ArtistContract.View view) {
        this.view = view;
    }

    @Override
    public void getArtist(String artist) {
            webservice.getArtist(artist,Constant.GET_TOP_ALBUM,Constant.API_KEY)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(getObserver());
    }

    private SingleObserver<BaseArtistAlbum> getObserver() {
        return new SingleObserver<BaseArtistAlbum>() {
            @Override
            public void onSubscribe(Disposable d) {
                d.dispose();
            }

            @Override
            public void onSuccess(BaseArtistAlbum baseArtistAlbum) {
                view.displayArtist(baseArtistAlbum);
            }

            @Override
            public void onError(Throwable e) {
                view.displayError(e.getLocalizedMessage());
            }
        };
    }
}
