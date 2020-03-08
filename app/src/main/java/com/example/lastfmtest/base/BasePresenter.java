package com.example.lastfmtest.base;

import io.reactivex.disposables.CompositeDisposable;

public class BasePresenter<View> {
    protected CompositeDisposable disposable = new CompositeDisposable();
    View view = null;

    public void attachView(View view){
        this.view = view;
    }

    public void detachView(View view){
        disposable.clear();
        this.view = null;
    }
}
