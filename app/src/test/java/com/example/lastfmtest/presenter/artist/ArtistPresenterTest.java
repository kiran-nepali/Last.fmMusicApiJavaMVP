package com.example.lastfmtest.presenter.artist;

import com.example.lastfmtest.Constant.Constant;
import com.example.lastfmtest.network.Webservice;
import com.example.lastfmtest.ui.artistalbum.ArtistContract;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Single;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ArtistPresenterTest {

    @Mock
    private ArtistContract.View viewmock;
    @Mock
    private Webservice webservicemock;
    private ArtistPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new ArtistPresenter(viewmock);
    }

    @Test
    public void whenApiisSuccessful_providesEmptyAlbumList(){
        when(webservicemock.getArtist(Constant.GET_TOP_ALBUM,"justin",Constant.API_KEY,Constant.FORMAT)).thenReturn(Single.just())
    }
}