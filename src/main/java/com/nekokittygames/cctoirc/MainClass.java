package com.nekokittygames.cctoirc;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by katrina on 03/01/2016.
 */
public class MainClass {

    public static void main(String[] args)
    {
        String code=args[0];
        System.out.println("Looking up code: "+code);
        String packName=CardCastAPI.GetPackName(code);
        System.out.println("Card pack name: "+packName);
        System.out.println("Retrieving Questions.");
        JSONArray questions=CardCastAPI.GetQuestions(code);
        //System.out.println(CardCastAPI.JsonToCard(arr.getJSONObject(0), "Question", packName).toString());
        List<IRCCard> QuestionCards=new ArrayList<IRCCard>();
        for(int i=0;i<questions.length();i++)
        {
        	QuestionCards.add(CardCastAPI.JsonToCard(questions.getJSONObject(i), "Question", packName));
        }
        System.out.println("Retrieving Answers.");
        JSONArray answers=CardCastAPI.GetAnswers(code);
        List<IRCCard> AnswerCards=new ArrayList<IRCCard>();
        for(int i=0;i<answers.length();i++)
        {
        	AnswerCards.add(CardCastAPI.JsonToCard(answers.getJSONObject(i), "Answer", packName));
        }
        GsonBuilder builder=new GsonBuilder();
        builder.disableHtmlEscaping();
        
        System.out.println("Outputting "+code+"_q.json");
        String jsonQ=builder.create().toJson(QuestionCards);
        StringUtils.replace(jsonQ, "\u0027", "'");
        try {
			//PrintWriter writer=new PrintWriter(code+"_q.json");
        	FileWriter writer=new FileWriter(code+"_q.json");
			writer.write(jsonQ);
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("Outputting "+code+"_a.json");
        String jsonA=builder.create().toJson(AnswerCards);
        StringUtils.replace(jsonA, "\u0027", "'");
        try {
			PrintWriter writer=new PrintWriter(code+"_a.json");
			writer.write(jsonA);
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
