package com.openclassroom.api.controllerTest;

import com.openclassroom.api.controller.EmployeeController;
import com.openclassroom.api.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = EmployeeController.class)
public class EmployeeControllerUnitTest {
    @MockBean
    EmployeeService es;  //je mock le bean service

    @Autowired
    private MockMvc mockMvc;  //je l'instancie pour pouvoir utiliser la methode perform

    @Test
    public void testGetEmployee() throws Exception {
        mockMvc.perform(get("/employees")).andExpect(status().isOk());
    }

}
