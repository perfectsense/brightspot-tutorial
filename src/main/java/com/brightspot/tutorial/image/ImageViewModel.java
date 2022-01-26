package com.brightspot.tutorial.image;

import java.util.Map;
import java.util.Optional;

import com.psddev.cms.image.ImageSize;
import com.psddev.cms.view.PageEntryView;
import com.psddev.cms.view.ViewInterface;
import com.psddev.cms.view.ViewModel;

@ViewInterface
public class ImageViewModel extends ViewModel<Image> implements PageEntryView {

    @com.psddev.styleguide.ImageAttributes
    public Map<String, ?> getImage() {
        return Optional.ofNullable(model.getFile())
            .map(ImageSize::getAttributes)
            .orElse(null);
    }

    public String getTitle() {
        return model.getTitle();
    }
}
