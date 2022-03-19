package com.chesterjavier.entity;

import com.chesterjavier.dto.SuburbsDto;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by chesterjavier
 */

@Table(name = "SUBURBS")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Suburbs {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @JsonProperty("suburb")
  @Column(name = "suburb")
  private String suburb;

  @JsonProperty("postcode")
  @Column(name = "post_code")
  private String postCode;

  public Suburbs(SuburbsDto suburbs) {
    this.suburb = suburbs.getSuburb();
    this.postCode = suburbs.getPostcode();
  }
}