package ds.sagsa.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity 
@Table(name = Perfil.TABLE_NAME)
@Getter 
@Setter 
@EqualsAndHashCode 
@NoArgsConstructor

public class Perfil {

    public static final String TABLE_NAME = "perfil";

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_perfil")
    @EqualsAndHashCode.Include
    private Long id_perfil;

    @Column(name = "nome_cargo", length = 50, nullable = false, unique = true)
    @NotBlank 
    @NotNull 
    private String nome_cargo;


}
