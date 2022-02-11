package com.brightspot.tutorial.graphql.demo3;

import com.psddev.cms.db.Content;
import com.psddev.dari.db.Recordable;
import com.psddev.dari.db.Singleton;
import com.psddev.dari.util.StorageItem;

@Recordable.DisplayName("Demo 3 Queries")
public class Demo3Queries extends Content implements Singleton {

    private StorageItem file;

    public StorageItem getFile() {
        return file;
    }

    public void setFile(StorageItem file) {
        this.file = file;
    }

    @Override
    public String getLabel() {
        return getState().getType().getLabel();
    }
}
