package com.chesterjavier.service;

import com.chesterjavier.dto.SuburbsDto;
import com.chesterjavier.entity.Suburbs;

import lombok.extern.slf4j.Slf4j;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by chesterjavier
 */

@Service
@Slf4j
@Repository
@Transactional
public class SuburbsService {

  @Autowired
  private EntityManager em;

  public void addSuburbs(SuburbsDto suburbs) {
    log.info("[SuburbsService] - Adding Suburbs into the database....");
    em.persist(new Suburbs(suburbs));
    em.flush();
  }

  public JSONObject fetchAllSuburbs() {
    log.info("[SuburbsService] - Fetching all Suburbss in the database....");
    CriteriaBuilder builder = em.getCriteriaBuilder();
    CriteriaQuery<Suburbs> query = builder.createQuery(Suburbs.class);
    Root<Suburbs> suburbs = query.from(Suburbs.class);
    query.select(suburbs);
    TypedQuery<Suburbs> result = em.createQuery(query);
    List<SuburbsDto> suburbsList = result.getResultList().stream().filter(Objects::nonNull).map(data -> {
      return SuburbsDto.builder().suburb(data.getSuburb()).postcode(data.getPostCode()).build();
    }).collect(Collectors.toList());
    JSONObject json = new JSONObject();
    json.put("suburbs", suburbsList);
    return json;
  }

  public JSONObject fetchAllSuburbsInPostCodeRange(int from, int to) {
    log.info("[SuburbsService] - Fetching all Suburbss in the database in range from [{}] - [{}]", from, to);
    CriteriaBuilder builder = em.getCriteriaBuilder();
    CriteriaQuery<Suburbs> query = builder.createQuery(Suburbs.class);
    Root<Suburbs> suburbs = query.from(Suburbs.class);
    Predicate range = builder.between(suburbs.get("postCode"), from, to);
    query.select(suburbs).where(range);
    TypedQuery<Suburbs> result = em.createQuery(query);

    Long totalCount = result.getResultList().stream().filter(Objects::nonNull)
        .map(Suburbs::getSuburb)
        .flatMapToInt(String::chars).count();

    List<SuburbsDto> suburbsList = result.getResultList().stream().filter(Objects::nonNull)
        .map(data -> SuburbsDto.builder().suburb(data.getSuburb()).postcode(data.getPostCode())
            .build())
        .sorted(Comparator.comparing(SuburbsDto::getSuburb)).collect(Collectors.toList());

    JSONObject json = new JSONObject();
    json.put("suburbs", suburbsList);
    json.put("totalNumberOfCharacters", totalCount);
    return json;
  }
}
