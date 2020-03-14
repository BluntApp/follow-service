package com.blunt.follow.dto;

import com.blunt.follow.serializer.ObjectIdSerializer;
import com.blunt.follow.type.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.LocalDateTime;
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
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime followedOn;
  private String bluntName;
  private String bluntNickName;
  private String mobile;
  private String followerName;
  private String followerNickName;
  private String followerUserId;
  private Status status;
}
