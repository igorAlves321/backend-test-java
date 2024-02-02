package desafioestacionamento.desafioestacionamento;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class VeiculoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Long estabelecimentoId;

    @BeforeEach
    public void setup() throws Exception {
        String novoEstabelecimentoJson = "{\"nome\": \"Novo Estabelecimento\", \"cnpj\": \"12345678901234\", \"endereco\": \"Rua Exemplo, 123\", \"telefone\": \"11999999999\", \"qtdVagasMotos\": 10, \"qtdVagasCarros\": 20}";

        MvcResult result = mockMvc.perform(post("/estabelecimentos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(novoEstabelecimentoJson))
                .andExpect(status().isCreated())
                .andReturn();

        // Extrair o ID do estabelecimento da resposta
        String response = result.getResponse().getContentAsString();
        Map<String, Object> responseMap = objectMapper.readValue(response, Map.class);
        estabelecimentoId = Long.valueOf(responseMap.get("id").toString());
    }

    @Test
    public void deveCriarVeiculo() throws Exception {
        String novoVeiculoJson = String.format("{\"marca\": \"Marca\", \"modelo\": \"Modelo\", \"cor\": \"Cor\", \"placa\": \"PLC123\", \"tipo\": \"CARRO\", \"estabelecimento_id\": %d}", estabelecimentoId);

        mockMvc.perform(post("/veiculos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(novoVeiculoJson))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("cadastrado com sucesso")));
    }

    // Aqui você pode incluir outros testes para os endpoints de veículos
    // seguindo a mesma lógica de criação, atualização, exclusão, etc.
}
