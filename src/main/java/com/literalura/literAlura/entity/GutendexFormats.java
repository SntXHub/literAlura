package com.literalura.literAlura.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GutendexFormats {

    @JsonProperty("text/plain; charset=utf-8")
    private String textPlain;

    @JsonProperty("text/html")
    private String textHtml;

    @JsonProperty("application/epub+zip")
    private String epub;

    // Getters y Setters
    public String getTextPlain() {
        return textPlain;
    }

    public void setTextPlain(String textPlain) {
        this.textPlain = textPlain;
    }

    public String getTextHtml() {
        return textHtml;
    }

    public void setTextHtml(String textHtml) {
        this.textHtml = textHtml;
    }

    public String getEpub() {
        return epub;
    }

    public void setEpub(String epub) {
        this.epub = epub;
    }
}