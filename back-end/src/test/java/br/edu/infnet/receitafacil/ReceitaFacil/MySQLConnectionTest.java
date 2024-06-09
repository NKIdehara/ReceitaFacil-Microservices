package br.edu.infnet.receitafacil.ReceitaFacil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.edu.infnet.receitafacil.ReceitaFacil.controller.ReceitaController;
import br.edu.infnet.receitafacil.ReceitaFacil.model.Receita;
import br.edu.infnet.receitafacil.ReceitaFacil.service.ReceitaService;

@WebMvcTest(ReceitaController.class)
public class MySQLConnectionTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReceitaService receitaService;

    @Test
    public void testMySQLConnection(){
        String url = "jdbc:mysql://localhost:3306";
        String username = "admin";
        String password = "12345678";

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
        String username = "admin";
        String password = "12345678";

        Connection connection = null;
        Statement statement = null;
        ResultSet resultset = null;
        int count = 0;
        try {
            connection = DriverManager.getConnection(url, username, password);
                statement = connection.createStatement();
                statement.execute("DELETE FROM tbl_receita;");
                statement.execute("INSERT INTO tbl_receita (usuario, nome, preparo, ingredientes, data_receita, figura) VALUES ('Usuario', 'Receita', 'Preparo', 'Ingredientes', '2024-01-01', 'Figura');");
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
        Receita receita = new Receita();
        receita.setUsuario("Usuario");
        receita.setNome("Receita");
        receita.setPreparo(" Preparo");
        receita.setIngredientes(" Ingredientes");
        receita.setData_receita(new Date(2024, 01, 01));
        receita.setFigura("Figura");
        ObjectMapper json = new ObjectMapper();
        this.mockMvc.perform(post("/receita").contentType(MediaType.APPLICATION_JSON).content(json.writeValueAsString(receita))).andExpect(status().isOk());
    }

    @SuppressWarnings("deprecation")
    @Test
    public void testDeleteReceita() throws Exception {
        Receita receita = new Receita();
        receita.setUsuario("Usuario");
        receita.setNome("Receita");
        receita.setPreparo(" Preparo");
        receita.setIngredientes(" Ingredientes");
        receita.setData_receita(new Date(2024, 01, 01));
        receita.setFigura("Figura");
        ObjectMapper json = new ObjectMapper();        
        this.mockMvc.perform(post("/receita").contentType(MediaType.APPLICATION_JSON).content(json.writeValueAsString(receita))).andExpect(status().isOk());
        this.mockMvc.perform(delete("/receita/{id}", "0").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

    }
}
