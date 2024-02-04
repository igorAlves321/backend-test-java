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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

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

        String response = result.getResponse().getContentAsString();
        Map<String, Object> responseMap = objectMapper.readValue(response, Map.class);
        estabelecimentoId = Long.valueOf(responseMap.get("id").toString());
    }

    @Test
    public void deveCriarVeiculo() throws Exception {
        String novoVeiculoJson = String.format("{\"marca\": \"Marca\", \"modelo\": \"Modelo\", \"cor\": \"Cor\", \"placa\": \"ABC1D23\", \"tipo\": \"CARRO\", \"estabelecimento\": {\"id\": %d}}", estabelecimentoId);

        mockMvc.perform(post("/veiculos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(novoVeiculoJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message", is("Veículo cadastrado com sucesso")))
                .andExpect(jsonPath("$.veiculo.marca", is("Marca")));
    }
}
