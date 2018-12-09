package com.example.invertedindex.service;

import com.example.invertedindex.model.*;
import com.example.invertedindex.repository.DocumentRepository;
import com.example.invertedindex.repository.WordEntryRepository;
import com.google.common.collect.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class InvertedIndexService {

    @Autowired
    DocumentRepository documentRepository;
    @Autowired
    WordEntryRepository wordEntryRepository;

    Map<UUID, String> documentEntryCache;
    HashMultimap<String, String> wordEntryCache;

    @PostConstruct
    public void init()
    {
        documentEntryCache = documentRepository.findAll()
                .stream()
                .collect(Collectors.toMap(DocumentEntry::getDocumentID, DocumentEntry::getDocument));

        wordEntryCache = wordEntryRepository.findAll()
                .stream()
                .collect(Collector.of(HashMultimap::create, (multimap, wordEntry) -> {
                    multimap.put(wordEntry.getWordEntryID().getWord().toUpperCase(), wordEntry.getWordEntryID().getDocumentID());
                }, (multi1, multi2) -> {
                    multi1.putAll(multi2);
                    return multi1;
                }));
    }

    public void addDocuments(List<String> documents)
    {
        documents.forEach(this::addDocument);
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

    public SearchResults getResults(SearchTerms searchTerms) {
        List<String> documents = searchTerms.getSearchTerms()
                .stream()
                .map(String::toUpperCase)
                .filter(wordEntryCache::containsKey)
                .map(wordEntryCache::get)
                .flatMap(Set::stream)
                .map(UUID::fromString)
                .filter(documentEntryCache::containsKey)
                .map(documentEntryCache::get)
                .collect(Collectors.toList());
        documents.forEach(System.out::println);
        return new SearchResults(documents);
    }
}
