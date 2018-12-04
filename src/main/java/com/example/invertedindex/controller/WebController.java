package com.example.invertedindex.controller;

import com.example.invertedindex.model.Documents;
import com.example.invertedindex.service.InvertedIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class WebController {
    @Autowired
    InvertedIndexService invertedIndexService;

    @RequestMapping(value="/addDocuments", method = RequestMethod.POST)
    public ResponseEntity<Void> addDocuments(@RequestBody Documents documents){
        invertedIndexService.addDocuments(documents.getDocuments());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
