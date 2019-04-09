package brightspot.tutorial.example2;

import com.psddev.cms.db.Content;
import com.psddev.dari.db.Recordable;

@Recordable.DisplayName("Example 2")
public class Example2Model extends Content {

    private String title;

    public String getTitle() {
        return title;
    }
}
