package com.company.event;

import java.util.Date;

import com.psddev.dari.db.Recordable;

public interface CompanyEvent extends Recordable {

    Date getCompanyEventDate();
}
