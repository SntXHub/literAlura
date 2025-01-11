package com.literalura.literAlura.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GutendexResponse {

    @JsonProperty("count")
    private int count;

    @JsonProperty("next")
    private String next;

    @JsonProperty("previous")
    private String previous;

    @JsonProperty("results")
    private List<GutendexBook> books;

    // Getters y setters
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<GutendexBook> getBooks() {
        return books;
    }

    public void setBooks(List<GutendexBook> books) {
        this.books = books;
    }
}

