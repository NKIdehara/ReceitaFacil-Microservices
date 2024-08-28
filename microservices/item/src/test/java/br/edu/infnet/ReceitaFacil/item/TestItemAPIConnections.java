package br.edu.infnet.ReceitaFacil.item;

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

import br.edu.infnet.ReceitaFacil.item.model.ItemResponse;
import br.edu.infnet.ReceitaFacil.item.model.Medida;
import br.edu.infnet.ReceitaFacil.item.model.Unidade;
import br.edu.infnet.ReceitaFacil.item.service.ItemService;

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
        Medida medida = new Medida(1L, "Medida 1", Unidade.MASSA);
        ItemResponse item = ItemResponse.builder()
                                .id(1L)
                                .descricao("Item 1")
                                .preco(2.50f)
                                .medida(medida)
                                .build();
        itemService.add(item);

        mockMvc.perform(MockMvcRequestBuilders.get("/item")).andExpect(status().isOk());
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
        mockMvc.perform(MockMvcRequestBuilders
            .post("/item/")
            .content(objectMapper.writeValueAsString(item))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());
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
        Long id = itemService.add(item);
        mockMvc.perform(MockMvcRequestBuilders.delete("/item/" + id)).andDo(print()).andExpect(status().isAccepted());
    }
}