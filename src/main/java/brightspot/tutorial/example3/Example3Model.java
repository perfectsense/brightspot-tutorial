package brightspot.tutorial.example3;

import com.psddev.cms.db.Content;
import com.psddev.dari.db.Recordable;

@Recordable.DisplayName("Example 3")
public class Example3Model extends Content {

    private String title;

    public String getTitle() {
        return title;
    }
}
