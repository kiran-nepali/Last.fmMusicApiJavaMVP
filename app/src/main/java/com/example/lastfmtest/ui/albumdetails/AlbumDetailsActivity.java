package com.example.lastfmtest.ui.albumdetails;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lastfmtest.R;
import com.squareup.picasso.Picasso;

public class AlbumDetailsActivity extends AppCompatActivity {

    private TextView tv_albumNameDetails, tv_playcount, tv_artistName;
    private ImageView iv_albumDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_details);
        initialiseUI();
        setAlbumDetailsIntent();
//        Album album = getIntent().getParcelableExtra("albumdetails");
    }

    private void initialiseUI() {
        tv_albumNameDetails = findViewById(R.id.tv_albumDetailsName);
        tv_playcount = findViewById(R.id.tv_playcount);
        tv_artistName = findViewById(R.id.tv_artistName);
        iv_albumDetails = findViewById(R.id.iv_albumDetails);
    }

    private void setAlbumDetailsIntent() {
        Integer playcount = getIntent().getIntExtra("playcount", 0);
        String albumname = getIntent().getStringExtra("albumName");
        String artistname = getIntent().getStringExtra("artistnme");
        String albumimage = getIntent().getStringExtra("albumimage");
        tv_albumNameDetails.setText(albumname);
        tv_playcount.setText(String.valueOf(playcount));
        tv_artistName.setText(artistname);
        assert albumimage != null;
        if (albumimage.isEmpty()) {
            iv_albumDetails.setImageResource(R.drawable.noimage);
        } else {
            Picasso.get().load(albumimage).into(iv_albumDetails);
        }
    }
}
