package com.sist.news;


public class Item {
    private String title;
    private String description;
    private String author;
    private String link;
    private String url;
    
    
    public String getUrl() {
		return url;
	}
    
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getLink() {
		return link;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	/*public Mediathumbnail getMediathumbnail() {
		return mediathumbnail;
	}
	@XmlElement(name="media:thumbnail")
	public void setMediathumbnail(Mediathumbnail mediathumbnail) {
		this.mediathumbnail = mediathumbnail;
	}
   */
   
}
