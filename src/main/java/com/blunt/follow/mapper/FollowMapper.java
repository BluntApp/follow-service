package com.blunt.follow.mapper;

import com.blunt.follow.dto.FollowDto;
import com.blunt.follow.entity.Follow;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper
public interface FollowMapper {

  FollowDto followToFollowDto(Follow follow);

  List<FollowDto> followListToFollowDtoList(List<Follow> follow);

  List<Follow> followDtoListToFollowList(List<FollowDto> followDto);

  Follow followDtoToFollow(FollowDto followDto);
}
