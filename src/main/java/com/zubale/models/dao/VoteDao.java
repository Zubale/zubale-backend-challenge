package com.zubale.models.dao;

import com.zubale.models.entity.Vote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface VoteDao extends CrudRepository<Vote, Long> {

    @Query("SELECT u from Vote u where u.id=?1")
    public Vote findByIdQuery(String id);
}
