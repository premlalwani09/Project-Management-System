package com.app.ProjectManagementSystem;

import com.app.ProjectManagementSystem.Model.Project;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProjectControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateProject() throws Exception {
        Project project = new Project(null, "New Project", "Description", LocalDate.now(), LocalDate.now().plusDays(7));
        mockMvc.perform(post("/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(project)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value(project.getName()))
                .andExpect(jsonPath("$.description").value(project.getDescription()));
    }

    @Test
    public void testGetAllProjects() throws Exception {
        mockMvc.perform(get("/projects"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testGetProjectById() throws Exception {
        mockMvc.perform(get("/projects/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testUpdateProject() throws Exception {
        Project updatedProject = new Project(1L, "Updated Project", "Updated Description", LocalDate.now(), LocalDate.now().plusDays(10));
        mockMvc.perform(put("/projects/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedProject)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(updatedProject.getName()))
                .andExpect(jsonPath("$.description").value(updatedProject.getDescription()));
    }

    @Test
    public void testDeleteProject() throws Exception {
        mockMvc.perform(delete("/projects/{id}", 1L))
                .andExpect(status().isNoContent());
    }
}

