package com.app.ProjectManagementSystem;
import com.app.ProjectManagementSystem.Model.Project;
import com.app.ProjectManagementSystem.Repository.ProjectRepository;
import com.app.ProjectManagementSystem.Service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectService projectService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllProjects() {
        List<Project> projects = new ArrayList<>();
        projects.add(new Project(1L, "Project 1", "Description 1", LocalDate.now(), LocalDate.now().plusDays(5)));
        projects.add(new Project(2L, "Project 2", "Description 2", LocalDate.now(), LocalDate.now().plusDays(10)));
        when(projectRepository.findAll()).thenReturn(projects);

        List<Project> result = projectService.getAllProjects();

        assertEquals(2, result.size());
    }

    @Test
    public void testGetProjectById() {
        Long projectId = 1L;
        Project project = new Project(projectId, "Project 1", "Description 1", LocalDate.now(), LocalDate.now().plusDays(5));
        when(projectRepository.findById(projectId)).thenReturn(Optional.of(project));

        Optional<Project> result = projectService.getProjectById(projectId);

        assertTrue(result.isPresent());
        assertEquals(project, result.get());
    }

    @Test
    public void testCreateProject() {
        Project project = new Project(null, "Project 1", "Description 1", LocalDate.now(), LocalDate.now().plusDays(5));
        when(projectRepository.save(project)).thenReturn(project);

        Project createdProject = projectService.createProject(project);

        assertNotNull(createdProject.getId());
        assertEquals(project.getName(), createdProject.getName());
    }

    @Test
    public void testUpdateProject() {
        Long projectId = 1L;
        Project existingProject = new Project(projectId, "Project 1", "Description 1", LocalDate.now(), LocalDate.now().plusDays(5));
        Project updatedProject = new Project(projectId, "Updated Project", "Updated Description", LocalDate.now(), LocalDate.now().plusDays(10));
        when(projectRepository.existsById(projectId)).thenReturn(true);
        when(projectRepository.save(updatedProject)).thenReturn(updatedProject);

        Project result = projectService.updateProject(projectId, updatedProject);

        assertEquals(updatedProject, result);
    }

    @Test
    public void testDeleteProject() {
        Long projectId = 1L;
        doNothing().when(projectRepository).deleteById(projectId);

        projectService.deleteProject(projectId);

        verify(projectRepository, times(1)).deleteById(projectId);
    }
}
