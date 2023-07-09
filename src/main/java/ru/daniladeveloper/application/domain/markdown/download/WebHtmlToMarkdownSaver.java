package ru.daniladeveloper.application.domain.markdown.download;


import lombok.Getter;
import lombok.extern.java.Log;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;


import static ru.daniladeveloper.application.infrustructure.util.StringUtils.containsAnyOf;
import static ru.daniladeveloper.application.infrustructure.util.UrlUtils.asURL;

@Log
public class WebHtmlToMarkdownSaver {

    private WebCrawler crawler;

    @Getter
    private Path savePath;

    private boolean isDefaultPath;

    private List<String> content;

    public WebHtmlToMarkdownSaver() {
        this("/tmp/mdsaver/");
        this.isDefaultPath = true;
    }

    public WebHtmlToMarkdownSaver(String pathToSave) {
        this.savePath = Path.of(pathToSave);
        this.isDefaultPath = false;
        this.content = new ArrayList<>(100);
    }

    public void saveAsMarkdown(String url) {
        try {
            saveAsMarkdown(asURL(url));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveAsMarkdown(URL url) {
        createDirectoryIfNeeded(url);
        crawler = new WebCrawler(url);

        String html = crawler.getPageAsString();
        Document document = Jsoup.parse(html);

        for (Element element: document.getAllElements()) {
            switch (element.tagName()) {
                case "img" : saveImageAndTurnToMarkdownFormat(element);
            }
        }
    }

    private void createDirectoryIfNeeded(URL url) {
        if (!isDefaultPath) {
            savePath = savePath.resolve(getDirectoryName(url));
        }
        try {
            Files.createDirectories(savePath);
        } catch (FileAlreadyExistsException ignored) { }
        catch (SecurityException | IOException e) {
            log.severe("Unable to create directory");
            throw new RuntimeException(e);
        }
    }

    private String getDirectoryName(URL url) {
        if (url.getFile().endsWith("/")) {
            return url.getHost();
        }
        return url.getFile();
    }

    private void saveImageAndTurnToMarkdownFormat(Element element) {
        if (!isNotAppropriateImage(element)) {
            String imageSrc = element.attr("src");
            String imageName;
            try {
                imageName = crawler.downloadImage(imageSrc, savePath);
                String imageAsMarkdownReference = imageToMarkdown(element.attr("alt"), imageName);
                content.add(imageAsMarkdownReference);
            } catch (ImageDownloadException e) {
                log.warning("Image: " + imageSrc +  " was not saved because of exception. Message: " + e.getMessage());
            }
        }
    }



    private boolean isNotAppropriateImage(Element element) {
        Set<String> attributes = new HashSet<>(element.classNames());
        attributes.add(element.attr("src"));
        return attributes.stream().anyMatch(attribute -> containsAnyOf(attribute, ParseExpressions.FORBIDDEN_WORDS));
    }

    private String imageToMarkdown(String imageAlt, String imageSrc) {
        return "!" + "[" + imageAlt + "](" + imageSrc + ")";
    }


    static class ParseExpressions {

        public static final Pattern IMAGE_IGNORE = Pattern.compile("(.*logo.*|.*icon.*|.*Logo.*|.*Icon.*)");

        public static final Set<String> FORBIDDEN_WORDS = Set.of("logo", "Logo", "Icon", "icon", "footer");
    }

    private void save() {

        content.forEach(log::info);
        //String fileName = url.getPath() + MarkdownConstants.MD_EXTENSION;
    }
}
