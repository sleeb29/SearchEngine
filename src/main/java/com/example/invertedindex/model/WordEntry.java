package com.example.invertedindex.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.UUID;

@Entity
public class WordEntry {

    @EmbeddedId
    WordEntryID wordEntryID;

    public WordEntry(){

    }

    public WordEntry(WordEntryID wordEntryID)
    {
        this.wordEntryID = wordEntryID;
    }

    public WordEntryID getWordEntryID() {
        return wordEntryID;
    }

    public void setWordEntryID(WordEntryID wordEntryID) {
        this.wordEntryID = wordEntryID;
    }
}
