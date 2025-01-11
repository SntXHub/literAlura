package com.literalura.literAlura.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class GutendexBook {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("authors")
    private List<GutendexAuthor> authors;

    @JsonProperty("languages")
    private List<String> languages;

    @JsonProperty("subjects")
    private List<String> subjects;

    @JsonProperty("formats")
    private GutendexFormats formats;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<GutendexAuthor> getAuthors() {
        return authors;
    }

    public void setAuthors(List<GutendexAuthor> authors) {
        this.authors = authors;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public GutendexFormats getFormats() {
        return formats;
    }

    public void setFormats(GutendexFormats formats) {
        this.formats = formats;
    }

    public Map<Object, Object> getBookshelves() {
        Map<Object, Object> Bookshelves = Map.of();
        return Bookshelves;
    }
}
