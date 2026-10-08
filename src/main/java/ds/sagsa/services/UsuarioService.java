package ds.sagsa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ds.sagsa.models.Usuario;
import ds.sagsa.repositories.UsuarioRepository;

@Service 
public class UsuarioService {

    @Autowired 
    private UsuarioRepository usuarioRepository;

    public Usuario findById(Long id) {
        Optional<Usuario> usuario = this.usuarioRepository.findById(id);
        return usuario.orElseThrow(() -> new RuntimeException(
            "Usuario nao encontrado! Id:" + id + ", Tipo: " + Usuario.class.getName()
        ));
    }

    public List<Usuario> findAll() {
        return this.usuarioRepository.findAll();
    }

    @Transactional
    public Usuario create(Usuario obj) {
        obj.setId(null);
        obj = this.usuarioRepository.save(obj);
        return obj;
    }

    @Transactional
    public Usuario update(Usuario obj) {
        Usuario newObj = findById(obj.getId());
        newObj.setUsername(obj.getUsername());
        newObj.setEmail(obj.getEmail());
        newObj.setSenha_hash(obj.getSenha_hash());
        newObj.setAtivo(obj.getAtivo());
        return this.usuarioRepository.save(newObj);
    }

    public void delete(Long id) {
        findById(id);
        try {
            this.usuarioRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir pois há entidades relacionadas!");
        }
    }
}