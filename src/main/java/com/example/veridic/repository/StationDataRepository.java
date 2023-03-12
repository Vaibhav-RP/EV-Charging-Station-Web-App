package com.example.veridic.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.veridic.model.StationData;

public interface StationDataRepository  extends MongoRepository<StationData, String> {

    StationData save(Optional<StationData> data);
    
}
