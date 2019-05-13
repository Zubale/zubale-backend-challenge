package com.zubale.repo;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.zubale.model.Quote;

public interface QuoteRepo  extends JpaRepository<Quote, Long>{

}
