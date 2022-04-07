package pl.edu.pw.mini.zpoif.project.youtube.app.data;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Channel;
import com.google.api.services.youtube.model.ChannelListResponse;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.stream.Collectors;

public class ChannelGenerator {
	//klasa korzystaj�ca z api
	public static class ApiAccess {
		private static final String APPLICATION_NAME = "YouTubeApp";
		private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
		private String apiKey;
	    
		// przyjmuje link do filmu i zwraca kana� z uzupe�nionymi informacjami
		public Channel getChannelInfo(YouTubeChannel channel)
			throws GeneralSecurityException, IOException, GoogleJsonResponseException{	
			final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
	    	String channelId = channel.getChannelId();
			YouTube youtubeService = new YouTube.Builder(httpTransport, JSON_FACTORY, null)
	    			.setApplicationName(APPLICATION_NAME)
	                .build();
			YouTube.Channels.List request = youtubeService.channels()
		            .list("snippet,contentDetails,statistics");
		    ChannelListResponse response = request.setKey(apiKey).setId(channelId).execute();
		    Channel chan = response.getItems().get(0);
		    return chan;
		}
		
		// zwraca informacje o kanale na podstawie jego customUrl
		public Channel getChannelInfoFromCustomURL(YouTubeChannel channel) 
				throws IOException, GeneralSecurityException {
			final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
	    	YouTube youtubeService = new YouTube.Builder(httpTransport, JSON_FACTORY, null)
	    			.setApplicationName(APPLICATION_NAME)
	                .build();
			YouTube.Search.List request = youtubeService.search()
		            .list("snippet");
		    SearchListResponse response = request.setKey(apiKey).setQ(channel.getCustomURL())
		    		.setType("channel")
		            .execute();
		    Channel chan = response.getItems().stream()
		    		.filter(sr -> sr.getId().getKind().equals("youtube#channel"))
		    		.map(sr -> sr.getId().getChannelId())
		    		.map(s -> {
		    			YouTubeChannel chann = YouTubeChannel.channelFromId(s);
		    			try {
							return getChannelInfo(chann);
						} catch (GoogleJsonResponseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (GeneralSecurityException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		    			return null;
		    		})
		    		.filter(ch -> ch!=null)
		    		.filter(ch -> ch.getSnippet().getCustomUrl().equalsIgnoreCase(channel.getCustomURL()))
		    		.findAny().orElse(null);
			return chan;
		}
		
	    // zwraca list� film�w dla danego kana�u po id
	    public List<String> getVideoIdList(String channelId)
	        throws GeneralSecurityException, IOException, GoogleJsonResponseException {
	    	final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
	    	YouTube youtubeService = new YouTube.Builder(httpTransport, JSON_FACTORY, null)
	    			.setApplicationName(APPLICATION_NAME)
	                .build();
	    	YouTube.Search.List request = youtubeService.search()
	            .list("snippet");
	        SearchListResponse response = request.setKey(apiKey).setChannelId(channelId)
	        		.setMaxResults(50L)
	                .setOrder("viewCount")
	                .execute();
	        return response.getItems().stream().
	        		map(s -> s.getId().getVideoId()).
	        		filter(s -> s!=null).
	        		collect(Collectors.toList());
	    }
	    
	    // zwraca klas� film z informacjami po podaniu id filmu 
	    public Film getVideoInformationFromId(String videoId)
	            throws GeneralSecurityException, IOException, GoogleJsonResponseException {
	    	final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();	
	    	YouTube youtubeService = new YouTube.Builder(httpTransport, JSON_FACTORY, null)
		    			.setApplicationName(APPLICATION_NAME)
		                .build();
	            // Define and execute the API request
	            YouTube.Videos.List request = youtubeService.videos()
	                .list("snippet,contentDetails,statistics");
	            VideoListResponse response = request.setKey(apiKey).setId(videoId).execute();
	            List<Video> videos = response.getItems();
	            return videos.stream().
	            		map(v -> new Film(v)).
	            		collect(Collectors.toList()).get(0);
	        }
	    
	    public YouTubeChannel createChannel(String link) throws GoogleJsonResponseException, 
	    GeneralSecurityException, IOException {
	    	YouTubeChannel channel = new YouTubeChannel(link);
	    	Channel chan;
	    	if(channel.getChannelId()!=null) {
	    		chan = getChannelInfo(channel);
	    	}else {
	    		chan = getChannelInfoFromCustomURL(channel);
	    	}
	    	channel.addInfo(chan);
	    	List<Film> videos = getVideoIdList(channel.getChannelId()).stream()
	    			.map(id -> {
						try {
							return getVideoInformationFromId(id);
						} catch (GoogleJsonResponseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (GeneralSecurityException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return null;
					})
	    			.filter(v -> v!=null)
	    			.collect(Collectors.toList());
	    	channel.addVideoList(videos);
	    	return channel;
	    }
	    
	    public ApiAccess(String apiKey) {
	    	this.apiKey = apiKey;
	    }
	}
	
	//public Channel getChannel();
	//public List<Film> getChannelsFilms();
}