package com.blunt.follow.controller;

import com.blunt.follow.dto.FollowDto;
import com.blunt.follow.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
  public ResponseEntity<Object> followBlunt(@RequestBody FollowDto followDto){
    return followService.followBlunt(followDto);
  }

  @GetMapping("followers")
  public ResponseEntity<Object> followers(@RequestHeader(name = "BLUNT-ID", required = true) String bluntId){
    return followService.followers(bluntId);
  }

  @GetMapping("pending")
  public ResponseEntity<Object> pendingFollowers(@RequestHeader(name = "BLUNT-ID", required = true) String bluntId) {
    return followService.pendingFollowers(bluntId);
  }

  @PostMapping("respond")
  public ResponseEntity<Object> respondFollowRequest(
      @RequestHeader(name = "BLUNT-ID", required = true) String bluntId,
      @RequestBody FollowDto followDto) {
    return followService.respondFollowRequest(bluntId, followDto);
  }

}
