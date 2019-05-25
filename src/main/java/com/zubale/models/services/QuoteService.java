package com.zubale.models.services;

import com.zubale.models.entity.Quote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuoteService {

    public List<Quote> findAll();

    public Page<Quote> findAll(Pageable pageable);

    public Quote findById(Long id);

    public Quote save(Quote cliente);

    public void delete(Long id);


}
