package com.brightspot.tutorial.graphql.demo13.mark;

import java.util.Map;
import java.util.Set;

import com.psddev.cms.view.ViewInterface;
import com.psddev.cms.view.ViewModel;
import com.psddev.dari.web.annotation.WebParameter;

@ViewInterface
public class MapDataEntryViewModel extends ViewModel<Map.Entry<String, String>> {

    @WebParameter
    private Set<String> keys;

    @Override
    protected boolean shouldCreate() {
        return keys.isEmpty() || keys.contains(model.getKey());
    }

    public String getKey() {
        return model.getKey();
    }

    public String getValue() {
        return model.getValue();
    }
}
