package com.sunsoft.MySpringBoot.controller;

import com.google.common.hash.Hashing;
import com.sunsoft.MySpringBoot.entity.Url;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import javax.validation.constraints.NotNull;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class storeURLController {

  @Autowired
  private RedisTemplate<String, Url> redisTemplate;

  @Value("${redis.ttl}")
  private long ttl;

  @GetMapping
  public ResponseEntity getUrl(@PathVariable String id) {
    Url url = redisTemplate.opsForValue().get(id);
    return ResponseEntity.ok(url);
  }

  @PostMapping
  public ResponseEntity postUrl(@RequestBody @NotNull Url url) {

    String id = Hashing.murmur3_32().hashString(url.getUrl(), Charset.defaultCharset()).toString();
    url.setId(id);
    url.setCreated(LocalDateTime.now());

    redisTemplate.opsForValue().set(url.getId(), url, ttl, TimeUnit.SECONDS);

    return ResponseEntity.ok(url);
  }
  

}