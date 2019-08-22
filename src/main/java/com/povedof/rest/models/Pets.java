package com.povedof.rest.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pets {
	  @Id
	  public ObjectId _id;
	  public String name;
	  public String species;
	  public String breed;
	  
	  // ObjectId needs to be converted to string
	  public String get_id() { return _id.toHexString(); }

	
}
