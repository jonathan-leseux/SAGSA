package ds.sagsa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ds.sagsa.models.Instrutor;
import ds.sagsa.models.SAPZ;
import ds.sagsa.models.Usuario;
import ds.sagsa.repositories.SAPZRepository;

@Service 
public class SAPZService {

    @Autowired 
    private SAPZRepository sapzRepository;

    @Autowired 
    private UsuarioService usuarioService;

    @Autowired 
    private InstrutorService instrutorService;

    public SAPZ findById(Long id) {
        Optional<SAPZ> sapz = this.sapzRepository.findById(id);
        return sapz.orElseThrow(() -> new RuntimeException(
            "SAPZ não encontrada! Id: " + id + ", Tipo: " + SAPZ.class.getName()
        ));
    }

    public List<SAPZ> findAll() {
        return this.sapzRepository.findAll();
    }

    public List<SAPZ> findByUsuario_Id_usuario(Long usuarioId) {
        this.usuarioService.findById(usuarioId);
        return this.sapzRepository.findByUsuario_Id_usuario(usuarioId);
    }
}
