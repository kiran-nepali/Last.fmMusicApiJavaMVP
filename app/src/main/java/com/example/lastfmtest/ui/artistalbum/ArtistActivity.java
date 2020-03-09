package com.example.lastfmtest.ui.artistalbum;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lastfmtest.R;
import com.example.lastfmtest.model.BaseArtistAlbum;
import com.example.lastfmtest.presenter.artist.ArtistPresenter;
import com.example.lastfmtest.ui.albumdetails.AlbumDetailsActivity;

public class ArtistActivity extends AppCompatActivity implements ArtistContract.View {

    ArtistPresenter presenter;
    EditText et_artistName;
    RecyclerView rvAlbum;
    Button btn_go;
    ArtistAlbumAdapter artistAlbumAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_artistName = findViewById(R.id.et_artistName);
        rvAlbum = findViewById(R.id.rv_album);
        btn_go = findViewById(R.id.btn_go);
        presenter = new ArtistPresenter(this);
        btn_go.setOnClickListener(view -> presenter.getArtist(et_artistName.getText().toString()));
        rvAlbum.setLayoutManager(new GridLayoutManager(ArtistActivity.this, 2));
    }

    @Override
    public void displayArtist(BaseArtistAlbum artist) {
        if (artist != null) {
            artistAlbumAdapter = new ArtistAlbumAdapter(artist, ArtistActivity.this, album -> {
                Intent intent = new Intent(this, AlbumDetailsActivity.class);
                intent.putExtra("albumName", album.getName());
                intent.putExtra("playcount", album.getPlaycount());
//                intent.putExtra("albumdetails",album);
                intent.putExtra("artistnme", album.getArtist().getName());
                intent.putExtra("albumimage", album.getImage().get(3).getText());
                startActivity(intent);
            });
            rvAlbum.setAdapter(artistAlbumAdapter);
        } else {
            Log.d("artistresponsenull", " response is null");
        }
    }

    @Override
    public void displayNoNetworkFound() {

    }

    @Override
    public void displayError(String error) {
        Toast.makeText(ArtistActivity.this, error, Toast.LENGTH_SHORT).show();
    }


}
