package com.zubale.models.services;

import com.zubale.models.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    public User findByUsername(String id);

    public List<User> findAll();

    public Page<User> findAll(Pageable pageable);

    public User findById(Long id);

    public User save(User cliente);

    public void delete(Long id);
}
