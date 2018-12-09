package com.example.invertedindex.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class WordEntryID implements Serializable {
    @Column(name = "word")
    private String word;

    @Column(name = "document_ID")
    private String documentID;

    public WordEntryID()
    {

    }

    public WordEntryID(String word, String documentID) {
        this.word = word;
        this.documentID = documentID;
    }

    public String getWord() {
        return word;
    }

    public String getDocumentID() {
        return documentID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WordEntryID)) return false;
        WordEntryID that = (WordEntryID) o;
        return Objects.equals(getWord(), that.getWord()) &&
                Objects.equals(getDocumentID(), that.getDocumentID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWord(), getDocumentID());
    }
}
