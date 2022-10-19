package br.edu.unifio.segundotrabalhoparcial.beans;

import br.edu.unifio.segundotrabalhoparcial.entidades.Colaborador;
import br.edu.unifio.segundotrabalhoparcial.entidades.Produto;
import br.edu.unifio.segundotrabalhoparcial.repositorios.ColaboradorRepository;
import br.edu.unifio.segundotrabalhoparcial.repositorios.ProdutoRepository;
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
public class ProdutoBean {
    private List<Produto> produtos;
    private Produto produto;
    @Autowired
    private ProdutoRepository produtoRepository;

    public void listar(){
        produtos = produtoRepository.findAll();
    }
    public void novo(){
        produto = new Produto();
    }

    public void salvar(){
        try { produtoRepository.save(produto);
            Messages.addFlashGlobalInfo("Deu certo");
            Faces.navigate("produto-cadastro.xhtml?faces-redirect=true");
            System.out.println("Deu certo");
        } catch (DataIntegrityViolationException excecao){
            excecao.printStackTrace();
        }
    }
    public void salvarEdicao(){
        try {produtoRepository.save(produto);
            Messages.addFlashGlobalInfo("Deu certo");
            Faces.navigate("produto-editar.xhtml?faces-redirect=true");
            System.out.println("Deu certo");
        } catch (DataIntegrityViolationException excecao){
            excecao.printStackTrace();
        }
    }
    public void selecionarExclusao(Produto cursor) {
        Faces.setFlashAttribute("cursor", cursor);
        Faces.navigate("produto-excluir.xhtml?faces-redirect=true");
    }

    public void selecionarEdicao(Produto cursor) {
        Faces.setFlashAttribute("cursor", cursor);
        Faces.navigate("produto-editar.xhtml?faces-redirect=true");
    }

    public void carregar() {
        produto = Faces.getFlashAttribute("cursor");
    }

    public void excluir() {
        try{
            produtoRepository.delete(produto);
            Messages.addFlashGlobalInfo("Registro removido com sucesso");
            Faces.navigate("produto-listar.xhtml?faces-redirect=true");
        } catch (DataIntegrityViolationException excecao) {
            excecao.printStackTrace();
            Messages.addFlashGlobalError("Registro possui registros vinculados");
        }
    }
}

