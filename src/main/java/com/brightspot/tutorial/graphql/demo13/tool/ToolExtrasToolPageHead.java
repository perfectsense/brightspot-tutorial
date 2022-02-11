package com.brightspot.tutorial.graphql.demo13.tool;

import java.io.IOException;

import com.psddev.cms.tool.ToolPageContext;
import com.psddev.cms.tool.ToolPageHead;
import com.psddev.dari.html.enumerated.LinkRel;
import com.psddev.dari.util.Cdn;

import static com.psddev.dari.html.Nodes.*;

public class ToolExtrasToolPageHead implements ToolPageHead {

    @Override
    public void writeHtml(ToolPageContext page) throws IOException {
        page.write(LINK.rel(LinkRel.STYLESHEET)
            .type("text/css")
            .href(Cdn.getUrl(page.getRequest(), "/_resource/ToolExtras.css")));
    }
}
