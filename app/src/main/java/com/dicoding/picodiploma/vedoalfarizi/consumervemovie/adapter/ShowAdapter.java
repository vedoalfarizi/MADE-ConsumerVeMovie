package com.dicoding.picodiploma.vedoalfarizi.consumervemovie.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dicoding.picodiploma.vedoalfarizi.consumervemovie.R;
import com.dicoding.picodiploma.vedoalfarizi.consumervemovie.model.Show;

import java.util.ArrayList;

import static com.dicoding.picodiploma.vedoalfarizi.consumervemovie.Provider.POSTER_URL;

public class ShowAdapter extends RecyclerView.Adapter<ShowAdapter.ShowViewHolder> {
    private ArrayList<Show> shows;
    private OnItemClick onItemClick;

    public ShowAdapter(){

    }

    public void setShows(ArrayList<Show> shows) {
        this.shows = shows;
        notifyDataSetChanged();
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public ShowViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_show, viewGroup, false);
        return new ShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ShowViewHolder showViewHolder, int i) {
        Show show = shows.get(i);

        String poster_path = POSTER_URL +show.getPhoto();

        Glide.with(showViewHolder.itemView.getContext())
                .load(poster_path)
                .into(showViewHolder.imgPoster);

        showViewHolder.tvTitle.setText(show.getTitle());
        showViewHolder.tvRating.setText(String.valueOf(show.getRating()));
        showViewHolder.tvPremiereDate.setText(show.getPremiere());
        showViewHolder.rbRating.setRating(show.getRating()/2);

        showViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.click(shows.get(showViewHolder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        if(shows != null){
            return shows.size();
        }else{
            return 0;
        }
    }

    class ShowViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPoster;
        TextView tvTitle, tvRating, tvPremiereDate;
        RatingBar rbRating;

        ShowViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPoster = itemView.findViewById(R.id.img_poster);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvRating = itemView.findViewById(R.id.tv_rating);
            tvPremiereDate = itemView.findViewById(R.id.tv_premiere_date);
            rbRating = itemView.findViewById(R.id.rb_rating);
        }
    }

    public interface OnItemClick{
        void click(Show s);
    }
}
