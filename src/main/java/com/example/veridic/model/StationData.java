package com.example.veridic.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "stations")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class StationData {

    @Id
    String id;
    
    private String station_id;
    
    private String station_name;
  
    private String station_image;
   
    private String station_pricing;
 
    private String station_address;
}
