package com.example.invertedindex.repository;

import com.example.invertedindex.model.DocumentEntry;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface DocumentRepository extends CassandraRepository<DocumentEntry, String> {
}
