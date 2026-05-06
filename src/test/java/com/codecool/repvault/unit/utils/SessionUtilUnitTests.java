package com.codecool.repvault.unit.utils;


import com.codecool.repvault.application.DTOs.incoming.ExerciseRequestDTO;
import com.codecool.repvault.application.DTOs.incoming.SessionRequestDTO;
import com.codecool.repvault.application.DTOs.incoming.SetRequestDTO;
import com.codecool.repvault.domain.entities.Exercise;
import com.codecool.repvault.domain.entities.Session;
import com.codecool.repvault.domain.entities.Set;
import com.codecool.repvault.domain.entities.User;
import com.codecool.repvault.domain.entities.enums.ExerciseEnum;
import com.codecool.repvault.domain.entities.enums.SetEnum;
import com.codecool.repvault.domain.utils.SecurityUtil;
import com.codecool.repvault.domain.utils.SessionUtil;
import com.codecool.repvault.infrastructure.repositories.SessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SessionUtilUnitTests {

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private SecurityUtil securityUtil;

    @InjectMocks
    private SessionUtil sessionUtil;

    private User user;
    private User otherUser;

    private String sessionName;
    private String sessionDescription;
    private ExerciseEnum exerciseType;
    private String exerciseDescription;
    private SetEnum setType;
    private int setReps;
    private BigDecimal setWeight;

    private int setsPerExercise = 4;
    private int exercisesPerSession = 4;

    private Session session;

    @BeforeEach
    void setup() {
        user = new User(1L, "user", "password");
        otherUser = new User(2L, "user", "password");

        sessionName = "test-session";
        sessionDescription = "Testing session";
        exerciseType = ExerciseEnum.BARBELL_BENCH_PRESS;
        exerciseDescription = "Testing exercise";
        setType = SetEnum.NORMAL;
        setReps = 8;
        setWeight = BigDecimal.valueOf(50);
        setsPerExercise = 4;
        exercisesPerSession = 4;

        // Create session request dto
        SessionRequestDTO sessionRequestDTO = new SessionRequestDTO();
        sessionRequestDTO.setName(sessionName);
        sessionRequestDTO.setDescription(sessionDescription);

        // Create exercise request dto
        ExerciseRequestDTO exerciseRequestDTO = new ExerciseRequestDTO();
        exerciseRequestDTO.setType(exerciseType);
        exerciseRequestDTO.setDescription(exerciseDescription);

        // Create set request dto
        SetRequestDTO setRequestDTO = new SetRequestDTO();
        setRequestDTO.setType(setType);
        setRequestDTO.setReps(setReps);
        setRequestDTO.setWeight(setWeight);

        // Add sets to exercise
        ArrayList<SetRequestDTO> setRequestDTOs = new ArrayList<>();
        for (int i = 0; i < setsPerExercise; i++) { // For each set
            setRequestDTOs.add(setRequestDTO);
        }
        exerciseRequestDTO.setSets(setRequestDTOs);

        // Add exercises to session
        ArrayList<ExerciseRequestDTO> exerciseRequestDTOS = new ArrayList<>();
        for (int i = 0; i < exercisesPerSession; i++) { // For each set
            exerciseRequestDTOS.add(exerciseRequestDTO);
        }
        sessionRequestDTO.setExercises(exerciseRequestDTOS);

        when(securityUtil.getAuthenticatedUser()).thenReturn(user);
        session = sessionUtil.convertToEntity(sessionRequestDTO);
    }

    @Test
    void shouldCorrectlyConvertToEntity() {
        assertEquals(session.getUser(), user);
        assertEquals(session.getName(), sessionName);
        assertEquals(session.getDescription(), sessionDescription);

        for (Exercise exercise : session.getExercises()) {
            assertEquals(exercise.getDescription(), exerciseDescription);
            assertEquals(exercise.getType(), exerciseType);

            for (Set set : exercise.getSets()) {
                assertEquals(set.getType(), setType);
                assertEquals(set.getReps(), setReps);
                assertEquals(set.getWeight(), setWeight);
            }
        }
    }

    @Test
    void canViewSessionIfOwner() {
        assertTrue(sessionUtil.canViewSession(session));
    }

    @Test
    void canUpdateSessionIfOwner() {
        assertTrue(sessionUtil.canViewSession(session));
    }

    @Test
    void canDeleteSessionIfOwner() {
        assertTrue(sessionUtil.canViewSession(session));
    }

    @Test
    void canNotViewSessionIfNotOwner() {
        when(securityUtil.getAuthenticatedUser()).thenReturn(otherUser);
        assertFalse(sessionUtil.canViewSession(session));
    }

    @Test
    void canNotUpdateSessionIfNotOwner() {
        when(securityUtil.getAuthenticatedUser()).thenReturn(otherUser);
        assertFalse(sessionUtil.canViewSession(session));
    }

    @Test
    void canNotDeleteSessionIfNotOwner() {
        when(securityUtil.getAuthenticatedUser()).thenReturn(otherUser);
        assertFalse(sessionUtil.canViewSession(session));
    }
}