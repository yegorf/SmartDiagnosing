package com.yegorf.controllers.different;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class JokeParser {
    private Document doc = null;

    public String getJoke() throws IOException {
        String url = "https://www.anekdot.ru/random/anekdot/";
        doc = Jsoup.connect(url).get();
        Element element = doc.select(".text").get(1);
        return element.text();
    }
}
