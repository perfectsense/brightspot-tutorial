package com.company.company;

import java.util.Date;

import com.psddev.cms.db.Content;
import com.psddev.cms.db.Directory;
import com.psddev.cms.db.Site;
import com.psddev.cms.db.ToolUi;
import com.psddev.dari.db.Singleton;
import com.psddev.dari.util.StorageItem;

@ToolUi.Main
public class Company extends Content implements Singleton,
        Directory.Item {

    private String name;

    private Date foundedOn;

    private StorageItem logo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getFoundedOn() {
        return foundedOn;
    }

    public void setFoundedOn(Date foundedOn) {
        this.foundedOn = foundedOn;
    }

    public StorageItem getLogo() {
        return logo;
    }

    public void setLogo(StorageItem logo) {
        this.logo = logo;
    }

    @Override
    public String createPermalink(Site site) {
        return "/";
    }
}
