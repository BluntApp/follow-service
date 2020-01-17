package com.blunt.follow.config;

import brave.sampler.Sampler;
import com.blunt.follow.mapper.FollowMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FollowServiceConfiguration {

  @Bean
  public Sampler defaultSampler() {
    return Sampler.ALWAYS_SAMPLE;
  }

  @Bean
  public FollowMapper bluntMapper(){
    FollowMapper mapper = Mappers.getMapper(FollowMapper.class);
    return mapper;
  }

}
