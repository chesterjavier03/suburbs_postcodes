package com.chesterjavier.dto;

import java.util.Comparator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by chesterjavier
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SuburbsDto implements Comparator<SuburbsDto> {
  private String suburb;
  private String postcode;

  @Override
  public int compare(SuburbsDto o1, SuburbsDto o2) {
    return o1.getSuburb().compareTo(o2.getSuburb());
  }
}
