package edu.pe.softroute.organizationservice.domain.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Client {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  private String names;

  private String surnames;

  private String dni;

  private String email;

  private String phoneNumber;

  @ManyToOne
  private Company company;
}
