package desafioestacionamento.desafioestacionamento;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class EstabelecimentoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void deveCriarEstabelecimento() throws Exception {
        String novoEstabelecimentoJson = "{\"nome\": \"Novo Estabelecimento\", \"cnpj\": \"12345678901234\", \"endereco\": \"Rua Exemplo, 123\", \"telefone\": \"11999999999\", \"qtdVagasMotos\": 10, \"qtdVagasCarros\": 20}";

        mockMvc.perform(post("/estabelecimentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(novoEstabelecimentoJson))
                .andExpect(status().isCreated()) // Verifica se o status é 201 Created
                .andExpect(jsonPath("$.nome", is("Novo Estabelecimento"))) // Verifica se os detalhes do estabelecimento estão corretos
                .andExpect(jsonPath("$.cnpj", is("12345678901234")))
        // Adicione mais verificações conforme necessário
        ;
    }

}
