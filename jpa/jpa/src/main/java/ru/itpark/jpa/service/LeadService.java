package ru.itpark.jpa.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itpark.jpa.entity.Address;
import ru.itpark.jpa.entity.Code;
import ru.itpark.jpa.entity.Comment;
import ru.itpark.jpa.entity.Lead;
import ru.itpark.jpa.repository.LeadRepository;

import java.util.List;
import java.util.Map;

@Transactional
@Service
public class LeadService {
  private final LeadRepository repository;

  public LeadService(LeadRepository repository) {
    this.repository = repository;
  }

  public void process() {
    {
      Lead lead = new Lead("Vasya");
      lead.setAddress(new Address("Kazan", "NoStreet"));
      lead.setTags(
          List.of("hot", "dog")
      );
      lead.setComments(List.of(
          new Comment("anonymous", "good guy"),
          new Comment("anonymous", "perspective")
      ));
      lead.setFields(
          Map.of("priority", "high")
      );
      lead.setCode(new Code("secret"));
      repository.save(lead);
    }

    {
      Lead lead = repository.findById(1).orElseThrow(
          () -> new RuntimeException("lead did'nt exists")
      );
      System.out.println(lead);
      System.out.println(lead.getTags());
      System.out.println(lead.getComments().get(0).getAuthor());
      System.out.println(lead.getFields());
      System.out.println(lead.getCode());
    }
  }
}
