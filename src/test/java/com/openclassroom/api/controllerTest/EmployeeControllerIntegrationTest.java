package com.openclassroom.api.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.openclassroom.api.controller.EmployeeController;
import com.openclassroom.api.model.Employee;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerIntegrationTest {

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    MockMvc mockMvc;

    @Test
    @Sql({"/data.sql"})
    public void getEmployeeTest() throws Exception {
        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is("Laurent")))
                .andExpect(jsonPath("$[1].firstName", is("Sophie")))
                .andExpect(jsonPath("$[2].firstName", is("Agathe")));
    }

//    @Test
//    @Sql({"/data.sql"})
//    public void getAnEmployeeTest() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders
//                .get("/employee/{id}")
//                        .param("id",))
//                .andExpect(jsonPath("$[0].firstName", is("Laurent")));
//    }

    @Test
    @Sql({"/data.sql"})
    public void saveAnEmployeeTest() throws Exception {
        Employee newEmloyee = Employee.builder().firstName("tata").lastName("toto").mail("tatatoto@gmail.com").password("tata").build();

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post("/employee")
                .content(mapper.writeValueAsString(newEmloyee))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();

        assertThat(result, Matchers.notNullValue());
        //assertThat(result.getResponse().getContentAsString(), not(isEmptyOrNullString()));
       // assertThat(UUID.fromString(result.getResponse().getContentAsString().replaceAll("\\\"", "")), Matchers.notNullValue());
    }
}
