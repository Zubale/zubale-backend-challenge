package com.zubale.models.services;

import com.zubale.models.dao.QuoteDao;
import com.zubale.models.entity.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QuoteServiceImpl implements QuoteService {

    @Autowired
    private QuoteDao quoteDao;

    @Override
    @Transactional(readOnly = true)
    public List<Quote> findAll() {
        return (List<Quote>) quoteDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Quote> findAllOrderByMostVoted() {
        return (List<Quote>) quoteDao.findAllOrderByMostVoted();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Quote> findAll(Pageable pageable){
        return  quoteDao.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Quote findById(Long id) {
        return quoteDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Quote save(Quote quote) {
        return quoteDao.save(quote);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        quoteDao.deleteById(id);
    }
}
