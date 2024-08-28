package br.edu.infnet.ReceitaFacil.receita;

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

import br.edu.infnet.ReceitaFacil.receita.model.Receita;
import br.edu.infnet.ReceitaFacil.receita.service.ReceitaService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class TestReceitaAPIConnections {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ReceitaService receitaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGet() throws Exception {
        Receita receita = new Receita(1L, "Usuário 1", "Receita 1", "Preparo 1", "Figura 1", null, "Usuario", null, "Usuario");
        receitaService.add(receita);
        mockMvc.perform(MockMvcRequestBuilders.get("/receita")).andExpect(status().isOk());
    }

    @Test
    public void testPost() throws Exception {
        Receita receita = new Receita(1L, "Usuário 1", "Receita 1", "Preparo 1", "Figura 1", null, "Usuario", null, "Usuario");
        mockMvc.perform(MockMvcRequestBuilders
                .post("/receita")
                .content(objectMapper.writeValueAsString(receita))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void testPut() throws Exception {
        Receita receita1 = new Receita(1L, "Usuário 1", "Receita 1", "Preparo 1", "Figura 1", null, "Usuario", null, "Usuario");
        Receita receita2 = new Receita(1L, "Usuário 2", "Receita 2", "Preparo 2", "Figura 2", null, "Usuario", null, "Usuario");
        Long id = receitaService.add(receita1);
        mockMvc.perform(MockMvcRequestBuilders
                .put("/receita/" + id)
                .content(objectMapper.writeValueAsString(receita2))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }

    @Test
    public void testDelete() throws Exception {
        Receita receita = new Receita(1L, "Usuário 1", "Receita 1", "Preparo 1", "Figura 1", null, "Usuario", null, "Usuario");
        Long id = receitaService.add(receita);
        mockMvc.perform(MockMvcRequestBuilders.delete("/receita/" + id)).andDo(print()).andExpect(status().isAccepted());
    }
}