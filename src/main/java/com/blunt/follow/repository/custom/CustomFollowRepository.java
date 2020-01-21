package com.blunt.follow.repository.custom;

import com.blunt.follow.entity.Follow;
import java.util.List;

public interface CustomFollowRepository {

  List<Follow> findAlienByKey(String key);
}
