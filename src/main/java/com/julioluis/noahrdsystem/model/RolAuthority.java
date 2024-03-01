package com.julioluis.noahrdsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity(name = "rol_auth")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolAuthority {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private Long id;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "rol_id")
    private Rol rol;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "authority_id")
    private Authority authority;
}
