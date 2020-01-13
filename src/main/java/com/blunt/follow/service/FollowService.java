package com.blunt.follow.service;

import com.blunt.follow.dto.FollowDto;
import com.blunt.follow.entity.Follow;
import com.blunt.follow.mapper.FollowMapper;
import com.blunt.follow.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class FollowService {

  private final FollowRepository followRepository;
  private final FollowMapper followMapper;

  public ResponseEntity<Object> followBlunt(FollowDto followDto){
    Follow follow = followMapper.followDtoToFollow(followDto);
    follow = followRepository.save(follow);
    return new ResponseEntity<>(followMapper.followToFollowDto(follow), HttpStatus.OK);
  }





}
