package com.brightspot.tutorial.graphql.demo13.mark;

import com.psddev.cms.view.ViewInterface;

@ViewInterface
public interface MarkView {

    Integer getStart();

    Integer getEnd();

    Boolean getSelfClosing();

    Boolean getIsBlock();

    MarkedTextViewModel getBody();
}
