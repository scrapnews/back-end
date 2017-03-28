package com.asiangang.scrapnews.domain.dao;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import com.asiangang.scrapnews.domain.model.TestEntity;

public interface TestRepository extends CrudRepository<TestEntity, Long> {
	Collection<TestEntity> findAll();
}
