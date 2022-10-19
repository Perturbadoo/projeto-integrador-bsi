package br.edu.unifio.segundotrabalhoparcial.beans;

import br.edu.unifio.segundotrabalhoparcial.entidades.Colaborador;
import br.edu.unifio.segundotrabalhoparcial.entidades.Veiculo;
import br.edu.unifio.segundotrabalhoparcial.repositorios.ColaboradorRepository;
import br.edu.unifio.segundotrabalhoparcial.repositorios.VeiculoRepository;
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
public class VeiculoBean {
    private List<Veiculo> veiculos;
    private Veiculo veiculo;
    @Autowired
    private VeiculoRepository veiculoRepository;

    public void listar(){
        veiculos = veiculoRepository.findAll();
    }
    public void novo(){
        veiculo = new Veiculo();
    }

    public void salvar(){
        try { veiculoRepository.save(veiculo);
            Messages.addFlashGlobalInfo("Deu certo");
            Faces.navigate("veiculo-cadastro.xhtml?faces-redirect=true");
            System.out.println("Deu certo");
        } catch (DataIntegrityViolationException excecao){
            excecao.printStackTrace();
        }
    }
    public void salvarEdicao(){
        try {veiculoRepository.save(veiculo);
            Messages.addFlashGlobalInfo("Deu certo");
            Faces.navigate("veiculo-editar.xhtml?faces-redirect=true");
            System.out.println("Deu certo");
        } catch (DataIntegrityViolationException excecao){
            excecao.printStackTrace();
        }
    }
    public void selecionarExclusao(Veiculo cursor) {
        Faces.setFlashAttribute("cursor", cursor);
        Faces.navigate("veiculo-excluir.xhtml?faces-redirect=true");
    }

    public void selecionarEdicao(Veiculo cursor) {
        Faces.setFlashAttribute("cursor", cursor);
        Faces.navigate("veiculo-editar.xhtml?faces-redirect=true");
    }

    public void carregar() {
        veiculo = Faces.getFlashAttribute("cursor");
    }

    public void excluir() {
        try{
            veiculoRepository.delete(veiculo);
            Messages.addFlashGlobalInfo("Registro removido com sucesso");
            Faces.navigate("veiculo-listar.xhtml?faces-redirect=true");
        } catch (DataIntegrityViolationException excecao) {
            excecao.printStackTrace();
            Messages.addFlashGlobalError("Registro possui registros vinculados");
        }
    }
}

