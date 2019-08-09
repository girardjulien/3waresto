package com.example.a3wresto.model;

public class Recipe {
    private long id;
    private String title;
    private String photo;
    private long note;
    private String tempsPreparation;
    private String tempsCookRime;
    private String calories;
    private String ingredients;
    private String instructions;

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getTempsPreparation() {
        return tempsPreparation;
    }

    public void setTempsPreparation(String tempsPreparation) {
        this.tempsPreparation = tempsPreparation;
    }

    public String getTempsCookRime() {
        return tempsCookRime;
    }

    public void setTempsCookRime(String tempsCookRime) {
        this.tempsCookRime = tempsCookRime;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public long getNote() {
        return note;
    }

    public void setNote(long note) {
        this.note = note;
    }
}
