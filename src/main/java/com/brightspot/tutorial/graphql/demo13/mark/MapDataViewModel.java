package com.brightspot.tutorial.graphql.demo13.mark;

import java.util.Map;

import com.psddev.cms.view.ViewInterface;
import com.psddev.cms.view.ViewModel;
import com.psddev.dari.util.ObjectUtils;

@ViewInterface
public class MapDataViewModel extends ViewModel<Map<String, String>> {

    public String getJson() {
        return ObjectUtils.toJson(model);
    }

    public Iterable<MapDataEntryViewModel> getEntries() {
        return createViews(MapDataEntryViewModel.class, model.entrySet());
    }
}
