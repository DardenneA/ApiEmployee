package com.openclassroom.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity //annotation qui précise que la classe correspond à une table de la bdd
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="first_name")
    private String firstName; //VARCHAR(250) NOT NULL,
    @Column(name="last_name")
    private String lastName; //VARCHAR(250) NOT NULL,
    //@Column(name="mail") --> pas besoin car le nom de la colonne est identique
    private String mail; // VARCHAR(250) NOT NULL,
   // @Column(name="password") --> pas besoin car le nom de la colonne est identique
   private String password; //VARCHAR(250) NOT NULL
}
