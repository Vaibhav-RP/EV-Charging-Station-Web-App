package com.example.veridic.controller;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import lombok.RequiredArgsConstructor;
import com.example.veridic.model.StationData;
import com.example.veridic.repository.StationDataRepository;
import com.example.veridic.service.StationDataService;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(value = "/station")
public class StationDataController {

    @Autowired
    private final  StationDataService stationDataService;

    @Autowired
    private final  StationDataRepository stationDataRepository;
  
    
    @PostMapping("/add")
    public ResponseEntity<StationData> addStation(@Valid @RequestBody StationData entity) {  
        StationData data = stationDataService.addStationtoRepo(entity);
        return new ResponseEntity<>(data, HttpStatus.CREATED); 
    
    }


    @GetMapping("/")
    public ResponseEntity<List<StationData>> getAllStations() {
        if(stationDataRepository.findAll().isEmpty()){
            return ResponseEntity.ok().body(stationDataRepository.findAll());
        }
        return ResponseEntity.ok().body(stationDataService.getStations());
    }


    @GetMapping("/{id}")
    public ResponseEntity<StationData> findStationById(@PathVariable("id") String id) {
  
        Optional<StationData> data = stationDataService.getStationById(id);
    
        if(data.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else
            return new ResponseEntity<>(data.get(), HttpStatus.CREATED);
    }


    @GetMapping("/limit")
    public ResponseEntity<List<StationData>> getStationByLimit(@RequestParam(required = true)  int limit) {
  
        if(stationDataRepository.findAll().isEmpty()){
            return ResponseEntity.ok().body(stationDataRepository.findAll());
        }
        return ResponseEntity.ok().body(stationDataService.getStationsData(limit));
    }


    @GetMapping("/sort-value")
    public ResponseEntity<List<StationData>> getStationBySortValue(@RequestParam(required = true)  String sortType, String value ) {
  
        if(stationDataRepository.findAll().isEmpty()){
            return ResponseEntity.ok().body(stationDataRepository.findAll());
        }
        return ResponseEntity.ok().body(stationDataService.getStationsDataBySortValue(sortType,value));
    }

    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<StationData> deleteStationById(@PathVariable("id") String id) {
  
        Optional<StationData> data = stationDataService.getStationById(id);
    
        if(data.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            stationDataRepository.deleteById(id);
            return new ResponseEntity<>(data.get(), HttpStatus.CREATED);
        }
    }

     
    @PutMapping("/update/{id}")
    public ResponseEntity<StationData> updateStationData(@RequestBody StationData entity, @PathVariable String id) {
        if(stationDataRepository.existsById(id)){
        return ResponseEntity.ok().body(stationDataService.updateStationDataById(entity,id));
        }  
        else{
        throw new ResponseStatusException(
            HttpStatus.NOT_FOUND, "entity not found"
        );  
        }
    
    }
 
  
}
