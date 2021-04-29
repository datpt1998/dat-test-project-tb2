package com.example.utils.html.helper;

import com.example.utils.html.constant.HtmlTagName;
import com.example.utils.html.entity.HtmlNode;

public class MyHtmlHelper {
    private HtmlNode htmlNode;

    public MyHtmlHelper() {
        htmlNode = new HtmlNode(HtmlTagName.HTML);
    }

    public String getContent() {
        return htmlNode.toString();
    }

    public void addChildToRoot(Object child) {
        htmlNode.addChildren(child);
    }
}
