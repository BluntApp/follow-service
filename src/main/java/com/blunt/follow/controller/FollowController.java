package com.blunt.follow.controller;

import com.blunt.follow.dto.FollowDto;
import com.blunt.follow.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/follow")
public class FollowController {

  private final FollowService followService;

  @GetMapping("test")
  public ResponseEntity<Object> testService() {
    return new ResponseEntity<>("Success", HttpStatus.OK);
  }

  @PostMapping("/follow")
  public ResponseEntity<Object> followBlunt(FollowDto followDto){
    return followService.followBlunt(followDto);
  }

}
