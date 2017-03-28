package com.asiangang.scrapnews.domain.dao;

import org.springframework.data.repository.CrudRepository;

import com.asiangang.scrapnews.domain.model.UserEntity;

public interface UserDao extends CrudRepository<UserEntity, Long> {

	UserEntity findByUsername(String username);

}
