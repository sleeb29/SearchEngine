package com.example.invertedindex.repository;

import com.example.invertedindex.model.WordEntry;
import com.example.invertedindex.model.WordEntryID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordEntryRepository extends JpaRepository<WordEntry, WordEntryID> {
}
