package com.example.invertedindex.service;

import com.example.invertedindex.model.DocumentEntry;
import com.example.invertedindex.model.WordEntry;
import com.example.invertedindex.model.WordEntryID;
import com.example.invertedindex.repository.DocumentRepository;
import com.example.invertedindex.repository.WordEntryRepository;
import com.google.common.collect.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;

@Service
public class InvertedIndexService {

    @Autowired
    DocumentRepository documentRepository;
    @Autowired
    WordEntryRepository wordEntryRepository;

    HashMultimap<UUID, String> documentEntryCache;
    HashMultimap<String, String> wordEntryCache;

    @PostConstruct
    public void init()
    {
        documentEntryCache = documentRepository.findAll()
                .stream()
                .collect(Collector.of(HashMultimap::create, (multimap, documentEntry) -> {
                    multimap.put(documentEntry.getDocumentID(), documentEntry.getDocument());
                }, (multi1, multi2) -> {
                    multi1.putAll(multi2);
                    return multi1;
                }));

        wordEntryCache = wordEntryRepository.findAll()
                .stream()
                .collect(Collector.of(HashMultimap::create, (multimap, wordEntry) -> {
                    multimap.put(wordEntry.getWordEntryID().getWord(), wordEntry.getWordEntryID().getDocumentID());
                }, (multi1, multi2) -> {
                    multi1.putAll(multi2);
                    return multi1;
                }));
    }

    public void addDocuments(List<String> documents)
    {
        documents.forEach(document ->
        {
            addDocument(document);
        });
    }

    public boolean addDocument(String document) {
        try {
            String[] words = document.split("[.,\\s]+");
            UUID documentID = UUID.randomUUID();

            documentRepository.save(new DocumentEntry(documentID, document));
            documentEntryCache.put(documentID, document);

            for (String word : words) {
                wordEntryRepository.save(new WordEntry(new WordEntryID(word, documentID.toString())));
                wordEntryCache.put(word, documentID.toString());
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
