package br.com.inter.challenge.service.impl;

import br.com.inter.challenge.domain.User;
import br.com.inter.challenge.dto.UserDTO;
import br.com.inter.challenge.repository.UserRepository;
import br.com.inter.challenge.testutil.UserCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("Tests for user service")
class UserServiceImplTest {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @MockBean
    private UserRepository userRepositoryMock;

    @BeforeEach
    void setUp() {
        PageImpl<User> userPage = new PageImpl<>(List.of(UserCreator.createUserValid()));
        when(userRepositoryMock.findAll(any(PageRequest.class))).thenReturn(userPage);
        when(userRepositoryMock.save(any(User.class))).thenReturn(UserCreator.createUserValid());
        doNothing().when(userRepositoryMock).delete(any(User.class));
        when(userRepositoryMock.findById(anyLong())).thenReturn(Optional.of(UserCreator.createUserValid()));
    }

    @Test
    @DisplayName("Create user when successful")
    void createUser_WhenSuccessful() {
        UserDTO userDTO = userServiceImpl.create(UserCreator.createUserDtoToBeSaved());
        assertThat(userDTO.getEmail()).isNotNull().isEqualTo(UserCreator.createUserValid().getEmail());
    }

    @Test
    @DisplayName("Updated user when successful")
    void UpdateUser_WhenSuccessful() {
        assertThatCode(() -> userServiceImpl.update(UserCreator.createUserDtoToBeUpdated()))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("List return list of user inside page object when successful")
    void list_ReturnsListOfUserInsidePageObject_WhenSuccessful() {
        String expectedName = UserCreator.createUserValid().getName();
        Page<User> userPage = userServiceImpl.findAll(PageRequest.of(1,1));

        assertThat(userPage).isNotEmpty().hasSize(1);
        assertThat(userPage.toList()).isNotEmpty();
        assertThat(userPage.toList().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("Find by id user when successful")
    void findByIdUser_WhenSuccessful() {
        UserDTO userDtoValid = UserCreator.createUserDtoValid();
        UserDTO userDTO = userServiceImpl.findById(userDtoValid.getId());

        assertThat(userDTO).isNotNull();
        assertThat(userDTO.getName()).isEqualTo(userDtoValid.getName());
        assertThat(userDTO.getId()).isEqualTo(userDtoValid.getId());
    }

    @Test
    @DisplayName("Delete user when successful")
    void deleteUser_WhenSuccessful(){
        assertThatCode(() -> userServiceImpl.delete(1l))
                .doesNotThrowAnyException();
    }

}