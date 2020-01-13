package com.blunt.follow.entity;

import com.blunt.follow.serializer.ObjectIdSerializer;
import com.blunt.follow.type.Status;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Follow {

  @Id
  @JsonSerialize(using = ObjectIdSerializer.class)
  private ObjectId id;
  @JsonSerialize(using = ObjectIdSerializer.class)
  private ObjectId bluntId;
  @JsonSerialize(using = ObjectIdSerializer.class)
  private ObjectId followedBy;
  private Status status;
}
