package br.edu.unifio.segundotrabalhoparcial.beans;

import br.edu.unifio.segundotrabalhoparcial.entidades.Cliente;
import br.edu.unifio.segundotrabalhoparcial.entidades.Colaborador;
import br.edu.unifio.segundotrabalhoparcial.repositorios.ClienteRepository;
import br.edu.unifio.segundotrabalhoparcial.repositorios.ColaboradorRepository;
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
public class ColaboradorBean {
    private List<Colaborador> colaboradors;
    private Colaborador colaborador;
    @Autowired
    private ColaboradorRepository colaboradorRepository;

    public void listar(){
        colaboradors = colaboradorRepository.findAll();
    }
    public void novo(){
        colaborador = new Colaborador();
    }

    public void salvar(){
        try { colaboradorRepository.save(colaborador);
            Messages.addFlashGlobalInfo("Deu certo");
            Faces.navigate("colaborador-cadastro.xhtml?faces-redirect=true");
            System.out.println("Deu certo");
        } catch (DataIntegrityViolationException excecao){
            excecao.printStackTrace();
        }
    }
    public void salvarEdicao(){
        try { colaboradorRepository.save(colaborador);
            Messages.addFlashGlobalInfo("Deu certo");
            Faces.navigate("colaborador-editar.xhtml?faces-redirect=true");
            System.out.println("Deu certo");
        } catch (DataIntegrityViolationException excecao){
            excecao.printStackTrace();
        }
    }
    public void selecionarExclusao(Colaborador cursor) {
        Faces.setFlashAttribute("cursor", cursor);
        Faces.navigate("colaborador-excluir.xhtml?faces-redirect=true");
    }

    public void selecionarEdicao(Colaborador cursor) {
        Faces.setFlashAttribute("cursor", cursor);
        Faces.navigate("colaborador-editar.xhtml?faces-redirect=true");
    }

    public void carregar() {
        colaborador = Faces.getFlashAttribute("cursor");
    }

    public void excluir() {
        try{
            colaboradorRepository.delete(colaborador);
            Messages.addFlashGlobalInfo("Registro removido com sucesso");
            Faces.navigate("colaborador-listar.xhtml?faces-redirect=true");
        } catch (DataIntegrityViolationException excecao) {
            excecao.printStackTrace();
            Messages.addFlashGlobalError("Registro possui registros vinculados");
        }
    }
}

