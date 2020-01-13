package com.blunt.follow.mapper;

import com.blunt.follow.dto.FollowDto;
import com.blunt.follow.entity.Follow;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FollowMapper {

  FollowMapper INSTANCE = Mappers.getMapper(FollowMapper.class);

  public abstract FollowDto followToFollowDto(Follow follow);

  public abstract List<FollowDto> followListToFollowDtoList(List<Follow> follow);

  public abstract Follow followDtoToFollow(FollowDto followDto);
}
