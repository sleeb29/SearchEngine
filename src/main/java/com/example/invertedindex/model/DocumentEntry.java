package com.example.invertedindex.model;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Table("document_entry")
public class DocumentEntry {

    @PrimaryKey("document_id")
    UUID documentID;
    @Column("document")
    String document;

    public DocumentEntry()
    {

    }

    public DocumentEntry(UUID documentID, String document) {
        this.documentID = documentID;
        this.document = document;
    }

    public UUID getDocumentID() {
        return documentID;
    }

    public void setDocumentID(UUID documentID) {
        this.documentID = documentID;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }
}
