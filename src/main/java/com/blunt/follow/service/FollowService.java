package com.blunt.follow.service;

import com.blunt.follow.dto.FollowDto;
import com.blunt.follow.entity.Follow;
import com.blunt.follow.error.BluntException;
import com.blunt.follow.mapper.FollowMapper;
import com.blunt.follow.repository.FollowRepository;
import com.blunt.follow.type.Status;
import com.blunt.follow.util.BluntConstant;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

@Service
@Slf4j
@RequiredArgsConstructor
public class FollowService {

  private final FollowRepository followRepository;
  private final FollowMapper followMapper;

  public ResponseEntity<Object> followBlunt(FollowDto followDto){
    validateFollowRequest(followDto);
    Follow follow = followMapper.followDtoToFollow(followDto);
    follow = followRepository.save(follow);
    return new ResponseEntity<>(followMapper.followToFollowDto(follow), HttpStatus.OK);
  }

  private void validateFollowRequest(FollowDto followDto) {
    Follow follow =  followRepository.findByBluntIdAndFollowerId(followDto.getBluntId(), followDto.getFollowerId());
    if(!ObjectUtils.isEmpty(follow)){
      if (follow.getStatus().equals(Status.ACCEPTED)) {
        throw new BluntException(BluntConstant.FOLLOWING_ALREADY, HttpStatus.CONFLICT.value(),
            HttpStatus.CONFLICT);
      } else {
        throw new BluntException(BluntConstant.FOLLOW_REQUESTED_ALREADY,
            HttpStatus.CONFLICT.value(),
            HttpStatus.CONFLICT);
      }
    }
  }

  public ResponseEntity<Object> followers(String bluntId) {
    List<Follow> followers = followRepository.findAllByBluntIdAndStatus(new ObjectId(bluntId),
        Status.ACCEPTED);
    List<FollowDto> followDtoList = followMapper.followListToFollowDtoList(followers);
    return new ResponseEntity<>(followDtoList, HttpStatus.OK);
  }

  public ResponseEntity<Object> pendingFollowers(String bluntId) {
    List<Follow> followers = followRepository.findAllByBluntIdAndStatus(new ObjectId(bluntId),
        Status.PENDING);
    List<FollowDto> followDtoList = followMapper.followListToFollowDtoList(followers);
    return new ResponseEntity<>(followDtoList, HttpStatus.OK);
  }

  public ResponseEntity<Object> respondFollowRequest(String bluntId, FollowDto followDto) {
    Follow follow = followRepository
        .findByBluntIdAndFollowerId(new ObjectId(bluntId), followDto.getFollowerId());
    follow.setStatus(followDto.getStatus());
    follow.setFollowerNickName(StringUtils.isEmpty(followDto.getFollowerNickName())?follow.getFollowerName():followDto.getFollowerNickName());
    return new ResponseEntity<>(followRepository.save(follow), HttpStatus.OK);
  }

  public ResponseEntity<Object> searchAlienByKey(String key) {
    List<Follow> followers = followRepository.findAlienByKey(key);
    return new ResponseEntity<>(followers, HttpStatus.OK);
  }

  public ResponseEntity<Object> checkNickName(String bluntId, String nickName) {
    if(!validateNickName(bluntId, nickName)){
      throw new BluntException(BluntConstant.NICKNAME_UNAVAILABLE, HttpStatus.CONFLICT.value(),
          HttpStatus.CONFLICT);
    }
    return new ResponseEntity<>("Success", HttpStatus.OK);
  }

  private Boolean validateNickName(String bluntId, String nickName) {
    if(nickName.trim().isEmpty()){
      return true;
    }
    Follow follow = followRepository.findByBluntIdAndFollowerNickName(new ObjectId(bluntId), nickName);
    return ObjectUtils.isEmpty(follow);
  }
}


