package pl.edu.pw.mini.zpoif.project.youtube.app;

import pl.edu.pw.mini.zpoif.project.youtube.app.data.ChannelGenerator;
import pl.edu.pw.mini.zpoif.project.youtube.app.data.YouTubeChannel;

class Main{
    public static void main(String[] args) throws Exception{
    	// Tu wpisz klucz api
//       	String apiKey = "AIzaSyAwaxiFV5xg_05TivWGdbG6Ybz68tMXBHs";
       	String apiKey = "AIzaSyDya1-xeGvC4myMEyTCnoYhVpM9sVbnBiM";
    	// Tu wpisz link
    	String link = "https://www.youtube.com/c/BroCodez";
    	
    	ChannelGenerator.ApiAccess api = new ChannelGenerator.ApiAccess(apiKey);
    	YouTubeChannel channel = api.createChannel(link);
    	channel.getBacisInfo();
    }
}