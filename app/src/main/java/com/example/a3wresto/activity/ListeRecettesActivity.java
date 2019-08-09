package com.example.a3wresto.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.a3wresto.R;
import com.example.a3wresto.adapter.RecipeAdapter;
import com.example.a3wresto.manager.WsManager;
import com.example.a3wresto.model.Recipe;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class ListeRecettesActivity extends AppCompatActivity implements WsManager.Listener, RecipeAdapter.ItemClickListener {

    public static final String SELECTED_RECIPE_ID_KEY = "SELECTED_RECIPE_ID_KEY";
    private RecyclerView recyclerView;
    private Gson gson = new Gson();
    private List<Recipe> recipeList;

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_deconnexion:
                SharedPreferences sharedPref = getSharedPreferences("Profil", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.remove("email");
                editor.remove("nom");
                editor.remove("prenom");
                editor.apply();
                startActivity(new Intent(this, ConnexionActivity.class));
                return true;
            case R.id.action_profil:
                startActivity(new Intent(this, ProfilActivity.class));
                return true;
            case R.id.action_favoris:
                //startActivity(new Intent(this, Favoris.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_recettes);

        recyclerView = findViewById(R.id.recipe_list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        WsManager wsManager = new WsManager();
        wsManager.executePostRequest("ws/resto/listRecettes", this);

    }

    @Override
    public void onFailure(String errorMessage) {
        Log.e("tp9", errorMessage);
    }

    @Override
    public void onSuccess(String data) {
        Log.d("tp9", "test success");

        Recipe[] founderArray = gson.fromJson(data, Recipe[].class);
        recipeList = Arrays.asList(founderArray);
        Log.d("tp9", recipeList.toString());

        RecipeAdapter adapter = new RecipeAdapter(recipeList, this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onClickListener(int position) {
        Recipe recipe = recipeList.get(position);
        Log.d("tp3", "recipe selected : " + recipe);

        Intent intent = new Intent(this, RecetteActivity.class);
        intent.putExtra(SELECTED_RECIPE_ID_KEY, recipe.getId());
        startActivity(intent);
    }
}
