package com.backend.doctic_mongo.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.backend.doctic_mongo.Model.DescargaModel;

public interface IDescargaRepository extends MongoRepository<DescargaModel, ObjectId> {
}
