package ds.sagsa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ds.sagsa.models.*;
import ds.sagsa.repositories.*;

@Service 
public class DocumentoPdfService {
    
    @Autowired 
    private DocumentoPdfRepository documentoPdfRepository;

    @Autowired 
    private UsuarioService usuarioService;

    public DocumentoPdf findById(Long id) {
        Optional<DocumentoPdf> documento = this.documentoPdfRepository.findById(id);
        return documento.orElseThrow(() -> new RuntimeException(
            "Documento PDF nao encontrado! Id: " + id + ", Tipo: " + DocumentoPdf.class.getName()
        ));
    }

    public List<DocumentoPdf> findAll(){
        return this.documentoPdfRepository.findAll();
    }

    public List<DocumentoPdf> findByUsuarioId(Long usuarioId){
        this.usuarioService.findById(usuarioId);
        return this.documentoPdfRepository.findByUsuario_id_usuario(usuarioId);
    }

    @Transactional 
    public DocumentoPdf create(DocumentoPdf obj) {
        Usuario usuario = this.usuarioService.findById(obj.getUsuario().getIdUsuario());
        obj.setUsuario(usuario);

        obj.setIdDocumento(null);
        obj = this.documentoPdfRepository.save(obj);
        return obj;
    }

    @Transactional 
    public DocumentoPdf update(DocumentoPdf obj){
        DocumentoPdf newObj = findById(obj.getIdDocumento());
        newObj.setCaminhoArquivo(obj.getCaminhoArquivo());
        newObj.setDataGeracao(obj.getDataGeracao());
        return this.documentoPdfRepository.save(newObj);
    }

    public void delete(Long id){
        findById(id);
        try {
            this.documentoPdfRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possivel excluir pois ha entidades relacionadas!");
        }
    }
}
