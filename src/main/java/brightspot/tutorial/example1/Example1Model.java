package brightspot.tutorial.example1;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.psddev.cms.db.Content;
import com.psddev.cms.db.Renderer;
import com.psddev.dari.db.Recordable;
import com.psddev.dari.util.HtmlWriter;
import com.psddev.dari.util.StringUtils;

@Recordable.DisplayName("Example 1")
public class Example1Model extends Content implements Renderer {

    private String title;

    public String getTitle() {
        return title;
    }

    @Override
    public void renderObject(HttpServletRequest request, HttpServletResponse response, HtmlWriter writer) throws IOException, ServletException {
        writer.write("" +
                "<!doctype html>" +
                "<html>" +
                "<head>" +
                "<title>" + StringUtils.escapeHtml(getTitle()) + "!!!" + "</title>" +
                "</head>" +
                "<body>" +
                "<h1>" + StringUtils.escapeHtml(getTitle()) + "!!!" + "</h1>" +
                "</body>" +
                "</html>");
    }
}
