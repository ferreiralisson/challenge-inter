package br.com.inter.challenge.service;

import br.com.inter.challenge.domain.User;
import br.com.inter.challenge.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    UserDTO create(UserDTO userDTO);
    Page<User> findAll(Pageable pageable);
    UserDTO findById(long id);
    void update(UserDTO userDTO);
    void delete(long id);
}
