package com.example.invertedindex.repository;

import com.example.invertedindex.model.DocumentEntry;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface DocumentRepository extends CassandraRepository<DocumentEntry, String> {
}
