package com.blunt.follow.controller;

import com.blunt.follow.dto.FollowDto;
import com.blunt.follow.service.FollowService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/follow")
public class FollowController {

  private final FollowService followService;

  @GetMapping("test")
  public String followTest() {
    return "Success";
  }

  @PostMapping
  // security check only loggedIn user can access this api
  // spring security and JWT
  // get bluntId from JWT token
  public ResponseEntity<Object> followBlunt(@RequestBody FollowDto followDto){
    return followService.followBlunt(followDto);
  }

  @PutMapping
  public ResponseEntity<Object> updateFollowBlunt(@RequestBody List<FollowDto> followDtoList){
    return followService.updateFollowBlunt(followDtoList);
  }

  @GetMapping("followers/{mobile}")
  public ResponseEntity<Object> fetchInactiveFollowers(@PathVariable String mobile){
    return followService.fetchInactiveFollowers(mobile);
  }

  @GetMapping("followers")
  public ResponseEntity<Object> followers(@RequestHeader(name = "BLUNT-ID", required = true) String bluntId){
    return followService.followers(bluntId);
  }

  @GetMapping("following")
  public ResponseEntity<Object> following(@RequestHeader(name = "BLUNT-ID", required = true) String bluntId){
    return followService.following(bluntId);
  }

  @GetMapping("pending")
  public ResponseEntity<Object> pendingFollowers(@RequestHeader(name = "BLUNT-ID", required = true) String bluntId) {
    return followService.pendingFollowers(bluntId);
  }

  @GetMapping("check/{nickName}")
  public ResponseEntity<Object> checkNickName(@RequestHeader(name = "BLUNT-ID", required = true) String bluntId, @PathVariable String nickName) {
    return followService.checkNickName(bluntId, nickName);
  }

  @PostMapping("respond")
  public ResponseEntity<Object> respondFollowRequest(
      @RequestHeader(name = "BLUNT-ID", required = true) String bluntId,
      @RequestBody FollowDto followDto) {
    return followService.respondFollowRequest(bluntId, followDto);
  }

  @GetMapping("/search/{key}")
  public ResponseEntity<Object> searchAlien(@RequestHeader(name = "BLUNT-ID", required = true) String bluntId,@PathVariable String key) {
    return followService.searchAlienByKey(key);
  }

  @GetMapping("metrics")
  public ResponseEntity<Object> getMetrics(
      @RequestHeader(name = "BLUNT-ID", required = true) String bluntId) {
    return followService.getMetrics(bluntId);
  }

}
