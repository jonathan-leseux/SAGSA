package ds.sagsa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ds.sagsa.models.Perfil;
import ds.sagsa.repositories.PerfilRepository;

@Service 
public class PerfilService {
    
    @Autowired 
    private PerfilRepository perfilRepository;

    public Perfil findById(Long id){
        Optional<Perfil> perfil = this.perfilRepository.findById(id);
        return perfil.orElseThrow(() -> new RuntimeException(
            "Perfil nao encontrado! Id: " + id + ", Tipo: " + Perfil.class.getName()
        ));
    }

    public List<Perfil> findAll(){
        return this.perfilRepository.findAll();
    }

    @Transactional 
    public Perfil create(Perfil obj) {
        obj.setId_perfil(null);
            obj = this.perfilRepository.save(obj);
            return obj;
    }

    @Transactional 
    public Perfil update(Perfil obj) {
        Perfil newObj = findById(obj.getId_perfil());
        newObj.setNome_cargo(obj.getNome_cargo());
        return this.perfilRepository.save(newObj);
    }

    public void delete(Long id) {
        findById(id);
        try {
            this.perfilRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Nao é possivel excluir, pois há entidades relacionadas!");
        }
    }
}
