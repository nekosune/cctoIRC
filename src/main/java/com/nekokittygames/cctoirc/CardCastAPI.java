package com.nekokittygames.cctoirc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CardCastAPI {

	public static String MAIN_URL="https://api.cardcastgame.com/v1/decks/";
	
	
	private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	  }

	  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
	    InputStream is = new URL(url).openStream();
	    try {
	      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	      String jsonText = readAll(rd);
	      JSONObject json = new JSONObject(jsonText);
	      return json;
	    } finally {
	      is.close();
	    }
	  }
	  
	  
	
	public static String GetPackName(String code)
	{
		String Name="";
		try
		{
		JSONObject json = readJsonFromUrl(MAIN_URL+code);
		Name=(String) json.get("name");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return Name;
	}
	
	public static JSONArray GetQuestions(String code)
	{
		try
		{
		JSONObject json = readJsonFromUrl(MAIN_URL+code+"/cards");
		return json.getJSONArray("calls");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static JSONArray GetAnswers(String code)
	{
		try
		{
		JSONObject json = readJsonFromUrl(MAIN_URL+code+"/cards");
		return json.getJSONArray("responses");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static String TurnQuestionToString(JSONArray array)
	{
		String retVal="";
		String[] normalArray=new String[array.length()];
		int i=0;
		for(Object str:array)
		{
			String tmp=(String)str;
			normalArray[i++]=tmp;
		}
		retVal=StringUtils.join(normalArray,"%s");
		return retVal;
	}
	
	public static IRCCard JsonToCard(JSONObject obj,String type,String source)
	{
		IRCCard card=new IRCCard();
		card.setType(type);
		String val="";
		if(type.equalsIgnoreCase("Question"))
		{
			val=TurnQuestionToString(obj.getJSONArray("text"));
		}
		else
			val=obj.getJSONArray("text").getString(0);
		card.setValue(val);
		card.setKeep("Yes");
		card.setDraw(StringUtils.countMatches(val, "%s")-1);
		card.setPick(StringUtils.countMatches(val, "%s"));
		if(type.equalsIgnoreCase("Answer"))
		{
			card.setDraw(0);
			card.setPick(1);
		}
		card.setSource(source);
		return card;
	}
}
