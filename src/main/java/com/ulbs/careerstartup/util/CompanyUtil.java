package com.ulbs.careerstartup.util;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class CompanyUtil {
    public String getFaviconUrl(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.head().select("link[href~=(?i)\\.(ico|png|gif|jpeg|svg)]");
            if (elements.isEmpty()) {
                elements = doc.head().select("link[rel=icon]");
            }
            if (!elements.isEmpty()) {
                return elements.attr("abs:href");
            }
        } catch (Exception e) {
            log.info("Failed to retrieve favicon: " + e.getMessage());
        }
        return null;
    }
}
