package com.zubale.models.services;

import com.zubale.models.entity.Vote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VoteService {

    public List<Vote> findAll();

    public Page<Vote> findAll(Pageable pageable);

    public Vote findById(Long id);

    public Vote save(Vote cliente);

    public void delete(Long id);
}
