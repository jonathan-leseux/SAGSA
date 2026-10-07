package ds.sagsa.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity 
@Table(name = DocumentoPdf.TABLE_NAME) 
@Getter @Setter 
@AllArgsConstructor @NoArgsConstructor 
@EqualsAndHashCode

public class DocumentoPdf {
    
    public static final String TABLE_NAME = "documento_pdf";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_documento", unique = true)
    private Long id_documento;

    @Column(name = "caminho_arquivo", length = 255, nullable = false)
    private String caminho_arquivo;

    @Column(name = "data_geração", nullable = false, updatable = false)
    private LocalDateTime data_geração = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne 
    @JoinColumn(name = "id_sapz", nullable = false)
    private SAPZ sapz;

}
