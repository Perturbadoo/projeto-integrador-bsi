package br.edu.unifio.segundotrabalhoparcial.beans;

import br.edu.unifio.segundotrabalhoparcial.entidades.Vendedor;
import br.edu.unifio.segundotrabalhoparcial.entidades.Visita;
import br.edu.unifio.segundotrabalhoparcial.repositorios.VendedorRepository;
import br.edu.unifio.segundotrabalhoparcial.repositorios.VisitaRepository;
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
public class VisitaBean {
    private List<Visita> visitas;
    private Visita visita;
    @Autowired
    private VisitaRepository visitaRepository;

    public void listar(){
        visitas = visitaRepository.findAll();
    }
    public void novo(){
        visita = new Visita();
    }

    public void salvar(){
        try { visitaRepository.save(visita);
            Messages.addFlashGlobalInfo("Deu certo");
            Faces.navigate("visita-cadastro.xhtml?faces-redirect=true");
            System.out.println("Deu certo");
        } catch (DataIntegrityViolationException excecao){
            excecao.printStackTrace();
        }
    }
    public void salvarEdicao(){
        try {visitaRepository.save(visita);
            Messages.addFlashGlobalInfo("Deu certo");
            Faces.navigate("visita-editar.xhtml?faces-redirect=true");
            System.out.println("Deu certo");
        } catch (DataIntegrityViolationException excecao){
            excecao.printStackTrace();
        }
    }
    public void selecionarExclusao(Visita cursor) {
        Faces.setFlashAttribute("cursor", cursor);
        Faces.navigate("visita-excluir.xhtml?faces-redirect=true");
    }

    public void selecionarEdicao(Visita cursor) {
        Faces.setFlashAttribute("cursor", cursor);
        Faces.navigate("visita-editar.xhtml?faces-redirect=true");
    }

    public void carregar() {
        visita = Faces.getFlashAttribute("cursor");
    }

    public void excluir() {
        try{
            visitaRepository.delete(visita);
            Messages.addFlashGlobalInfo("Registro removido com sucesso");
            Faces.navigate("visita-listar.xhtml?faces-redirect=true");
        } catch (DataIntegrityViolationException excecao) {
            excecao.printStackTrace();
            Messages.addFlashGlobalError("Registro possui registros vinculados");
        }
    }
}

