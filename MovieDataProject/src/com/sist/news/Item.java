package com.sist.news;

import javax.xml.bind.annotation.XmlElement;

/*
 *      <title>영화 '신데렐라' "꿈은 현실이 된다! 릴리 제임스, 리차드 매든, 케이트 블란쳇 주연"</title>
		<link>http://www.lecturernews.com/news/articleView.html?idxno=63032</link>
		<description>
		<![CDATA[ 36, 누적관객수 716,491명을 기록한 113분 분량의 로맨스 영화다. 이 영화는 42회 새턴 어워즈에서 최우수 판타지영화상을 수상했다. 네이버 영화가 소개하는 영화 <신데렐라>의 줄거리를 알아보자. "착한 마음과 용기를 가지렴.... ]]>
		</description>
		<pubDate>Mon, 15 Mar 2021 09:16:00 +0900</pubDate>
		<author>한국강사신문</author>
		<category>섹션없음</category>
 */
public class Item {
    private String title;
    private String link;
    private String description;
	private String pubDate;
	private String author;
    public String getTitle() {
	return title;
	}
    @XmlElement
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	@XmlElement
	public void setLink(String link) {
		this.link = link;
	}
	public String getDescription() {
		return description;
	}
	// element.text() @XmlElement, elemenent.attr() @XmlAttribute
	@XmlElement
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPubDate() {
		return pubDate;
	}
	@XmlElement
	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}
	public String getAuthor() {
		return author;
	}
	@XmlElement
	public void setAuthor(String author) {
		this.author = author;
	} 
}
