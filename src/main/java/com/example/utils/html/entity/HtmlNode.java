package com.example.utils.html.entity;

import com.example.utils.html.constant.General;
import com.example.utils.html.constant.HtmlTagName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HtmlNode {
    private String tagName;
    private Map<String, String> attribute;
    private List<Object> children;

    public HtmlNode(String tagName) {
        this.tagName = tagName;
        attribute = new HashMap<>();
        children = new ArrayList<>();
    }

    public String getTagName() {
        return tagName;
    }

    public HtmlNode setTagName(String tagName) {
        this.tagName = tagName;
        return this;
    }

    public Map<String, String> getAttribute() {
        return attribute;
    }

    public List<Object> getChildren() {
        return children;
    }

    public HtmlNode addAttribute(String key, String value) {
        attribute.put(key, value);
        return this;
    }

    public HtmlNode addChildren(Object child) {
        if(validateChild(child)) {
            children.add(child);
            return this;
        } else {
            throw new IllegalArgumentException("Invalid child");
        }
    }

    private boolean validateChild(Object child) {
        if(tagName.equalsIgnoreCase(HtmlTagName.STYLE)) {
            return (child instanceof CssNode);
        } else {
            return (child instanceof String) || (child instanceof HtmlNode);
        }
    }

    @Override
    public String toString() {
        StringBuilder childrenString = new StringBuilder();
        List<String> attributeStrings = new ArrayList<>();
        for(Object child : children) {
            childrenString.append(child.toString());
            childrenString.append(" ");
        }
        attribute.forEach((key, value)->{
            attributeStrings.add(String.format(General.ATTRIBUTE_FORMAT, key, value));
        });
        return String.format(General.HTML_FORMAT,
                tagName,
                String.join(General.ATTRIBUTE_DELIM, attributeStrings.toArray(new String[attributeStrings.size()])),
                childrenString.toString(),
                tagName);
    }
}
