package com.chesterjavier.controller;

import com.chesterjavier.dto.SuburbsDto;
import com.chesterjavier.service.SuburbsService;
import lombok.extern.slf4j.Slf4j;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by chesterjavier
 */

@RestController
@RequestMapping(path = "/api")
@Slf4j
public class SuburbsController {

  @Autowired
  private SuburbsService SuburbsService;

  @PostMapping(path = "/suburbs", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> addSuburbNameList(@RequestBody List<SuburbsDto> suburbsDto) {
    try {
      log.info("[SuburbsController] - Adding new suburb name list into the database....");
      suburbsDto.stream().filter(Objects::nonNull).forEach(data -> SuburbsService.addSuburbs(data));
      return new ResponseEntity<>("Creation successful!", HttpStatus.CREATED);
    } catch (Exception e) {
      log.error("[SuburbsController] ERROR [{}]", e.getLocalizedMessage());
      return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping(path = "/suburb-list", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> suburbNameList() {
    log.info("[SuburbsController] - Listing all suburbs...");
    return Optional.ofNullable(SuburbsService.fetchAllSuburbs())
        .map(result -> new ResponseEntity<>(result, HttpStatus.OK))
        .orElse(new ResponseEntity("No data found", HttpStatus.NOT_FOUND));
  }

  @GetMapping(path = "/suburb-list-range", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> suburbNameListInRange(@RequestParam int fromPostCode, @RequestParam int toPostCode) {
    log.info("[SuburbsController] - Listing all suburbs...");
    return Optional.ofNullable(SuburbsService.fetchAllSuburbsInPostCodeRange(fromPostCode, toPostCode))
        .map(result -> new ResponseEntity<>(result, HttpStatus.OK))
        .orElse(new ResponseEntity("No data found", HttpStatus.NOT_FOUND));
  }
}
