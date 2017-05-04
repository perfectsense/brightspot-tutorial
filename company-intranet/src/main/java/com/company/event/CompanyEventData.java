package com.company.event;

import java.util.Date;

import com.psddev.cms.db.ToolUi;
import com.psddev.dari.db.Modification;

public class CompanyEventData extends Modification<CompanyEvent> {

    @ToolUi.Hidden
    @Indexed
    public Date getEventDate() {
        return getOriginalObject().getCompanyEventDate();
    }
}
