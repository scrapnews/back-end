package com.asiangang.scrapnews.config;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.asiangang.scrapnews.domain.dao.DaoPackage;

@EnableJpaRepositories(basePackageClasses = DaoPackage.class)
public class DataConfig {

}
