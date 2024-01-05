package com.julioluis.cenordlayered.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

@Entity
@Table(name = "cnd_donate",indexes = {@Index(name = "created_at_index",columnList = "createdAt")})
@Data
public class Donate {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private Long id;

    private String commet;
    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    private LocalDate createdAt;


}
