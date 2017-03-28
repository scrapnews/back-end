package com.asiangang.scrapnews.service;

import java.util.Collection;

import com.asiangang.scrapnews.domain.model.NewsEntity;

public interface NewsService {

	Collection<NewsEntity> findAll();

}
