package ds.sagsa.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = SAPZ.TABLE_NAME)
@Getter 
@AllArgsConstructor 
@EqualsAndHashCode 
@NoArgsConstructor 
@Setter 

public class SAPZ {

    public static final String TABLE_NAME = "sapz";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sapz", unique = true)
    private Long id_sapz;

    @Column(name = "codigo", length = 50, nullable = false, unique = true)
    @NotBlank 
    private String codigo;

    @Column(name = "descrição", length = 255, nullable = false)
    @NotNull
    @NotBlank
    private String descrição;

    @Column(name = "status", nullable = false)
    @NotNull
    private Boolean status = true;

    @Column(name = "data_criação", nullable = false)
    private LocalDateTime data_criação = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false, updatable = false)
    private Usuario usuario;

    @ManyToOne 
    @JoinColumn(name = "id_plano")
    private PlanoDeCurso planoDeCurso;

   @ManyToMany 
    @JoinTable(
        name = "sapz_instrutor",
        joinColumns = @JoinColumn(name = "id_sapz"),
        inverseJoinColumns = @JoinColumn(name = "id_instrutor")
    )
    private List<Instrutor> instrutores = new ArrayList<>(); 
}

