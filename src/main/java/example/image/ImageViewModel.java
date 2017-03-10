package example.image;

import com.psddev.cms.image.ImageSize;
import com.psddev.cms.view.ViewModel;
import styleguide.image.ImageView;

public class ImageViewModel extends ViewModel<Image> implements ImageView {

    @Override
    public CharSequence getSrc() {
        return ImageSize.getUrl(model.getFile());
    }

    @Override
    public CharSequence getAlt() {
        return model.getAltText();
    }
}
