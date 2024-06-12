package br.edu.infnet.receitafacil.ReceitaFacil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.wavefront.WavefrontProperties.Application;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.edu.infnet.receitafacil.ReceitaFacil.controller.ReceitaController;
import br.edu.infnet.receitafacil.ReceitaFacil.model.Ingrediente;
import br.edu.infnet.receitafacil.ReceitaFacil.model.Receita;
import br.edu.infnet.receitafacil.ReceitaFacil.service.ReceitaService;

// @SpringBootTest(classes = Application.class)
@WebMvcTest(ReceitaController.class)
@ExtendWith(MockitoExtension.class)
// @AuditorAware
// @DataJpaTest
public class MySQLConnectionTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReceitaService receitaService;
/* 
    @Test
    public void testMySQLConnection(){
        String url = "jdbc:mysql://localhost:3306";
        String username = "root";
        String password = "abcde";

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Assertions.assertNotNull(connection);
    }

    @Test
    public void testMySQLDatabase(){
        String url = "jdbc:mysql://localhost:3306/receitafacil?createDatabaseIfNotExist=true";
        String username = "root";
        String password = "abcde";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultset = null;
        int count = 0;
        try {
            connection = DriverManager.getConnection(url, username, password);
                statement = connection.createStatement();
                statement.execute("DELETE FROM tbl_receita;");
                statement.execute("INSERT INTO tbl_receita (usuario, nome, preparo, data_receita, figura) VALUES ('Usuario', 'Receita', 'Preparo', '2024-01-01', 'Figura');");
                resultset = statement.executeQuery("SELECT COUNT(*) as count FROM tbl_receita;");
                resultset.next();
                count = resultset.getInt("count");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(1, count);
    }

    @Test
    public void testGetReceita() throws Exception {
        this.mockMvc.perform(get("/receita")).andDo(print()).andExpect(status().isOk());
    }

    @SuppressWarnings("deprecation")
    @Test
    public void testAddReceita() throws Exception {
        Ingrediente ingrediente1 = new Ingrediente("Ingrediente 1", 1.0f, "kg");
        Ingrediente ingrediente2 = new Ingrediente("Ingrediente 2", 200.0f, "ml");
        List<Ingrediente> ingredientes = new ArrayList<>();
        ingredientes.add(ingrediente1);
        ingredientes.add(ingrediente2);
        Receita receita = new Receita("Usuario", "Receita", " Preparo", ingredientes, new Date(2024, 01, 01), "Figura");
        ObjectMapper json = new ObjectMapper();
        this.mockMvc.perform(post("/receita").contentType(MediaType.APPLICATION_JSON).content(json.writeValueAsString(receita))).andExpect(status().isOk());
    }

    @SuppressWarnings("deprecation")
    @Test
    public void testDeleteReceita() throws Exception {
        Ingrediente ingrediente1 = new Ingrediente("Ingrediente 1", 1.0f, "kg");
        Ingrediente ingrediente2 = new Ingrediente("Ingrediente 2", 200.0f, "ml");
        List<Ingrediente> ingredientes = new ArrayList<>();
        ingredientes.add(ingrediente1);
        ingredientes.add(ingrediente2);
        Receita receita = new Receita("Usuario", "Receita", " Preparo", ingredientes, new Date(2024, 01, 01), "Figura");
        ObjectMapper json = new ObjectMapper();
        this.mockMvc.perform(post("/receita").contentType(MediaType.APPLICATION_JSON).content(json.writeValueAsString(receita))).andExpect(status().isOk());
        this.mockMvc.perform(delete("/receita/{id}", "0").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    } */
}
