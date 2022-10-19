package br.edu.unifio.segundotrabalhoparcial.beans;

import br.edu.unifio.segundotrabalhoparcial.entidades.Agendamento;
import br.edu.unifio.segundotrabalhoparcial.repositorios.AgendamentoRepository;
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
public class AgendamentoBean {
    private List<Agendamento> agendamentos;
    private Agendamento agendamento;
    @Autowired
    private AgendamentoRepository agendamentoRepository;

    public void listar(){
        agendamentos = agendamentoRepository.findAll();
    }
    public void novo(){
        agendamento = new Agendamento();
    }

    public void salvar(){
        try { agendamentoRepository.save(agendamento);
            Messages.addFlashGlobalInfo("Deu certo");
            Faces.navigate("agendamento-cadastro.xhtml?faces-redirect=true");
            System.out.println("Deu certo");
        } catch (DataIntegrityViolationException excecao){
            excecao.printStackTrace();
        }
    }

    public void salvarEdicao(){
        try { agendamentoRepository.save(agendamento);
            Messages.addFlashGlobalInfo("Deu certo");
            Faces.navigate("agendamento-editar.xhtml?faces-redirect=true");
            System.out.println("Deu certo");
        } catch (DataIntegrityViolationException excecao){
            excecao.printStackTrace();
        }
    }


    public void selecionarExclusao(Agendamento cursor) {
        Faces.setFlashAttribute("cursor", cursor);
        Faces.navigate("agendamento-excluir.xhtml?faces-redirect=true");
    }

    public void selecionarEdicao(Agendamento cursor) {
        Faces.setFlashAttribute("cursor", cursor);
        Faces.navigate("agendamento-editar.xhtml?faces-redirect=true");
    }

    public void carregar() {
        agendamento = Faces.getFlashAttribute("cursor");
    }

    public void excluir() {
        try{
            agendamentoRepository.delete(agendamento);
            Messages.addFlashGlobalInfo("Registro removido com sucesso");
            Faces.navigate("agendamento-listar.xhtml?faces-redirect=true");
        } catch (DataIntegrityViolationException excecao) {
            excecao.printStackTrace();
            Messages.addFlashGlobalError("Registro possui registros vinculados");
        }
    }
}

