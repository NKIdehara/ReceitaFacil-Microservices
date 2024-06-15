package br.edu.infnet.ReceitaFacil;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.edu.infnet.ReceitaFacil.controller.ReceitaController;
import br.edu.infnet.ReceitaFacil.model.Receita;
import br.edu.infnet.ReceitaFacil.service.ReceitaService;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReceitaController.class)
public class TestConnections {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReceitaService receitaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/receita")).andExpect(status().isOk());
    }

    @Test
    public void testPost() throws Exception {
        Receita receita = new Receita("Usuário 1", "Receita 1", "Preparo 1", null, "Figura 1");

        mockMvc.perform(MockMvcRequestBuilders
                .post("/receita")
                .content(objectMapper.writeValueAsString(receita))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void testPut() throws Exception {
        Receita receita1 = new Receita("Usuário 1", "Receita 1", "Preparo 1", null, "Figura 1");
        Receita receita2 = new Receita("Usuário 2", "Receita 2", "Preparo 2", null, "Figura 2");
        Mockito.when(receitaService.update(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/receita")
                .content(objectMapper.writeValueAsString(receita1))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders
                .put("/receita/1")
                .content(objectMapper.writeValueAsString(receita2))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }

    @Test
    public void testDelete() throws Exception {
        Receita receita = new Receita("Usuário 1", "Receita 1", "Preparo 1", null, "Figura 1");
        Mockito.when(receitaService.delete(ArgumentMatchers.any())).thenReturn(true);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/receita")
                .content(objectMapper.writeValueAsString(receita))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.delete("/receita/1")).andDo(print()).andExpect(status().isAccepted());
    }
}