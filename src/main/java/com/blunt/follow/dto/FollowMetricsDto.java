package com.blunt.follow.dto;

import com.blunt.follow.serializer.ObjectIdSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.ArrayList;
import java.util.Map;
import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class FollowMetricsDto {
  @JsonSerialize(using = ObjectIdSerializer.class)
  private ObjectId bluntId;
  private Long totalFollowers;
  private Long totalFollowings;
  private ArrayList<ArrayList<String>> monthlyFollowers;
}
