package com.example.veridic.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import com.example.veridic.model.StationData;
import com.example.veridic.repository.StationDataRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StationDataService {
    
    @Autowired
    private  StationDataRepository stationDataRepository;

    @Autowired
    private MongoTemplate mongoTemplate;
  
    
    public StationData addStationtoRepo(StationData entity) {
        stationDataRepository.save(entity);
        return entity;
    }


    public List<StationData> getStations() {
        return stationDataRepository.findAll();
    }
    

    public Optional<StationData> getStationById(String id) {
        return stationDataRepository.findById(id);
    }

    public List<StationData> getStationsData(int limit) {
        Query query = new Query();
        query.limit(limit);
        query.with(Sort.by(Sort.Direction.DESC, "id"));
        List<StationData> data = mongoTemplate.find(query,StationData.class);
        return data;
    }


    public List<StationData> getStationsDataBySortValue(String sortType,String value) {
        Query query = new Query();
        if(sortType.equals("ASC") || sortType.equals("asc"))
            query.with(Sort.by(Sort.Direction.ASC, value));
        else
            query.with(Sort.by(Sort.Direction.DESC, value));
        List<StationData> data = mongoTemplate.find(query,StationData.class);
        return data;
    }
    

    public StationData updateStationDataById(StationData entity,String id) {
        Optional<StationData> data = stationDataRepository.findById(id);
        data.get().setStation_id(entity.getStation_id());
        data.get().setStation_name(entity.getStation_name());
        data.get().setStation_image(entity.getStation_image());
        data.get().setStation_pricing(entity.getStation_pricing());
        data.get().setStation_address(entity.getStation_address());
        stationDataRepository.save(data.get());
        return data.get();
       }
  

}
