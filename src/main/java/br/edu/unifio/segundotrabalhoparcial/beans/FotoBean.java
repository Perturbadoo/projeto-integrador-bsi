package br.edu.unifio.segundotrabalhoparcial.beans;

import br.edu.unifio.segundotrabalhoparcial.entidades.Colaborador;
import br.edu.unifio.segundotrabalhoparcial.entidades.Foto;
import br.edu.unifio.segundotrabalhoparcial.repositorios.ColaboradorRepository;
import br.edu.unifio.segundotrabalhoparcial.repositorios.FotoRepository;
import lombok.Data;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import javax.faces.view.ViewScoped;
import java.util.List;

@Component
@ViewScoped
@Data
public class FotoBean {
    private List<Foto> fotos;
    private Foto foto;
    @Autowired
    private FotoRepository fotoRepository;

    public void listar(){
        fotos = fotoRepository.findAll();
    }
    public void novo(){
        foto = new Foto();
    }

    public void salvar(){
        try { fotoRepository.save(foto);
            Messages.addFlashGlobalInfo("Deu certo");
            Faces.navigate("foto-cadastro.xhtml?faces-redirect=true");
            System.out.println("Deu certo");
        } catch (DataIntegrityViolationException excecao){
            excecao.printStackTrace();
        }
    }
    public void salvarEdicao(){
        try {fotoRepository.save(foto);
            Messages.addFlashGlobalInfo("Deu certo");
            Faces.navigate("foto-editar.xhtml?faces-redirect=true");
            System.out.println("Deu certo");
        } catch (DataIntegrityViolationException excecao){
            excecao.printStackTrace();
        }
    }
    public void selecionarExclusao(Foto cursor) {
        Faces.setFlashAttribute("cursor", cursor);
        Faces.navigate("foto-excluir.xhtml?faces-redirect=true");
    }

    public void selecionarEdicao(Foto cursor) {
        Faces.setFlashAttribute("cursor", cursor);
        Faces.navigate("foto-editar.xhtml?faces-redirect=true");
    }

    public void carregar() {
        foto = Faces.getFlashAttribute("cursor");
    }

    public void excluir() {
        try{
            fotoRepository.delete(foto);
            Messages.addFlashGlobalInfo("Registro removido com sucesso");
            Faces.navigate("foto-listar.xhtml?faces-redirect=true");
        } catch (DataIntegrityViolationException excecao) {
            excecao.printStackTrace();
            Messages.addFlashGlobalError("Registro possui registros vinculados");
        }
    }
}

