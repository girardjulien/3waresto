package com.example.a3wresto.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.a3wresto.R;
import com.example.a3wresto.model.Recipe;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.MyViewHolder> {

    private List<Recipe> recipes;
    private RecipeAdapter.ItemClickListener itemClickListener;

    public RecipeAdapter(List<Recipe> recipes, RecipeAdapter.ItemClickListener listener) {
        this.recipes = recipes;
        this.itemClickListener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recipe, viewGroup, false);

        MyViewHolder viewHolder = new MyViewHolder(view, itemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapter.MyViewHolder myViewHolder, int i) {
        Recipe recipe = recipes.get(i);
        myViewHolder.titleTextView.setText(recipe.getTitle());
        Picasso.get().load(recipe.getPhoto()).into(myViewHolder.photoImageView);
        myViewHolder.noteRatingBar.setRating(recipe.getNote());
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        TextView titleTextView;
        ImageView photoImageView;
        RatingBar noteRatingBar;
        private RecipeAdapter.ItemClickListener itemClickListener;


        MyViewHolder(View v, ItemClickListener itemClickListener) {
            super(v);
            this.itemClickListener = itemClickListener;
            titleTextView = v.findViewById(R.id.recipe_title);
            photoImageView = v.findViewById(R.id.recipe_photo);
            noteRatingBar = v.findViewById(R.id.recipe_note);
            v.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Log.d("tp3", "onClick " + position);
            if(itemClickListener != null) {
                itemClickListener.onClickListener(position);
            }
        }
    }

    public interface ItemClickListener {
        void onClickListener(int position);
    }

}


