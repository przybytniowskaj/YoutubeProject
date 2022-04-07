package pl.edu.pw.mini.zpoif.project.youtube.app.data;

import java.util.ArrayList;
import java.util.HashSet;

public class ChannelCollector {
	private ArrayList<YouTubeChannel> channels;
	private ArrayList<ChannelGenerator.ApiAccess> apiKeys;
	private HashSet<String> channelIds;
	
	
	//Klasa przechowuje dane z kana��w, kt�re ju� zosta�y wyszukane
	//Oraz klucze api
	
	public void addChannel(YouTubeChannel channel) {
		String channelId = channel.getChannelId();
		YouTubeChannel chan = channels.stream().filter(c -> c.getChannelId().equals(channelId)).findAny().orElse(null);
		if(chan==null){
			channels.add(channel);
		}
	}
	public void deleteChannel(YouTubeChannel channel) {
		channels.remove(channel);
	}
	
	public void addApiKey(String apiKey) {
		apiKeys.add(new ChannelGenerator.ApiAccess(apiKey));
	}
	
	// sprawdza czy w channels jest kana� z nazw� z linku typu /c/channelName
	public YouTubeChannel searchForChannelByName() {
		return null;
	}
	
	// sprawdza czy w channels jest kana� o danym id
	public YouTubeChannel searchForChannelById() {
		return null;
	}
	
	public ChannelCollector() {
		this.channels = new ArrayList<YouTubeChannel>();
		this.apiKeys = new ArrayList<ChannelGenerator.ApiAccess>();
		this.channelIds = new HashSet<>();
	}
	
	public ArrayList<YouTubeChannel> getList(){
		return this.channels;
	}
}