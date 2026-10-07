package ds.sagsa.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

@Entity
@Table(name = Usuario.TABLE_NAME)
@Getter 
@Setter 
@NoArgsConstructor 
@EqualsAndHashCode 
@AllArgsConstructor 

public class Usuario {

    public interface CreateUser {}
    public interface UpdateUser {}

    public static final String TABLE_NAME = "usuario";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", unique = true)
    private Long id;

    @Column(name = "nome", length = 100, nullable = false, unique = true)
    @NotNull
    @NotBlank
    @Size(min = 2, max = 100)
    private String username;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    @NotNull
    @NotBlank
    @Size(min = 2, max = 100)
    private String email;

    @Column(name = "ativo", nullable = false)
    @NotNull
    private Boolean ativo = true;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "senha_hash", length = 60, nullable = false)
    @NotNull
    @NotBlank
    @Size(min = 8, max = 60)
    private String senha_hash;

    @Column(name = "data_criação", nullable = false)
    private LocalDateTime data_criação = LocalDateTime.now();

    @ManyToOne 
    @JoinColumn(name = "id_perfil", nullable = false)
    private Perfil perfil;

    @OneToMany(mappedBy = "usuario")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<SAPZ> sapzs = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<DocumentoPdf> documentos = new ArrayList<>();

   
}
