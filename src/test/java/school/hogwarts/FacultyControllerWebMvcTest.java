package school.hogwarts;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;
import school.hogwarts.controller.FacultyController;
import school.hogwarts.model.Faculty;
import school.hogwarts.repository.FacultyRepository;
import school.hogwarts.repository.StudentRepository;
import school.hogwarts.service.FacultyService;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = FacultyController.class)
public class FacultyControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @SpyBean
    private FacultyService facultyService;

    @MockBean
    private FacultyRepository facultyRepository;

    @MockBean
    private StudentRepository studentRepository;

    private final Faker faker = new Faker();
    @Test
    public void updatePositiveTest() throws Exception {
        long id = 1;
        String newName = "Гриффиндор";
        String newColor = "красный";

        Faculty oldFaculty = new Faculty();
        oldFaculty.setId(id);
        oldFaculty.setName("Слизерин");
        oldFaculty.setColor("зеленый");

        Faculty newFaculty = new Faculty();
        newFaculty.setId(id);
        newFaculty.setName(newName);
        newFaculty.setColor(newColor);


        when(facultyRepository.findById(eq(id))).thenReturn(Optional.of(oldFaculty));
        when(facultyRepository.save(any())).thenReturn(newFaculty);

        mockMvc.perform(MockMvcRequestBuilders.put("/faculty/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding(StandardCharsets.UTF_8)
                .content(objectMapper.writeValueAsString(newFaculty))
        ).andExpect(result -> {
            MockHttpServletResponse response = result.getResponse();
            Faculty responseFaculty = objectMapper.readValue(response.getContentAsString(StandardCharsets.UTF_8), Faculty.class);
            assertThat(responseFaculty).usingRecursiveComparison().isEqualTo(newFaculty);
            assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        });

}


}
