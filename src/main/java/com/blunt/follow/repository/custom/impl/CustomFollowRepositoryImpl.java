package com.blunt.follow.repository.custom.impl;


import static com.blunt.follow.util.BluntConstant.CASE_INSENSITIVE;

import com.blunt.follow.entity.Follow;
import com.blunt.follow.repository.custom.CustomFollowRepository;
import com.blunt.follow.type.Status;
import io.micrometer.core.instrument.util.StringUtils;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CustomFollowRepositoryImpl implements CustomFollowRepository {

  private final MongoTemplate mongoTemplate;

  @Override
  public List<Follow> findAlienByKey(String key) {
    if (StringUtils.isBlank(key) || key.length()<3) {
      return Collections.EMPTY_LIST;
    }
    String value = Pattern.quote(key);
    Criteria criteria = new Criteria();
    criteria.orOperator(
        Criteria.where("followerName").regex(value, CASE_INSENSITIVE),
        Criteria.where("followerNickName").regex(value, CASE_INSENSITIVE),
        Criteria.where("followerUserId").regex(value, CASE_INSENSITIVE)
    );
    Query query = new Query();
    query.addCriteria(criteria.andOperator(Criteria.where("status").is(Status.ACCEPTED)));
    addProjections(query);
    return mongoTemplate.find(query, Follow.class);
  }

  private void addProjections(Query query) {
    query.fields()
        .include("_id")
        .include("followerId")
        .include("followerName")
        .include("followerNickName")
        .include("bluntId");
  }
}
