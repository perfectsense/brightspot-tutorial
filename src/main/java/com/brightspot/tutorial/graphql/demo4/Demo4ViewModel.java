package com.brightspot.tutorial.graphql.demo4;

import java.util.Collections;
import java.util.List;

import com.psddev.cms.view.ViewInterface;
import com.psddev.cms.view.ViewModel;

/**
 * This is class / type level documentation.
 */
@ViewInterface
public class Demo4ViewModel extends ViewModel<MyFourthGraphQLEndpoint> {

    /**
     * This describes what Alpha is and what you can do with it.
     */
    public String getAlpha() {
        return "bet";
    }

    /**
     * Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent eget.
     */
    public Integer getBravo() {
        return 42;
    }

    /**
     * Sometimes returns true, sometimes returns false, and in rare cases it returns nothing at all!
     */
    public Boolean getCharlie() {
        return Math.random() > 0.5;
    }

    /**
     * This is a collection field. While technically null is allowed, we promise it will never be null. At worst,
     * it'll be empty.
     */
    public List<String> getDelta() {
        return Collections.emptyList();
    }

    /**
     * This field returns a special type. Check out the docs on this one!
     */
    public Demo4RelationViewModel getFoxtrot() {
        return createView(Demo4RelationViewModel.class, model);
    }
}
