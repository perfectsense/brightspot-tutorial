package brightspot.tutorial.example2;

import com.psddev.cms.view.PageEntryView;
import com.psddev.cms.view.ViewInterface;
import com.psddev.cms.view.ViewModel;
import com.psddev.handlebars.HandlebarsTemplate;

@ViewInterface
@HandlebarsTemplate("brightspot/tutorial/example2/Example2")
public class Example2ViewModel extends ViewModel<Example2Model> implements PageEntryView {

    public String getTitle() {
        return model.getTitle() + "!!!";
    }
}
