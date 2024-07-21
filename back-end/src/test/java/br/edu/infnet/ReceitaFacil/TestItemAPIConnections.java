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

import br.edu.infnet.ReceitaFacil.model.Item;
import br.edu.infnet.ReceitaFacil.model.Medida;
import br.edu.infnet.ReceitaFacil.model.Unidade;
import br.edu.infnet.ReceitaFacil.service.ItemService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class TestItemAPIConnections {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ItemService itemService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGet() throws Exception {
        Medida medida = new Medida(1L, "Medida 1", Unidade.TipoUnidade.MASSA);
        Item item = new Item(null, "Item 1", 0.0f, medida);
        itemService.add(item);
        mockMvc.perform(MockMvcRequestBuilders.get("/item")).andExpect(status().isOk());
    }

    @Test
    public void testPost() throws Exception {
        Medida medida = new Medida(1L, "Medida 1", Unidade.TipoUnidade.MASSA);
        Item item = new Item(null, "Item 1", 0.0f, medida);
        mockMvc.perform(MockMvcRequestBuilders
            .post("/item/1")
            .content(objectMapper.writeValueAsString(item))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());
    }

    @Test
    public void testDelete() throws Exception {
        Medida medida = new Medida(1L, "Medida 1", Unidade.TipoUnidade.MASSA);
        Item item = new Item(null, "Item 1", 0.0f, medida);
        Long id = itemService.add(item);
        mockMvc.perform(MockMvcRequestBuilders.delete("/item/" + id)).andDo(print()).andExpect(status().isAccepted());
    }
}