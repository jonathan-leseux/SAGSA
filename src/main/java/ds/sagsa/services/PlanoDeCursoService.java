package ds.sagsa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ds.sagsa.models.*;
import ds.sagsa.repositories.*;

@Service 
public class PlanoDeCursoService {
    
    @Autowired 
    private PlanoDeCursoRepository planoDeCursoRepository;

    @Autowired 
    private SAPZService sapzService;

    public PlanoDeCurso findById(Long id){
        Optional<PlanoDeCurso> plano = this.planoDeCursoRepository.findById(id);
        return plano.orElseThrow(() -> new RuntimeException(
            "Plano de Curso não encontrado! Id: " + id + ", Tipo: " + PlanoDeCurso.class.getName()
        ));
    }
    
    public List<PlanoDeCurso> findAll() {
        return this.planoDeCursoRepository.findAll();
    }

    public List<PlanoDeCurso> findBySapzId(Long sapzId) {
        this.sapzService.findById(sapzId);
        return this.planoDeCursoRepository.findBySapz_Id_sapz(sapzId);
    }

    @Transactional 
    public PlanoDeCurso create(PlanoDeCurso obj) {
        if (obj.getSapz() != null && obj.setSapz().getIdSapz() != null){
            SAPZ sapz = this.sapzService.findById(obj.getSapz().getIdSapz());
            obj.setSapz(sapz);
        }

        obj.setIdSapz(null);
        obj = this.planoDeCursoRepository.save(obj);
        return obj;
    }

    @Transactional
    public PlanoDeCurso update(PlanoDeCurso obj){
        PlanoDeCurso newObj = findById(obj.getIdPlano());
        newObj.setTitulo(obj.getTitulo());
        newObj.setDescricao(obj.getDescricao());
        newObj.setCargaHoraria(obj.getCargaHoraria());
        newObj.setAtivo(obj.getAtivo());
        return this.planoDeCursoRepository.save(newObj);
    }

    public void delete(Long id) {
        findById(id);
        try {
            this.planoDeCursoRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possivel excluir pois há entidades relacionadas!");
        }
    }
}
