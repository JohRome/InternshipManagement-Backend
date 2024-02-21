package com.jromeo.internshipbackend.service;

import com.jromeo.internshipbackend.dto.InternshipDTO;
import com.jromeo.internshipbackend.entity.Internship;
import com.jromeo.internshipbackend.exception.InternshipNotFoundException;
import com.jromeo.internshipbackend.repository.InternshipRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class InternshipServiceTest {
    @InjectMocks
    private InternshipService internshipService;
    @Mock
    private InternshipRepository internshipRepository;
    @Mock
    private ModelMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_create_internship() {
        // Given
        InternshipDTO dto = new InternshipDTO(
                1,
                "Company",
                "Description",
                "Contact Person",
                true
        );
        Internship internship = new Internship(
                1,
                "Company",
                "Description",
                "Contact Person",
                true
        );
        when(mapper.map(dto, Internship.class)).thenReturn(internship);
        when(mapper.map(internship, InternshipDTO.class)).thenReturn(dto);
        when(internshipRepository.save(internship)).thenReturn(internship);

        // When
        InternshipDTO createdInternship = internshipService.createInternship(dto);

        // Then
        assertEquals(dto.getId(), createdInternship.getId());
    }

    @Test
    public void should_throw_internship_not_found_exception_when_internship_not_found() {
        // Given
        when(internshipRepository.findById(1))
                .thenReturn(java.util.Optional.empty());

        // When
        // Then
        assertThrows(InternshipNotFoundException.class,
                () -> internshipService.getInternship(1));
    }

}