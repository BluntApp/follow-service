package com.blunt.follow.repository;

import com.blunt.follow.entity.Follow;
import com.blunt.follow.repository.custom.CustomFollowRepository;
import com.blunt.follow.type.Status;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends MongoRepository<Follow, ObjectId>,
    CustomFollowRepository {

  List<Follow> findAllByFollowerIdAndStatus(ObjectId bluntId, Status status);

  List<Follow> findAllByMobileAndStatus(String mobile, Status status);

  List<Follow> findAllByBluntIdAndStatus(ObjectId bluntId, Status status);

  Follow findByBluntIdAndFollowerId(ObjectId bluntId, ObjectId followerId);

  Follow findByBluntIdAndFollowerNickName(ObjectId bluntId, String followerNickName);

  Follow findByFollowerIdAndMobile(ObjectId followerId, String mobile);
}
