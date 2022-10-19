package br.edu.unifio.segundotrabalhoparcial.beans;

import br.edu.unifio.segundotrabalhoparcial.entidades.Colaborador;
import br.edu.unifio.segundotrabalhoparcial.entidades.Vendedor;
import br.edu.unifio.segundotrabalhoparcial.repositorios.ColaboradorRepository;
import br.edu.unifio.segundotrabalhoparcial.repositorios.VeiculoRepository;
import br.edu.unifio.segundotrabalhoparcial.repositorios.VendedorRepository;
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
public class VendedorBean {
    private List<Vendedor> vendedors;
    private Vendedor vendedor;
    @Autowired
    private VendedorRepository vendedorRepository;

    public void listar(){
        vendedors = vendedorRepository.findAll();
    }
    public void novo(){
        vendedor = new Vendedor();
    }

    public void salvar(){
        try { vendedorRepository.save(vendedor);
            Messages.addFlashGlobalInfo("Deu certo");
            Faces.navigate("vendedor-cadastro.xhtml?faces-redirect=true");
            System.out.println("Deu certo");
        } catch (DataIntegrityViolationException excecao){
            excecao.printStackTrace();
        }
    }
    public void salvarEdicao(){
        try {vendedorRepository.save(vendedor);
            Messages.addFlashGlobalInfo("Deu certo");
            Faces.navigate("vendedor-editar.xhtml?faces-redirect=true");
            System.out.println("Deu certo");
        } catch (DataIntegrityViolationException excecao){
            excecao.printStackTrace();
        }
    }
    public void selecionarExclusao(Vendedor cursor) {
        Faces.setFlashAttribute("cursor", cursor);
        Faces.navigate("vendedor-excluir.xhtml?faces-redirect=true");
    }

    public void selecionarEdicao(Vendedor cursor) {
        Faces.setFlashAttribute("cursor", cursor);
        Faces.navigate("vendedor-editar.xhtml?faces-redirect=true");
    }

    public void carregar() {
        vendedor = Faces.getFlashAttribute("cursor");
    }

    public void excluir() {
        try{
            vendedorRepository.delete(vendedor);
            Messages.addFlashGlobalInfo("Registro removido com sucesso");
            Faces.navigate("vendedor-listar.xhtml?faces-redirect=true");
        } catch (DataIntegrityViolationException excecao) {
            excecao.printStackTrace();
            Messages.addFlashGlobalError("Registro possui registros vinculados");
        }
    }
}

