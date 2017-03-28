package com.asiangang.scrapnews.web.api;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.asiangang.scrapnews.domain.model.NewsEntity;
import com.asiangang.scrapnews.service.NewsService;
import com.asiangang.scrapnews.service.impl.NewsServiceImpl;

@RestController
public class NewsResource {
	
	@Autowired
	@Qualifier(value = NewsServiceImpl.NAME)
	private NewsService newsService;

	@RequestMapping(value = "/api/client/newsAll", method = RequestMethod.GET)
	@ResponseStatus(code = HttpStatus.OK)
	public Collection<NewsEntity> apiTestCreate() {
		return newsService.findAll();
	}

}
