package com.zubale.models.services;

import com.zubale.models.dao.VoteDao;
import com.zubale.models.entity.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteDao voteDao;

    @Override
    @Transactional(readOnly = true)
    public List<Vote> findAll() {
        return (List<Vote>) voteDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Vote> findAll(Pageable pageable){
        return  voteDao.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Vote findById(Long id) {
        return voteDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Vote save(Vote vote) {
        return voteDao.save(vote);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        voteDao.deleteById(id);
    }
}
