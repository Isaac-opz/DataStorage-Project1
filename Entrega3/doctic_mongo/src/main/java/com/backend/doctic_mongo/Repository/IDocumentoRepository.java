package com.backend.doctic_mongo.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.backend.doctic_mongo.Model.DocumentoModel;

public interface IDocumentoRepository extends MongoRepository<DocumentoModel,ObjectId> {
   
    
}

