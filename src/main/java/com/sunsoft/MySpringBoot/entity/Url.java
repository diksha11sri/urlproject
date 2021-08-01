package com.sunsoft.MySpringBoot.entity;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Url {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
  private String id;
  private String url;
  private LocalDateTime created;

  public Url() {
  }

  public Url(String id, String url, LocalDateTime created) {
    this.id = id;
    this.url = url;
    this.created = created;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public LocalDateTime getCreated() {
    return created;
  }

  public void setCreated(LocalDateTime created) {
    this.created = created;
  }
}