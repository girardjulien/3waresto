package com.example.a3wresto.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.a3wresto.R;
import com.example.a3wresto.manager.WsManager;
import com.example.a3wresto.manager.WsManager.Listener;
import com.example.a3wresto.model.Recipe;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class RecetteActivity extends AppCompatActivity implements Listener {

    private Gson gson = new Gson();
    private TextView recipeTitle;
    private RatingBar recipeNote;
    private ImageView recipePhoto;
    private TextView preparationDesc;
    private TextView cookDesc;
    private TextView caloriesDesc;
    private TextView ingredientsDesc;
    private TextView instructionsDesc;

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recette);

        recipeTitle = findViewById(R.id.recipe_title);
        recipeNote = findViewById(R.id.recipe_note);
        recipePhoto = findViewById(R.id.recipe_photo);
        preparationDesc = findViewById(R.id.preparation_desc);
        cookDesc = findViewById(R.id.cook_desc);
        caloriesDesc = findViewById(R.id.calories_desc);
        ingredientsDesc = findViewById(R.id.ingredients_desc);
        instructionsDesc = findViewById(R.id.instructions_desc);

        long recipeId = getIntent().getLongExtra(ListeRecettesActivity.SELECTED_RECIPE_ID_KEY, -1);
        WsManager wsManager = new WsManager();
        wsManager.executePostRequest("ws/resto/infoRecette/"+recipeId, this);
    }

    @Override
    public void onFailure(String errorMessage) {
        Log.e("tp9", errorMessage);
    }

    @Override
    public void onSuccess(String content) {
        Recipe recipe = gson.fromJson(content, Recipe.class);

        recipeTitle.setText(recipe.getTitle());
        recipeNote.setRating(recipe.getNote());
        Picasso.get().load(recipe.getPhoto()).into(recipePhoto);
        preparationDesc.setText(recipe.getTempsPreparation()+" min");
        cookDesc.setText(recipe.getTempsCookRime()+" min");
        caloriesDesc.setText(recipe.getCalories()+" kcal");
        ingredientsDesc.setText("* "+recipe.getIngredients().replace("\n", "\n* "));
        instructionsDesc.setText(recipe.getInstructions());
    }
}
