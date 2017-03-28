package com.asiangang.scrapnews.domain.dao;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import com.asiangang.scrapnews.domain.model.NewsEntity;

public interface NewsRepository extends CrudRepository<NewsEntity, Long> {
	Collection<NewsEntity> findAll();
}
