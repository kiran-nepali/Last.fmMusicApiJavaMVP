package com.example.lastfmtest.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lastfmtest.R;
import com.example.lastfmtest.model.BaseArtistAlbum;
import com.example.lastfmtest.presenter.ArtistPresenter;

public class MainActivity extends AppCompatActivity implements ArtistContract.View {

    ArtistPresenter presenter = new ArtistPresenter(this);
    EditText et_artistName;
    RecyclerView rvAlbum;
    private ArtistAlbumAdapter artistAlbumAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_artistName = findViewById(R.id.et_artistName);
        rvAlbum = findViewById(R.id.rv_artist);
        et_artistName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String artist = editable.toString();

                presenter.getArtist(artist);
            }
        });
    }

    @Override
    public void displayArtist(BaseArtistAlbum artist) {
        rvAlbum.setLayoutManager(new LinearLayoutManager(this));
        if (artist != null) {
            artistAlbumAdapter = new ArtistAlbumAdapter(artist);
            rvAlbum.setAdapter(artistAlbumAdapter);
        }
    }

    @Override
    public void displayNoNetworkFound() {

    }

    @Override
    public void displayError(String error) {

    }
}
