package com.example.lastfmtest.ui.artistalbum;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lastfmtest.R;
import com.example.lastfmtest.model.Album;
import com.example.lastfmtest.model.BaseArtistAlbum;
import com.squareup.picasso.Picasso;

public class ArtistAlbumAdapter extends RecyclerView.Adapter<ArtistAlbumAdapter.ArtistAlbumViewHolder>{

    private BaseArtistAlbum baseArtistAlbum;
    private AlbumOnClickListener listener;
    private Context context;
    ArtistAlbumAdapter(BaseArtistAlbum baseArtistAlbum,Context context,AlbumOnClickListener listener) {
        this.baseArtistAlbum = baseArtistAlbum;
        this.listener = listener;
        this.context = context;
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
        String imgurl = baseArtistAlbum.getTopalbums().getAlbum().get(position).getImage().get(3).getText();
        if (imgurl.isEmpty()){
            holder.iv_albumimage.setImageResource(R.drawable.noimage);
        }else{
            Picasso.get().load(imgurl).into(holder.iv_albumimage);
        }
        holder.bind(baseArtistAlbum.getTopalbums().getAlbum().get(position),listener);
    }

    @Override
    public int getItemCount() {
        return baseArtistAlbum.getTopalbums().getAlbum().size();
    }

    static class ArtistAlbumViewHolder extends RecyclerView.ViewHolder{

        TextView tv_albumname;
        ImageView iv_albumimage;
        ArtistAlbumViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_albumname = itemView.findViewById(R.id.tv_albumName);
            iv_albumimage = itemView.findViewById(R.id.iv_album);
        }

        void bind(Album album,AlbumOnClickListener listener){
            itemView.setOnClickListener(view -> {
                listener.onalbumclick(album);
            });
        }
    }
}


