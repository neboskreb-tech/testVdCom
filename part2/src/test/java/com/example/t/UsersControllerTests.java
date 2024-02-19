package com.example.t;

import com.example.t.UsersController;
import com.example.t.UsersRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@WebMvcTest(UsersController.class)
public class UsersControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsersRepository entityRepository;

    @Test
    public void testIndexPage() throws Exception {
        when(entityRepository.findAll()).thenReturn(Collections.emptyList());
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("entities"));
    }

    @Test
    public void testAddPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/add"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("add"));
    }

    @Test
    public void testEditPage() throws Exception {
        Long id = 1L;
        Users entity = new Users();
        entity.setId(id);
        when(entityRepository.findById(id)).thenReturn(Optional.of(entity));
        mockMvc.perform(MockMvcRequestBuilders.get("/edit/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("edit"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("entity"))
                .andExpect(MockMvcResultMatchers.model().attribute("entity", entity));
    }

    @Test
    public void testAddEntity() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/add")
                        .param("midlname", "John")
                        .param("name", "Doe")
                        .param("surname", "Smith"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/add"));
        verify(entityRepository, times(1)).save(any());
    }

    @Test
    public void testUpdateEntity() throws Exception {
        Long id = 1L;
        Users entity = new Users();
        entity.setId(id);
        when(entityRepository.findById(id)).thenReturn(Optional.of(entity));
        mockMvc.perform(MockMvcRequestBuilders.post("/update")
                        .param("id", id.toString())
                        .param("midlname", "John")
                        .param("name", "Doe")
                        .param("surname", "Smith"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
        verify(entityRepository, times(1)).save(entity);
    }

    @Test
    public void testDeleteEntity() throws Exception {
        Long id = 1L;
        mockMvc.perform(MockMvcRequestBuilders.get("/delete/{id}", id))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
        verify(entityRepository, times(1)).deleteById(id);
    }

    @Test
    public void testUploadCSV() throws Exception {
        String csvData = "Сухов,Антон,Алексеевич";
        mockMvc.perform(MockMvcRequestBuilders.multipart("/upload")
                        .file("file", csvData.getBytes()))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
        verify(entityRepository, times(1)).save(any());
    }
}
