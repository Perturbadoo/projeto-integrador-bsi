package br.edu.unifio.segundotrabalhoparcial.beans;

import br.edu.unifio.segundotrabalhoparcial.entidades.Colaborador;
import br.edu.unifio.segundotrabalhoparcial.entidades.Pedido;
import br.edu.unifio.segundotrabalhoparcial.repositorios.ColaboradorRepository;
import br.edu.unifio.segundotrabalhoparcial.repositorios.PedidoRepository;
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
public class PedidoBean {
    private List<Pedido> pedidos;
    private Pedido pedido;
    @Autowired
    private PedidoRepository pedidoRepository;

    public void listar(){
        pedidos = pedidoRepository.findAll();
    }
    public void novo(){
        pedido = new Pedido();
    }

    public void salvar(){
        try { pedidoRepository.save(pedido);
            Messages.addFlashGlobalInfo("Deu certo");
            Faces.navigate("pedido-cadastro.xhtml?faces-redirect=true");
            System.out.println("Deu certo");
        } catch (DataIntegrityViolationException excecao){
            excecao.printStackTrace();
        }
    }
    public void salvarEdicao(){
        try {pedidoRepository.save(pedido);
            Messages.addFlashGlobalInfo("Deu certo");
            Faces.navigate("pedido-editar.xhtml?faces-redirect=true");
            System.out.println("Deu certo");
        } catch (DataIntegrityViolationException excecao){
            excecao.printStackTrace();
        }
    }
    public void selecionarExclusao(Pedido cursor) {
        Faces.setFlashAttribute("cursor", cursor);
        Faces.navigate("pedido-excluir.xhtml?faces-redirect=true");
    }

    public void selecionarEdicao(Pedido cursor) {
        Faces.setFlashAttribute("cursor", cursor);
        Faces.navigate("pedido-editar.xhtml?faces-redirect=true");
    }

    public void carregar() {
        pedido = Faces.getFlashAttribute("cursor");
    }

    public void excluir() {
        try{
            pedidoRepository.delete(pedido);
            Messages.addFlashGlobalInfo("Registro removido com sucesso");
            Faces.navigate("pedido-listar.xhtml?faces-redirect=true");
        } catch (DataIntegrityViolationException excecao) {
            excecao.printStackTrace();
            Messages.addFlashGlobalError("Registro possui registros vinculados");
        }
    }
}

