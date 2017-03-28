package com.asiangang.scrapnews.service.impl;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.asiangang.scrapnews.config.SecurityConfig.Roles;
import com.asiangang.scrapnews.domain.dao.NewsRepository;
import com.asiangang.scrapnews.domain.model.NewsEntity;
import com.asiangang.scrapnews.service.NewsService;

@Service(value = NewsServiceImpl.NAME)
public class NewsServiceImpl implements NewsService {
	public final static String NAME = "NewsService";
//	private final static String COUNTER_TEST = "rs.pscode.entity.test.";

	@Autowired
	private NewsRepository newsRepository;

//	@Autowired
//	private CounterService counterService;

	@Transactional
	@Secured(value = Roles.ROLE_USER)
	public Collection<NewsEntity> findAll() {
		return newsRepository.findAll();
	}
}
