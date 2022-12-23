package com.brightspot.tutorial;

import com.psddev.cms.view.ViewInterface;
import com.psddev.cms.view.ViewModel;

/**
 * <a href="https://www.brightspot.com/" target="_blank">Brightspot</a>
 * <strong>Profile</strong>
 * <img src="https://cdn-icons-png.flaticon.com/32/3177/3177440.png" alt="profile_image"/>
 */
@ViewInterface
public class ProfileViewModel extends ViewModel<Profile> {

    /**
     * <strong>A Profile's Display Name</strong>
     */
    public String getDisplayName() {
        return model.getDisplayName();
    }

    /**
     * <p>A Favorite Sport</p>
     * <img src="https://cdn-icons-png.flaticon.com/32/1540/1540454.png" alt="soccer_ball"
     * <img src="https://cdn-icons-png.flaticon.com/32/2813/2813821.png" alt="football">
     * <img src="https://cdn-icons-png.flaticon.com/32/9105/9105802.png" alt="hockey"
     * <img src="https://cdn-icons-png.flaticon.com/32/502/502142.png" alt="tennis">
     */
    public String getFavoriteSport() {
        return model.getFavoriteSport();
    }
    /**
     * <p>A Favorite Book</p>
     * <img src="https://cdn-icons-png.flaticon.com/32/3145/3145765.png" alt="book" />
     */
    public String getFavoriteBook() {
        return model.getFavoriteBook();
    }
    /**
     * <p>A Favorite Song</p>
     * <img src="https://cdn-icons-png.flaticon.com/32/3083/3083417.png" alt="songs"/>
     */
    public String getFavoriteSong() {
        return model.getFavoriteSong();
    }
    /**
     * <p>A Favorite Food</p>
     * <img src="https://cdn-icons-png.flaticon.com/32/685/685352.png" alt="food" />
     */
    public String getFavoriteFood() {
        return model.getFavoriteFood();
    }
}
