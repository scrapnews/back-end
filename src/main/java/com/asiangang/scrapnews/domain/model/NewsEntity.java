package com.asiangang.scrapnews.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity(name = "news")
@Table(name = "NEWS")
public class NewsEntity  {

	private static final long serialVersionUID = 4815877135015943617L;

	@Id()
	@Column(name = "ID_")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "DESCRIPTION_", nullable = false, unique = true)
	private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}