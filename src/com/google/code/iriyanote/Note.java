package com.google.code.iriyanote;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Note {
	public static final int STATUS_ACTIVE = 0;
	public static final int STATUS_DEACTIVE = 1;
	public static final int STATUS_FIN = 2;
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	
	@Persistent
	private String author;
	@Persistent
	private String name;
	@Persistent
	private Integer chapter;
	@Persistent
	private Integer maxChapter;
	@Persistent
	private Integer showDay;
	@Persistent
	private String imgUrl;
	@Persistent
	private Date createTime;
	@Persistent
	private int status;
	
	public void addChapter() {
		/*if(chapter >= maxChapter) { // 到max时两个都增长
			this.chapter = chapter + 1;
			this.maxChapter = maxChapter + 1;
		} else { // 只加chapter
			this.chapter = chapter + 1;
		}*/
		this.chapter = (chapter >= maxChapter - 1) ? maxChapter : chapter + 1;
	}
	
	public void subChapter() {
		this.chapter = (chapter <= 1) ? 0 : chapter - 1;
	}
	
	public void finChapter() {
		this.maxChapter = this.chapter;
		this.status = STATUS_FIN;
	}
	
	public void ignChapter() {
		this.status = STATUS_FIN;
	}
	
	public void resChapter() {
		this.maxChapter = maxChapter * 2;
		this.status = STATUS_ACTIVE;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getChapter() {
		return chapter;
	}
	public void setChapter(Integer chapter) {
		this.chapter = chapter;
	}
	public Integer getMaxChapter() {
		return maxChapter;
	}
	public void setMaxChapter(Integer maxChapter) {
		this.maxChapter = maxChapter;
	}
	public Integer getShowDay() {
		return showDay;
	}
	public void setShowDay(Integer showDay) {
		this.showDay = showDay;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
