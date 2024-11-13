package com.backend.doctic_mongo.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.backend.doctic_mongo.Model.UsuarioModel;

public interface IUsuarioRepository extends MongoRepository<UsuarioModel, ObjectId> {
}
