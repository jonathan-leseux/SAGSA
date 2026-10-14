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

    public List<SAPZ> findByUsuarioId(Long usuarioId) {
        this.usuarioService.findById(usuarioId);
        return this.sapzRepository.findByUsuario_IdUsuario(usuarioId);
    }

    public List<SAPZ> findByInstrutorId(Long instrutorId){
        this.usuarioService.findById(instrutorId);
        return this.sapzRepository.findByInstrutor_IdInstrutor(instrutorId);
    }

    @Transactional 
    public SAPZ create(SAPZ obj){
        Usuario usuario = this.usuarioService.findById(obj.getUsuario().getIdUsuario());
        obj.setUsuario(usuario);

        if(obj.getInstrutores() != null && obj.getInstrutor().getIdInstrutor() != null) {
            Instrutor instrutor = this.instrutorService.findById(obj.getInstrutor().getIdInstrutor());
            obj.setInstrutor(instrutor);
        }

        obj.setIdSapz(null);
        obj = this.sapzRepository.save(obj);
        return obj;
    }

    @Transactional 
    public SAPZ update(SAPZ obj) {
        SAPZ newObj = findById(obj.getIdSapz());
        newObj.setCodigo(obj.getCodigo());
        newObj.setDescricao(obj.getDescricao());
        newObj.setStatus(obj.getStatus());
        return this.sapzRepository.save(newObj);
    }

    public void delete(Long id){
        findById(id);
        try{
            this.sapzRepository.deleteById(id);
        } catch (Exception e){
            throw new RuntimeException("Não foi possivel excluir pois há entidades relacionadas!");
        }
    }
}
