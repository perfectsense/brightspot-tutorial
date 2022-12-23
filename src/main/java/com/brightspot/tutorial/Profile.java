package com.brightspot.tutorial;

import com.psddev.cms.db.Content;

public class Profile extends Content {

    @Required
    private String displayName;

    private String favoriteSport;

    private String favoriteBook;

    private String favoriteSong;

    private String favoriteFood;

    public String getDisplayName() {return displayName;}

    public void setDisplayName(String displayName) { this.displayName = displayName; }

    public String getFavoriteSport() {
        return favoriteSport;
    }

    public void setFavoriteSport (String favoriteSport) {
        this.favoriteSport = favoriteSport;
    }

    public String getFavoriteBook() {
        return favoriteBook;
    }

    public void setFavoriteBook(String favoriteBook) {
        this.favoriteBook = favoriteBook;
    }

    public String getFavoriteSong() {
        return favoriteSong;
    }

    public void setFavoriteSong(String favoriteMusic) {
        this.favoriteSong = favoriteSong;
    }

    public String getFavoriteFood() {return favoriteFood; }

    public void setFavoriteFood (String favoriteFood) {this.favoriteFood = favoriteFood; }
}
