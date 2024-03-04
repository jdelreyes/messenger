package ca.jdelreyes.userservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "roles")
@Getter
@Setter
@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
