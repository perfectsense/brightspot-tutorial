package com.brightspot.tutorial;

import java.util.Map;
import com.psddev.cms.image.ImageSize;
import com.psddev.cms.view.ViewInterface;
import com.psddev.cms.view.ViewModel;
import com.psddev.styleguide.ImageAttributes;

@ViewInterface
public class ImageViewModel extends ViewModel<Image>{

    @ImageAttributes
    public Map<String, ?> getImageFile() {
        return ImageSize.getAttributes(model.getImageFile());
    }

    public String getTitle() {
        return model.getTitle();
    }

    public String getImageId() {
        return model.getImageId().toString();
    }
}