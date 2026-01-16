package com.example.FileService.repository;

import com.example.FileService.entity.FileMgmt;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileMgmtRepository extends MongoRepository<FileMgmt, String> {

}
