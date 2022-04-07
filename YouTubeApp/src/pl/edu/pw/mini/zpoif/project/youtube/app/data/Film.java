package pl.edu.pw.mini.zpoif.project.youtube.app.data;

import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

import com.google.api.services.youtube.model.Video;

public class Film {
	private String videoId;
	private LocalDateTime publishedAt;
	private String title;
	private String description;
	private String thumbnailLink;
	private String duration;
	
	private BigInteger viewCount;
	private BigInteger likeCount;
	private BigInteger favoriteCount;
	private BigInteger commentCount;
	
	public void getBacisInfo() {
		System.out.println(this.title+" "+viewCount);
	}
	
	public Film(Video video){
		this.videoId = video.getId();
		long time = video.getSnippet().getPublishedAt().getValue();
		this.publishedAt = LocalDateTime.ofInstant(Instant.ofEpochMilli(time), TimeZone
                .getDefault().toZoneId());
		this.title = video.getSnippet().getTitle();
		this.description = video.getSnippet().getDescription();
		this.thumbnailLink = video.getSnippet().getThumbnails().getDefault().getUrl();
		this.duration = video.getContentDetails().getDuration();
		
		this.viewCount = video.getStatistics().getViewCount();
		this.likeCount = video.getStatistics().getLikeCount();
		this.favoriteCount = video.getStatistics().getFavoriteCount();
		this.commentCount = video.getStatistics().getCommentCount();
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getMinature() {
		return this.thumbnailLink;
	}
	
	public LocalDateTime getDate() {
		return this.publishedAt;
	}
	
	public String getViews() {
		return this.viewCount.toString();
	}
	
	public String getLikes() {
		return this.likeCount.toString();
	}
	
	public String getCom() {
		return this.commentCount.toString();
	}
}