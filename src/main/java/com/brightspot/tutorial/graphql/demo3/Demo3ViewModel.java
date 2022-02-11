package com.brightspot.tutorial.graphql.demo3;

import java.util.Random;

import com.psddev.cms.view.ViewInterface;
import com.psddev.cms.view.ViewModel;

@ViewInterface
public class Demo3ViewModel extends ViewModel<Demo3> {

    public String getMessage() {
        return model.getMessage();
    }

    public String getByline() {
        return model.getByline();
    }

    public Integer getRandomNumber() {
        return new Random().nextInt(1000);
    }

    public Boolean getAutomaticPersistedQueries() {
        return true;
    }

    public Boolean getStaticPersistedQueries() {
        return true;
    }
}
