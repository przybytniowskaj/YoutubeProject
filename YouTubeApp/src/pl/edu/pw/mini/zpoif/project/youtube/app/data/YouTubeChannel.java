package pl.edu.pw.mini.zpoif.project.youtube.app.data;

import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import com.google.api.services.youtube.model.Channel;

public class YouTubeChannel {
	private ArrayList<Film> films;
	
	// dla linku typu /channel/channelId
	private String channelId;
	// dla linku typu /c/channelName
	private String customURL;
	
	private String title;
	private String link;
	private String description;
	private LocalDateTime publishedAt;
	private String thumbnailLink;
	
	private BigInteger viewCount;
	private BigInteger subscriberCount;
	private BigInteger videoCount;
	
	private boolean hiddenSubscriberCount;
	
	public String getChannelId() {
		return channelId;
	}
	
	public String getCustomURL() {
		return customURL;
	}
	
	// dla /channel/channelId
	public String linkToId(String link) {
    	String[] list = link.split("/");
    	int j = -1;
    	for(int i=0;i<list.length;i++) {
    		if(list[i].equals("channel")) {
    			j = i;
    			break;
    		}
    	}
    	if(j!=-1) {
    		return list[j+1];
    	}else{
    		return null;
    	}
	}
	
	// dla /c/customURL
	public String linkToCustomURL(String link) {
    	String[] list = link.split("/");
    	int j = -1;
    	for(int i=0;i<list.length;i++) {
    		if(list[i].equals("c")) {
    			j = i;
    			break;
    		}
    	}
    	if(j!=-1) {
    		return list[j+1];
    	}else{
    		return null;
    	}
	}
	
	public void getInfoFromLink(String link) {
		String wynik = linkToId(link);
		if(wynik==null) {
			wynik = linkToCustomURL(link);
			this.customURL = wynik;
			this.channelId = null;
		} else {
			this.customURL = null;
			this.channelId = wynik;
		}
	}
	
	public void addInfo(Channel channel) {
		if(channel==null) {
			return;
		}
		
		this.channelId = channel.getId();
		
		this.title = channel.getSnippet().getTitle();
		long time = channel.getSnippet().getPublishedAt().getValue();
		this.publishedAt = LocalDateTime.ofInstant(Instant.ofEpochMilli(time), TimeZone
                .getDefault().toZoneId());
		this.thumbnailLink = channel.getSnippet().getThumbnails().getDefault().getUrl();
		this.description = channel.getSnippet().getDescription();
		
		this.viewCount = channel.getStatistics().getViewCount();
		this.subscriberCount = channel.getStatistics().getSubscriberCount();
		this.videoCount = channel.getStatistics().getVideoCount();
		
		this.hiddenSubscriberCount = channel.getStatistics().getHiddenSubscriberCount();
	}
	
	public void getBacisInfo() {
		System.out.println(this.title+", wy�wietlenia: "+subscriberCount);
		System.out.println("Filmy");
		films.stream().limit(5).forEach(Film::getBacisInfo);
	}
	
	public void addVideoList(List<Film> films1) {
		if(films!=null) {
			films = (ArrayList<Film>) films1;
		}else {
			films.addAll(films1);
		}
	}
	
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	
	public static YouTubeChannel channelFromId(String id) {
		YouTubeChannel channel = new YouTubeChannel();
		channel.setChannelId(id);
		return channel;
	}
	
	private YouTubeChannel() {
		
	}
	
	public YouTubeChannel(String link) {
		getInfoFromLink(link);
		this.link = link;
		this.films = new ArrayList<Film>();
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getSub() {
		return this.subscriberCount.toString();
	}
	
	public String getTotalViews() {
		return this.viewCount.toString();
	}
	
	public String getVideoCount() {
		return this.videoCount.toString();
	}

	public ArrayList<Film> getFilms(){
		return this.films;
	}
	
	public String getPhoto() {
		return this.thumbnailLink;
	}
	
	public LocalDateTime getDate() {
		return this.publishedAt;
	}
	
	public String getDescription() {
		return this.description;
	}

	
}