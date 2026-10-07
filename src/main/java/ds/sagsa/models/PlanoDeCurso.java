package ds.sagsa.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity 
@Table(name = PlanoDeCurso.TABLE_NAME)
@Getter @Setter 
@AllArgsConstructor @NoArgsConstructor 
@EqualsAndHashCode 
public class PlanoDeCurso {
    
    public static final String TABLE_NAME = "plano_de_curso";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plano", unique = true)
    private Long id_plano;

    @Column(name = "titulo", length = 50, nullable = false, unique = true)
    @NotBlank 
    private String titulo;

    @Column(name = "descrição", length = 255, nullable = false)
    @NotNull
    @NotBlank
    private String descrição;

    @Column(name = "carga_horaria", nullable = false)
    @NotNull
    private Boolean cargaHoraria;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;

}
