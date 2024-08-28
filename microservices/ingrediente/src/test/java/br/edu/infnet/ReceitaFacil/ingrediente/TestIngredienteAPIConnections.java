package br.edu.infnet.ReceitaFacil.ingrediente;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.edu.infnet.ReceitaFacil.ingrediente.model.IngredienteResponse;
import br.edu.infnet.ReceitaFacil.ingrediente.model.ItemResponse;
import br.edu.infnet.ReceitaFacil.ingrediente.model.Medida;
import br.edu.infnet.ReceitaFacil.ingrediente.model.Unidade;
import br.edu.infnet.ReceitaFacil.ingrediente.service.IngredienteService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class TestIngredienteAPIConnections {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IngredienteService ingredienteService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGet() throws Exception {
        Medida medida = new Medida(1L, "Medida 1", Unidade.MASSA);
        ItemResponse item = ItemResponse.builder()
                                .id(1L)
                                .descricao("Item 1")
                                .preco(2.50f)
                                .medida(medida)
                                .build();
        IngredienteResponse ingrediente = IngredienteResponse.builder()
                                            .id(1L)
                                            .quantidade(500.0f)
                                            .item(item)
                                            .medida(medida)
                                            .custo(medida.convert(item.getMedida()) * 500.0f * item.getPreco())
                                            .build();
        ingredienteService.addByReceitaId(1L, ingrediente);
        mockMvc.perform(MockMvcRequestBuilders.get("/ingrediente")).andExpect(status().isOk());
    }

    @Test
    public void testPost() throws Exception {
        Medida medida = new Medida(1L, "Medida 1", Unidade.MASSA);
        ItemResponse item = ItemResponse.builder()
                                .id(1L)
                                .descricao("Item 1")
                                .preco(2.50f)
                                .medida(medida)
                                .build();
        IngredienteResponse ingrediente = IngredienteResponse.builder()
                                            .id(1L)
                                            .quantidade(500.0f)
                                            .item(item)
                                            .medida(medida)
                                            .custo(medida.convert(item.getMedida()) * 500.0f * item.getPreco())
                                            .build();
        ingredienteService.addByReceitaId(1L, ingrediente);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/ingrediente/receita/1")
                .content(objectMapper.writeValueAsString(ingrediente))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void testPut() throws Exception {
        Medida medida = new Medida(1L, "Medida 1", Unidade.MASSA);
        ItemResponse item = ItemResponse.builder()
                                .id(1L)
                                .descricao("Item 1")
                                .preco(2.50f)
                                .medida(medida)
                                .build();
        IngredienteResponse ingrediente1 = IngredienteResponse.builder()
                                            .id(1L)
                                            .quantidade(500.0f)
                                            .item(item)
                                            .medida(medida)
                                            .custo(medida.convert(item.getMedida()) * 500.0f * item.getPreco())
                                            .build();

        IngredienteResponse ingrediente2 = IngredienteResponse.builder()
                                            .id(1L)
                                            .quantidade(500.0f)
                                            .item(item)
                                            .medida(medida)
                                            .custo(medida.convert(item.getMedida()) * 500.0f * item.getPreco())
                                            .build();
        mockMvc.perform(MockMvcRequestBuilders
                .post("/ingrediente/1/receita/1")
                .content(objectMapper.writeValueAsString(ingrediente1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        mockMvc.perform(MockMvcRequestBuilders
                .put("/ingrediente/1/receita/1")
                .content(objectMapper.writeValueAsString(ingrediente2))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }

    @Test
    public void testDelete() throws Exception {
        Medida medida = new Medida(1L, "Medida 1", Unidade.MASSA);
        ItemResponse item = ItemResponse.builder()
                                .id(1L)
                                .descricao("Item 1")
                                .preco(2.50f)
                                .medida(medida)
                                .build();
        IngredienteResponse ingrediente = IngredienteResponse.builder()
                                            .id(1L)
                                            .quantidade(500.0f)
                                            .item(item)
                                            .medida(medida)
                                            .custo(medida.convert(item.getMedida()) * 500.0f * item.getPreco())
                                            .build();
        ingredienteService.addByReceitaId(1L, ingrediente);
        mockMvc.perform(MockMvcRequestBuilders.delete("/ingrediente/1")).andDo(print()).andExpect(status().isAccepted());
    }
}