package it.academy.fitness_studio.core.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Pages<T> {
    @JsonProperty("number")
    int number;
    @JsonProperty("size")
    int size;
    @JsonProperty("total_pages")
    int totalPages;
    @JsonProperty("total_elements")
    long totalElements;
    @JsonProperty("first")
    boolean first;
    @JsonProperty("number_of_elements")
    int numberOfElements;
    @JsonProperty("last")
    boolean last;
    @JsonProperty("content")
    List<T> content;

    public Pages() {
    }

    public Pages(int number,
                 int size,
                 int totalPages,
                 long totalElements,
                 boolean first,
                 int numberOfElements,
                 boolean last,
                 List<T> content) {
        this.number = number;
        this.size = size;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.first = first;
        this.numberOfElements = numberOfElements;
        this.last = last;
        this.content =content;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
