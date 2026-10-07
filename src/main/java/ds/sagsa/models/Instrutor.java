package ds.sagsa.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity 
@Table(name = Instrutor.TABLE_NAME)
@Getter
@AllArgsConstructor 
@NoArgsConstructor 
@EqualsAndHashCode 
@Setter 

public class Instrutor {

    public static final String TABLE_NAME = "instrutor";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_instrutor", unique = true)
    private Long id_instrutor;

    @Column(name = "nome", length = 100, nullable = false)
    @NotBlank 
    private String nome;

    @Column(name = "cpf", length = 14, nullable = false)
    @NotBlank 
    private String cpf;

    @Column(name = "email", length = 100, nullable = false)
    @NotBlank 
    private String email;

    @Column(name = "especialidade", length = 100)
    @NotBlank 
    private String especialidade;
    
    @Column(name = "ativo", nullable = false)
    @NotBlank 
    private Boolean ativo = true;
}
