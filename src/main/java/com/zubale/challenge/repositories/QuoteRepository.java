/**
 * 
 */
package com.zubale.challenge.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.zubale.challenge.entities.Quote;

/**
 * @author maagapi
 *
 */
@Repository
public interface QuoteRepository extends PagingAndSortingRepository<Quote, Integer>{

}
