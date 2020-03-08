package com.example.lastfmtest.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lastfmtest.R;
import com.example.lastfmtest.model.BaseArtistAlbum;

public class ArtistAlbumAdapter extends RecyclerView.Adapter<ArtistAlbumAdapter.ArtistAlbumViewHolder>{

    private BaseArtistAlbum baseArtistAlbum;
    public ArtistAlbumAdapter(BaseArtistAlbum baseArtistAlbum) {
        this.baseArtistAlbum = baseArtistAlbum;
    }

    @NonNull
    @Override
    public ArtistAlbumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_viewholder,parent,false);
        return new ArtistAlbumViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistAlbumAdapter.ArtistAlbumViewHolder holder, int position) {
        holder.tv_albumname.setText(baseArtistAlbum.getTopalbums().getAlbum().get(position).getName());
    }

    @Override
    public int getItemCount() {
        return baseArtistAlbum.getTopalbums().getAlbum().size();
    }

    public class ArtistAlbumViewHolder extends RecyclerView.ViewHolder{

        TextView tv_albumname;
        ImageView iv_albumimage;
        public ArtistAlbumViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_albumname = itemView.findViewById(R.id.tv_albumName);
            iv_albumimage = itemView.findViewById(R.id.iv_album);
        }
    }
}


