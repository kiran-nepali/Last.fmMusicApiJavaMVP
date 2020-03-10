package com.example.lastfmtest.presenter.artist;

import com.example.lastfmtest.Constant.Constant;
import com.example.lastfmtest.model.Album;
import com.example.lastfmtest.model.BaseArtistAlbum;
import com.example.lastfmtest.network.ArtistAlbumWebservice;
import com.example.lastfmtest.ui.artistalbum.ArtistContract;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.net.UnknownHostException;

import io.reactivex.Single;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ArtistPresenterTest {

    @Mock
    private ArtistContract.View viewmock;

    @Mock
    private ArtistAlbumWebservice artistAlbumWebservicemock;
    @Mock
    private BaseArtistAlbum baseArtistAlbum;
    private ArtistPresenter presenter;
    private String errormsg = "Http 404";

    private Album album = new Album("somebody", 235, "asg");

    @Before
    public void setUp() throws Exception {
        presenter = new ArtistPresenter(viewmock, artistAlbumWebservicemock);
    }


    @Test
    public void whenApiisSuccessful_returns_successfulResponse() {
        when(artistAlbumWebservicemock.searchArtist(Constant.GET_TOP_ALBUM, "justin", Constant.API_KEY, Constant.FORMAT)).thenReturn(Single.just(baseArtistAlbum));
        presenter.getArtist("justin");
        verify(viewmock).displayArtist(baseArtistAlbum);
        verify(viewmock, never()).displayError(errormsg);
    }

    @Test
    public void whenApiIsFailure_returns_errorResponse() {
        when(artistAlbumWebservicemock.searchArtist(Constant.GET_TOP_ALBUM, "justin", Constant.API_KEY, Constant.FORMAT)).thenReturn(Single.error(new RuntimeException(errormsg)));
        presenter.getArtist("justin");
        verify(viewmock).displayError(errormsg);
    }

    @Test
    public void whenThereIsNoNetwork_returnsUnKnownHostException() {
        when(artistAlbumWebservicemock.searchArtist(Constant.GET_TOP_ALBUM, "justin", Constant.API_KEY, Constant.FORMAT)).thenReturn(Single.error(new UnknownHostException()));
        presenter.getArtist("justin");
        verify(viewmock).displayError(null);
        verify(viewmock, never()).displayArtist(baseArtistAlbum);
    }


}