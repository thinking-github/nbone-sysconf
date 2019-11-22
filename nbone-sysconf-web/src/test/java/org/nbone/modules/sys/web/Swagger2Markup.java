package org.nbone.modules.sys.web;

import io.github.swagger2markup.Swagger2MarkupConfig;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author thinking
 * @version 1.0
 * @since 2019-08-28
 */
public class Swagger2Markup {

    public static void main(String[] args) throws MalformedURLException, UnsupportedEncodingException {

        Swagger2MarkupConfig config = new Swagger2MarkupConfigBuilder()
                .withMarkupLanguage(MarkupLanguage.MARKDOWN)
                //.withOutputLanguage(Language.ZH)
                //.withPathsGroupedBy(GroupBy.TAGS)
                .withGeneratedExamples()
                .build();

        URL remoteSwaggerFile = new URL("http://127.0.0.1:8080/v2/api-docs?group=nbone-sysconf&path=/sys/dict/");


        Path outputDirectory = Paths.get("build/markdown/material-api.md");
        Path outputFile = Paths.get("/Users/chenyicheng/Documents/PROJECT/API-DOC/dict-api");

        Swagger2MarkupConverter.from(remoteSwaggerFile)
                .withConfig(config)
                .build()
                .toFile(outputFile);
        //.toFolder(outputDirectory);


    }

}
