package com.example.utils.html.entity;

import com.example.utils.html.constant.General;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CssNode {
    private String selector;
    private Map<String, String> attribute;

    public CssNode(String selector) {
        this.selector = selector;
        this.attribute = new HashMap<>();
    }

    public String getSelector() {
        return selector;
    }

    public CssNode setSelector(String selector) {
        this.selector = selector;
        return this;
    }

    public Map<String, String> getAttribute() {
        return attribute;
    }

    public CssNode addAttribute(String key, String value) {
        attribute.put(key, value);
        return this;
    }

    @Override
    public String toString() {
        List<String> attributeStrings = new ArrayList<>();
        attribute.forEach((key,value) -> {
            attributeStrings.add(String.format(General.CSS_ATTRIBUTE_FORMAT, key, value));
        });
        return String.format(General.CSS_FORMAT,
                selector,
                String.join(General.CSS_ATTRIBUTE_DELIM, attributeStrings.toArray(new String[attributeStrings.size()])));
    }
}
