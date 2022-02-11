package com.brightspot.tutorial.graphql.demo1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.psddev.cms.db.Content;
import com.psddev.dari.db.Location;
import com.psddev.dari.db.Recordable.DisplayName;
import com.psddev.dari.db.Region;
import com.psddev.dari.util.StorageItem;

@DisplayName("Demo 1")
public class Demo1 extends Content {

    private String aaString;

    private Boolean bbBoolean;

    private Date ccDate;

    private Integer ddInteger;

    private Float eeFloat;

    private List<String> ffListString;

    private Demo1Relation ggRef;

    @Embedded
    private Demo1Relation hhEmbedded;

    private List<Demo1Relation> iiListRef;

    @Embedded
    private List<Demo1Relation> jjListEmbedded;

    private StorageItem kkFile;

    private Location llLocation;

    private Region mmRegion;

    // getters / setters

    public String getAaString() {
        return aaString;
    }

    public Boolean getBbBoolean() {
        return bbBoolean;
    }

    public Date getCcDate() {
        return ccDate;
    }

    public Integer getDdInteger() {
        return ddInteger;
    }

    public Float getEeFloat() {
        return eeFloat;
    }

    public List<String> getFfListString() {
        return ffListString;
    }

    public Demo1Relation getGgRef() {
        return ggRef;
    }

    public Demo1Relation getHhEmbedded() {
        return hhEmbedded;
    }

    public List<Demo1Relation> getIiListRef() {
        if (iiListRef == null) {
            iiListRef = new ArrayList<>();
        }
        return iiListRef;
    }

    public List<Demo1Relation> getJjListEmbedded() {
        if (jjListEmbedded == null) {
            jjListEmbedded = new ArrayList<>();
        }
        return jjListEmbedded;
    }

    public StorageItem getKkFile() {
        return kkFile;
    }

    public Location getLlLocation() {
        return llLocation;
    }

    public Region getMmRegion() {
        return mmRegion;
    }
}
