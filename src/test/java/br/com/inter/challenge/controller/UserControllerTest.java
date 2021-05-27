package br.com.inter.challenge.controller;

import br.com.inter.challenge.domain.User;
import br.com.inter.challenge.dto.UserDTO;
import br.com.inter.challenge.service.UserService;
import br.com.inter.challenge.testutil.UserCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@ExtendWith(SpringExtension.class)
@DisplayName("Tests for user controller")
class UserControllerTest {

    @InjectMocks
    private  UserController userController;

    @Mock
    private UserService userServiceMock;

    @BeforeEach
    void setUp(){
        PageImpl<User> userPage = new PageImpl<>(List.of(UserCreator.createUserValid()));
        when(userServiceMock.findAll(any())).thenReturn(userPage);
        when(userServiceMock.create(any(UserDTO.class))).thenReturn(UserCreator.createUserDtoValid());
        when(userServiceMock.findById(anyLong())).thenReturn(UserCreator.createUserDtoValid());
        doNothing().when(userServiceMock).delete(anyLong());
        doNothing().when(userServiceMock).update(any());
    }

    @Test
    @DisplayName("Create user when successful")
    void createUser_WhenSuccessful() {
        UserDTO userTobeSave = UserCreator.createUserDtoToBeSaved();
        String expectedName = userTobeSave.getName();
        Long idCreatedUser = 1L;
        UserDTO userToSaved = userController.create(userTobeSave).getBody();

        assertThat(userToSaved).isNotNull();
        assertThat(userToSaved.getName()).isEqualTo(expectedName);
        assertThat(userToSaved.getId()).isEqualTo(idCreatedUser);
    }

    @Test
    @DisplayName("Updated user when successful")
    void UpdateUser_WhenSuccessful() {
        UserDTO userDTO = UserCreator.createUserDtoToBeSaved();
        assertThatCode(() -> userController.update(userDTO))
                .doesNotThrowAnyException();
        ResponseEntity<Void> entity = userController.update(userDTO);
        assertThat(entity).isNotNull();
        assertThat(entity.getStatusCode()).isEqualTo(NO_CONTENT);
    }

    @Test
    @DisplayName("Find by id user when successful")
    void findByIdUser_WhenSuccessful() {
        UserDTO userDtoValid = UserCreator.createUserDtoValid();
        UserDTO userDTO = userController.findById(Long.valueOf(userDtoValid.getId())).getBody();

        assertThat(userDTO).isNotNull();
        assertThat(userDTO.getName()).isEqualTo(userDtoValid.getName());
        assertThat(userDTO.getId()).isEqualTo(userDtoValid.getId());
    }

    @Test
    @DisplayName("List return list of user inside page object when successful")
    void list_ReturnsListOfUserInsidePageObject_WhenSuccessful() {
        String expectedName = UserCreator.createUserValid().getName();
        Page<User> userPage = userController.listAll(null).getBody();

        assertThat(userPage).isNotEmpty().hasSize(1);
        assertThat(userPage.toList()).isNotEmpty();
        assertThat(userPage.toList().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("Delete client when successful")
    void deleteClient_WhenSuccessful() {
        assertThatCode(() -> userController.delete(1l))
                .doesNotThrowAnyException();
        ResponseEntity<Void> entity = userController.delete(1l);
        assertThat(entity).isNotNull();
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

}