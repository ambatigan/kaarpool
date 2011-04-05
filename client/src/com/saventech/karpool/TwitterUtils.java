package com.saventech.karpool;

import oauth.signpost.OAuth;
import twitter4j.PagableResponseList;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import twitter4j.User;
import twitter4j.http.AccessToken;
import android.content.SharedPreferences;

public class TwitterUtils {

	public static boolean isAuthenticated(SharedPreferences prefs) {

		String token = prefs.getString(OAuth.OAUTH_TOKEN, "");
		String secret = prefs.getString(OAuth.OAUTH_TOKEN_SECRET, "");
		
		AccessToken a = new AccessToken(token,secret);
		Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(Constants.CONSUMER_KEY, Constants.CONSUMER_SECRET);
		twitter.setOAuthAccessToken(a);
		
		try {
			twitter.getAccountSettings();
			
			return true;
		} catch (TwitterException e) {
			return false;
		}
	}
	
	public static void friendList(SharedPreferences prefs,String msg) throws Exception {
		String token = prefs.getString(OAuth.OAUTH_TOKEN, "");
		String secret = prefs.getString(OAuth.OAUTH_TOKEN_SECRET, "");
		
		AccessToken a = new AccessToken(token,secret);
		Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(Constants.CONSUMER_KEY, Constants.CONSUMER_SECRET);
		twitter.setOAuthAccessToken(a);
        twitter.updateStatus(msg);
        String name = twitter.getScreenName();
        System.out.println(twitter.getId()+"user name");
        int sss = twitter.getId();
        System.out.println(name+"screen name"+a.getUserId()+"user name");
//        User user = twitter.showUser(sss);
//           System.out.println(user.getDescription());
//           System.out.println(user.getLocation());
//           System.out.println(user.getStatusText());
//           System.out.println(user.getLang());
        
       
        
		PagableResponseList<User> us = twitter.getFriendsStatuses();
		for(User u: us)
		{
			String ss = u.getScreenName();
			System.out.println(ss+"screen names in pageable response list");
		}
		
	}	
	
}
