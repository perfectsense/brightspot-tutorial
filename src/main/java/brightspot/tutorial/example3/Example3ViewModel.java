package brightspot.tutorial.example3;

import com.psddev.cms.view.JsonView;
import com.psddev.cms.view.PageEntryView;
import com.psddev.cms.view.ViewInterface;
import com.psddev.cms.view.ViewModel;

@ViewInterface
@JsonView
public class Example3ViewModel extends ViewModel<Example3Model> implements PageEntryView {

    public String getTitle() {
        return model.getTitle() + "!!!";
    }
}
