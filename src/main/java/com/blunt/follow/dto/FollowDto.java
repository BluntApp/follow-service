package com.blunt.follow.dto;

import com.blunt.follow.serializer.ObjectIdSerializer;
import com.blunt.follow.type.Status;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class FollowDto {

  @JsonSerialize(using = ObjectIdSerializer.class)
  private ObjectId id;
  @JsonSerialize(using = ObjectIdSerializer.class)
  private ObjectId bluntId;
  @JsonSerialize(using = ObjectIdSerializer.class)
  private ObjectId followerId;
  private String followerName;
  private String followerUserId;
  private Status status;
}
