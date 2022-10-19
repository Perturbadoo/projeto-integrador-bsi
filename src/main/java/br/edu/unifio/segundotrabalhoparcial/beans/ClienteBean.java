package br.edu.unifio.segundotrabalhoparcial.beans;

import br.edu.unifio.segundotrabalhoparcial.entidades.Agendamento;
import br.edu.unifio.segundotrabalhoparcial.entidades.Cliente;
import br.edu.unifio.segundotrabalhoparcial.repositorios.AgendamentoRepository;
import br.edu.unifio.segundotrabalhoparcial.repositorios.ClienteRepository;
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
public class ClienteBean {
    private List<Cliente> clientes;
    private Cliente cliente;
    @Autowired
    private ClienteRepository clienteRepository;

    public void listar(){
        clientes = clienteRepository.findAll();
    }
    public void novo(){
        cliente = new Cliente();
    }

    public void salvar(){
        try { clienteRepository.save(cliente);
            Messages.addFlashGlobalInfo("Deu certo");
            Faces.navigate("cliente-cadastro.xhtml?faces-redirect=true");
            System.out.println("Deu certo");
        } catch (DataIntegrityViolationException excecao){
            excecao.printStackTrace();
        }
    }
    public void salvarEdicao(){
        try { clienteRepository.save(cliente);
            Messages.addFlashGlobalInfo("Deu certo");
            Faces.navigate("cliente-editar.xhtml?faces-redirect=true");
            System.out.println("Deu certo");
        } catch (DataIntegrityViolationException excecao){
            excecao.printStackTrace();
        }
    }
    public void selecionarExclusao(Cliente cursor) {
        Faces.setFlashAttribute("cursor", cursor);
        Faces.navigate("cliente-excluir.xhtml?faces-redirect=true");
    }

    public void selecionarEdicao(Cliente cursor) {
        Faces.setFlashAttribute("cursor", cursor);
        Faces.navigate("cliente-editar.xhtml?faces-redirect=true");
    }

    public void carregar() {
        cliente = Faces.getFlashAttribute("cursor");
    }

    public void excluir() {
        try{
            clienteRepository.delete(cliente);
            Messages.addFlashGlobalInfo("Registro removido com sucesso");
            Faces.navigate("cliente-listar.xhtml?faces-redirect=true");
        } catch (DataIntegrityViolationException excecao) {
            excecao.printStackTrace();
            Messages.addFlashGlobalError("Registro possui registros vinculados");
        }
    }

}

