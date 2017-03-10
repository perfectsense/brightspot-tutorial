package example.module;

import com.psddev.cms.view.PageEntryView;
import com.psddev.cms.view.ViewModel;
import styleguide.module.ModuleView;
import styleguide.module.ModuleViewItemsField;

import java.util.Collection;
import java.util.stream.Collectors;

public class ModuleViewModel extends ViewModel<Module> implements ModuleView, PageEntryView {

    @Override
    public Collection<? extends ModuleViewItemsField> getItems() {
        return model.getItems().stream()
                .map(i -> createView(ModuleViewItemsField.class, i))
                .collect(Collectors.toList());
    }
}
