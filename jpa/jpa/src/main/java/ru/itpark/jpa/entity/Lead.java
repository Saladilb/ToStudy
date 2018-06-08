package ru.itpark.jpa.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "leads")
// @Access(AccessType.PROPERTY)
public class Lead {
  @Id
  @GeneratedValue
  private int id;
  @Column(nullable = false, columnDefinition = "TEXT")
  private String name;

  @Embedded
  private Address address;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "leads_tags")
  @Column(name = "tag")
  private List<String> tags = new ArrayList<>();

  @ElementCollection
  @CollectionTable(name = "leads_comments")
  private List<Comment> comments = new ArrayList<>();

  @ElementCollection
  @MapKeyColumn(name = "field")
  @Column(name = "value")
  private Map<String, String> fields = new HashMap<>();

  private Code code;

  public Lead() {
  }

  public Lead(String name) {
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public List<String> getTags() {
    return tags;
  }

  public void setTags(List<String> tags) {
    this.tags = tags;
  }

  public List<Comment> getComments() {
    return comments;
  }

  public void setComments(List<Comment> comments) {
    this.comments = comments;
  }

  public Map<String, String> getFields() {
    return fields;
  }

  public void setFields(Map<String, String> fields) {
    this.fields = fields;
  }

  public Code getCode() {
    return code;
  }

  public void setCode(Code code) {
    this.code = code;
  }

  @PrePersist
  public void prePersist() {
    System.out.println("persist");
  }

  @PostLoad
  public void postLoad() {
    System.out.println("loaded");
  }
}
