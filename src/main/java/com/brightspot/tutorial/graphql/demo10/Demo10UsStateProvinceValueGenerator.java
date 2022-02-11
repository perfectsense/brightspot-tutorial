package com.brightspot.tutorial.graphql.demo10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.psddev.cms.db.ValueGenerator;
import com.psddev.cms.tool.ToolPageContext;

public class Demo10UsStateProvinceValueGenerator implements ValueGenerator {

    // TODO: Filter automatically if the zip code and/or street is known
    @Override
    public List<Value> generate(ToolPageContext page, Object object, String input) {

        if (input != null) {
            input = input.replaceAll("[^a-zA-Z]", "");

            if (input.isEmpty()) {
                input = null;
            }
        }

        final String normalizedInput = input;

        List<Demo10UsStateProvince> topMatches = new ArrayList<>();

        if (normalizedInput != null) {
            if (normalizedInput.length() == 1) {
                topMatches.addAll(Demo10UsStateProvince.valuesOfAbbreviationFirstLetter(normalizedInput));

            } else if (normalizedInput.length() == 2) {
                Demo10UsStateProvince sp = Demo10UsStateProvince.valueOfAbbreviation(normalizedInput);
                if (sp != null) {
                    topMatches.add(sp);
                }
            }
        }

        ValueGenerator.inputFilterPredicate(input);

        return Arrays.stream(Demo10UsStateProvince.values())
            .map(sp -> Value.withLabel(usStateProvinceToValue(sp), usStateProvinceToLabel(sp)))
            .filter(ValueGenerator.inputFilterPredicate(input))
            .filter(value -> {
                if (!topMatches.isEmpty()) {
                    return topMatches.stream()
                        .anyMatch(st -> st.getAbbreviation().equals(value.getValue()));
                } else {
                    return true;
                }
            })
            .sorted(ValueGenerator.labelSortComparator())
            .collect(Collectors.toList());
    }

    public static String usStateProvinceToValue(Demo10UsStateProvince sp) {
        return sp.getAbbreviation();
    }

    public static String usStateProvinceToLabel(Demo10UsStateProvince sp) {
        return "(" + sp.getAbbreviation() + ") " + sp.getName();
    }
}
