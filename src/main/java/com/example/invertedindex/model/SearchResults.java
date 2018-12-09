package com.example.invertedindex.model;

import java.util.List;

public class SearchResults {
    List<String> searchResults;

    public SearchResults() {

    }

    public SearchResults(List<String> searchResults) {
        this.searchResults = searchResults;
    }

    public List<String> getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(List<String> searchResults) {
        this.searchResults = searchResults;
    }
}
