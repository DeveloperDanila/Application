package ru.daniladeveloper.application.domain.markdown.download;

import lombok.extern.java.Log;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static ru.daniladeveloper.application.infrustructure.util.UrlUtils.asURL;
import static ru.daniladeveloper.application.infrustructure.util.UrlUtils.getFullSource;

@Log
public class WebCrawler {

    private URL url;

    public WebCrawler(URL url) {
        this.url = url;
    }

    public WebCrawler(String url) throws MalformedURLException {
        this.url = asURL(url);
    }

    public String getPageAsString() {
        try {
            Document doc = Jsoup.connect(url.toString()).get();
            return extractTextAndContentLinks(doc);
        } catch (IOException e) {
            log.severe("Unable to connect or read from: " + url + "Error: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private String extractTextAndContentLinks(Document doc) {
        StringBuilder result = new StringBuilder();
        for (Element element : doc.getAllElements()) {
            String tagName = element.tagName();
            if (HtmlTagType.isAppropriateTag(tagName)) {
                HtmlElement tag = new HtmlElement(element, tagName);
                if (!tag.toString().isBlank()) {
                    result.append(tag).append("\n");
                }
            }
        }
        log.info(result.toString());
        return result.toString();
    }

    public String downloadImage(String imageSrc, Path pathToSave) throws ImageDownloadException {
        try {
            URL src = getFullSource(imageSrc, url);
            return downloadImage(src, pathToSave);
        } catch (Exception e) {
            throw new ImageDownloadException(e);
        }
    }

    private String downloadImage(URL src, Path pathToSave) throws IOException {
        try (InputStream in = src.openStream()) {
            String imageFile = Paths.get(src.getFile()).getFileName().toString();
            Files.copy(in, pathToSave.resolve(imageFile));
            return imageFile;
        }
    }

    public record HtmlElement(Element element, HtmlTagType type) {

        public HtmlElement(Element element, String tagName) {
            this(element, HtmlTagType.valueOf(tagName.toUpperCase()));
        }

        @Override
        public String toString() {
            return switch (type) {
                case IMG, H1, H2, H3, P -> element.toString();
                default -> "";
            };

        }
    }

    enum HtmlTagType {
        A,
        IMG,
        P,
        SPAN,
        LI,
        PRE,
        CODE,
        H1,
        H2,
        H3,
        H4,
        H5,
        H6;

        private static final Set<String> names = Arrays.stream(HtmlTagType.values()).map(tag -> tag.name().toLowerCase()).collect(Collectors.toSet());

        public static boolean isAppropriateTag(String tag) {
           return names.contains(tag);
        }
    }

}
