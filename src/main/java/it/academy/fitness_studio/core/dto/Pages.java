package it.academy.fitness_studio.core.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import it.academy.fitness_studio.core.dto.product.IngredientModel;
import it.academy.fitness_studio.core.dto.product.RecipeModel;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class Pages<T> {
    @JsonProperty("number")
    private int number;
    @JsonProperty("size")
    private int size;
    @JsonProperty("total_pages")
    private int totalPages;
    @JsonProperty("total_elements")
    private long totalElements;
    @JsonProperty("first")
    private boolean first;
    @JsonProperty("number_of_elements")
    private int numberOfElements;
    @JsonProperty("last")
    private boolean last;
    @JsonProperty("content")
    private List<T> content;

    public Pages () {
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
        this.content = content;
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

    public static class PagesBuilder<T> {
        private int number;
        private int size;
        private int totalPages;
        private long totalElements;
        private boolean first;
        private int numberOfElements;
        private boolean last;
        private List<T> content;

        private PagesBuilder() {
        }

        public static <T>PagesBuilder<T> create() {
            return new PagesBuilder<T>();
        }

        public PagesBuilder setNumber(int number) {
            this.number = number;
            return this;
        }

        public PagesBuilder setSize(int size) {
            this.size = size;
            return this;
        }

        public PagesBuilder setTotalPages(int totalPages) {
            this.totalPages = totalPages;
            return this;
        }

        public PagesBuilder setTotalElements(long totalElements) {
            this.totalElements = totalElements;
            return this;
        }

        public PagesBuilder setFirst(boolean first) {
            this.first = first;
            return this;
        }

        public PagesBuilder setNumberOfElements(int numberOfElements) {
            this.numberOfElements = numberOfElements;
            return this;
        }

        public PagesBuilder setLast(boolean last) {
            this.last = last;
            return this;
        }

        public PagesBuilder setContent(List<T> content) {
            this.content = content;
            return this;
        }

        public Pages build() {
            return new Pages(
                    number,
                    size,
                    totalPages,
                    totalElements,
                    first,
                    numberOfElements,
                    last,
                    content);
        }
    }
}
