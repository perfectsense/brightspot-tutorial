package example.module;

import com.psddev.cms.db.Content;

import java.util.ArrayList;
import java.util.List;

public class Module extends Content implements ModuleItem {

    @Required
    private String name;

    @Required
    private List<ModuleItem> items;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ModuleItem> getItems() {
        if (items == null) {
            items = new ArrayList<>();
        }
        return items;
    }

    public void setItems(List<ModuleItem> items) {
        this.items = items;
    }
}
