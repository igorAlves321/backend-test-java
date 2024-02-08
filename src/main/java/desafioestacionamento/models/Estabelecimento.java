package desafioestacionamento.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Estabelecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Nome não pode estar vazio")
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
    private String nome;

    @NotBlank(message = "CNPJ não pode estar vazio")
    @Pattern(regexp = "^[0-9]{14}$", message = "CNPJ deve ter 14 dígitos numéricos")
    private String cnpj;

    @NotBlank(message = "Endereço não pode estar vazio")
    private String endereco;

    @NotBlank(message = "Telefone não pode estar vazio")
    private String telefone;

    @Min(value = 0, message = "Quantidade de vagas para motos não pode ser negativa")
    private int qtdVagasMotos;

    @Min(value = 0, message = "Quantidade de vagas para carros não pode ser negativa")
    private int qtdVagasCarros;

    @OneToMany(mappedBy = "estabelecimento", cascade = CascadeType.ALL)
    private List<Veiculo> veiculos;



    public Estabelecimento() {
    }

    public Estabelecimento(String nome, String cnpj, String endereco, String telefone, int qtdVagasMotos, int qtdVagasCarros) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.telefone = telefone;
        this.qtdVagasMotos = qtdVagasMotos;
        this.qtdVagasCarros = qtdVagasCarros;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getQtdVagasMotos() {
        return qtdVagasMotos;
    }

    public void setQtdVagasMotos(int qtdVagasMotos) {
        this.qtdVagasMotos = qtdVagasMotos;
    }

    public int getQtdVagasCarros() {
        return qtdVagasCarros;
    }

    public void setQtdVagasCarros(int qtdVagasCarros) {
        this.qtdVagasCarros = qtdVagasCarros;
    }

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }
}
