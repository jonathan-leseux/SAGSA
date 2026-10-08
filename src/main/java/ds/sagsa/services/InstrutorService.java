package ds.sagsa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ds.sagsa.models.Instrutor;
import ds.sagsa.repositories.InstrutorRepository;

@Service 
public class InstrutorService {
    
    @Autowired 
    private InstrutorRepository instrutorRepository;

    public Instrutor findById(Long id){
        Optional<Instrutor> instrutor = this.instrutorRepository.findById(id);
        return instrutor.orElseThrow(() -> new RuntimeException(
            "Instrutor não encontrado! Id: " + id + ", Tipo: " + Instrutor.class.getName()
        ));
    }

    public List<Instrutor> findAll(){
        return this.instrutorRepository.findAll();
    }

    @Transactional 
    public Instrutor create(Instrutor obj){
        obj.setId_instrutor(null);
        obj = this.instrutorRepository.save(obj);
        return obj;
    }

    @Transactional 
    public Instrutor update(Instrutor obj){
        Instrutor newObj = findById(obj.getId_instrutor());
        newObj.setNome(obj.getNome());
        newObj.setCpf(obj.getCpf());
        newObj.setEmail(obj.getEmail());
        newObj.setEspecialidade(obj.getEspecialidade());
        newObj.setAtivo(obj.getAtivo());
        return this.instrutorRepository.save(newObj);
    }
    
    public void delete(Long id){
        findById(id);
        try {
            this.instrutorRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possivel excluir pois há entidades relacionadas!");
        }
    }
}
