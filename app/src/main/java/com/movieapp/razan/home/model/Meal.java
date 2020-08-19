package com.movieapp.razan.home.model;

public class Meal {
    String MealName;
      String MealPrice;

    public Meal() {
    }

    public String getMealName() {
        return MealName;
    }

    public String getMealPrice() {
        return MealPrice;
    }

    public void setMealName(String mealName) {
        MealName = mealName;
    }

    public void setMealPrice(String mealPrice) {
        MealPrice = mealPrice;
    }
}
