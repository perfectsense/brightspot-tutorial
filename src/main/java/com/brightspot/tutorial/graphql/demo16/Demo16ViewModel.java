package com.brightspot.tutorial.graphql.demo16;

import java.util.Map;
import java.util.Optional;

import com.psddev.cms.image.ImageSize;
import com.psddev.cms.view.PageEntryView;
import com.psddev.cms.view.ViewInterface;
import com.psddev.cms.view.ViewModel;
import com.psddev.styleguide.ImageAttributes;

@ViewInterface
public class Demo16ViewModel extends ViewModel<Demo16> implements PageEntryView {

    public String getTitle() {
        return model.getTitle();
    }

    public String getDescription() {
        return model.getDescription();
    }

    @ImageAttributes
    public Map<String, String> getImage() {
        return Optional.ofNullable(model.getImage())
            .map(Demo16Image::getFile)
            .map(ImageSize::getAttributes)
            .orElse(null);
    }
}
