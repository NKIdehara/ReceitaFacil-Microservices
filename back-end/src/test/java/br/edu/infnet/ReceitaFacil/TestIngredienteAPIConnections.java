package br.edu.infnet.ReceitaFacil;

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

import br.edu.infnet.ReceitaFacil.model.Ingrediente;
import br.edu.infnet.ReceitaFacil.model.Item;
import br.edu.infnet.ReceitaFacil.model.Medida;
import br.edu.infnet.ReceitaFacil.model.Unidade;
import br.edu.infnet.ReceitaFacil.service.IngredienteService;

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
        Ingrediente ingrediente = new Ingrediente(null, null, 0.0f, null, null);
        ingredienteService.add(ingrediente);
        mockMvc.perform(MockMvcRequestBuilders.get("/ingrediente")).andExpect(status().isOk());
    }

    @Test
    public void testPost() throws Exception {
        Medida medida = new Medida(1L, "Medida 1", Unidade.TipoUnidade.MASSA);
        Item item = new Item(null, "Item 1", 0.0f, medida);
        Ingrediente ingrediente = new Ingrediente(null, item, 0.0f, medida, null);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/ingrediente/1/1/1")
                .content(objectMapper.writeValueAsString(ingrediente))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void testPut() throws Exception {
        Medida medida = new Medida(null, "Medida 1", Unidade.TipoUnidade.MASSA);
        Item item = new Item(null, "Item 1", 0.0f, medida);
        Ingrediente ingrediente1 = new Ingrediente(null, item, 0.0f, medida, null);
        Ingrediente ingrediente2 = new Ingrediente(null, item, 0.0f, medida, null);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/ingrediente/1/1/1")
                .content(objectMapper.writeValueAsString(ingrediente1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        mockMvc.perform(MockMvcRequestBuilders
                .put("/ingrediente/1/1/1/1")
                .content(objectMapper.writeValueAsString(ingrediente2))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }

    @Test
    public void testDelete() throws Exception {
        Ingrediente ingrediente = new Ingrediente(null, null, 0.0f, null, null);
        Long id = ingredienteService.add(ingrediente);
        mockMvc.perform(MockMvcRequestBuilders.delete("/ingrediente/" + id)).andDo(print()).andExpect(status().isAccepted());
    }
}