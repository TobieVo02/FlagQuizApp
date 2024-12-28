package com.com.flag;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class activity_gameplay_hard_1 extends Activity implements View.OnClickListener {
    TextView Ques,Result;
    int pos = 0, pro = 0, HighScore = 0;
    Button A,B,C,D;
    ArrayList<QuestionNare> L = new ArrayList<>();
    ArrayList<QuestionNare> QuesList_layout1 = new ArrayList<>();
    ArrayList<QuestionNare> QuesList_layout2 = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.test_gamelayout_1);
//        Ques = (TextView) findViewById(R.id.textQuesID);
//        Result = (TextView) findViewById(R.id.TextLayout1Result);
//        A = (Button) findViewById(R.id.AnswerA);
//        B = (Button) findViewById(R.id.AnswerB);
//        C = (Button) findViewById(R.id.AnswerC);
//        D = (Button) findViewById(R.id.AnswerD);
        LoadHighScore();
//        ReadData();
//        Display(pos);
//        int[] Arr = {R.id.AnswerA, R.id.AnswerB,
//                R.id.AnswerC, R.id.AnswerD};
//        for (int id : Arr) {
//            View v = (View) findViewById(id);
//            v.setOnClickListener(this);
//        }
        ReadData("data_hard_layout1.xml", QuesList_layout1);
        ReadData("data_hard_layout2.xml", QuesList_layout2);
        Gameplay_layout2();
    }
    @Override
    public void onClick(View v) {
        int idCheck = v.getId();
//        if (idCheck == R.id.AnswerA) {
//            if (L.get(pos).Answer.compareTo("A") == 0)
//                pro = pro + 10;
//        } else if (idCheck == R.id.AnswerB) {
//            if (L.get(pos).Answer.compareTo("B") == 0)
//                pro = pro + 10;
//        } else if (idCheck == R.id.AnswerC) {
//            if (L.get(pos).Answer.compareTo("C") == 0)
//                pro = pro + 10;
//        } else if (idCheck == R.id.AnswerD) {
//            if (L.get(pos).Answer.compareTo("D") == 0)
//                pro = pro + 10;
            if (pos % 2 == 0) {
                if (idCheck == R.id.IButtonLayout2AnsA) {
                    if (QuesList_layout2.get(pos).Answer.compareTo("A") == 0)
                        pro = pro + 10;
                } else if (idCheck == R.id.IButtonLayout2AnsB) {
                    if (QuesList_layout2.get(pos).Answer.compareTo("B") == 0)
                        pro = pro + 10;
                } else if (idCheck == R.id.IButtonLayout2AnsC) {
                    if (QuesList_layout2.get(pos).Answer.compareTo("C") == 0)
                        pro = pro + 10;
                } else if (idCheck == R.id.IButtonLayout2AnsD) {
                    if (QuesList_layout2.get(pos).Answer.compareTo("D") == 0)
                        pro = pro + 10;
                }
            }
            else {
                if (idCheck == R.id.ButtonLayout1AnsA) {
                    if (QuesList_layout1.get(pos).Answer.compareTo("A") == 0)
                        pro = pro + 10;
                } else if (idCheck == R.id.ButtonLayout1AnsB) {
                    if (QuesList_layout1.get(pos).Answer.compareTo("B") == 0)
                        pro = pro + 10;
                } else if (idCheck == R.id.ButtonLayout1AnsC) {
                    if (QuesList_layout1.get(pos).Answer.compareTo("C") == 0)
                        pro = pro + 10;
                } else if (idCheck == R.id.ButtonLayout1AnsD) {
                    if (QuesList_layout1.get(pos).Answer.compareTo("D") == 0)
                        pro = pro + 10;
                }
            }
            pos++;
//            if (pos >= L.size()) {
                if (pos >= 10) {
                    Intent intent = new Intent(activity_gameplay_hard_1.this, activity_result.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("Progress", pro);
                    bundle.putInt("QuestionID", pos);
                    intent.putExtra("MyPackage", bundle);
                    startActivity(intent);
                    if (pro > HighScore) {
                        HighScore = pro;
                        SaveHighScore();
                    }
                    finish();
//                } else Display(pos);
            } else {
                if (pos % 2 == 0) {
                    Gameplay_layout2();
                }
                else
                {
                    Gameplay_layout1();
                }
            }
        }

//        private void SaveHighScore() {
//            SharedPreferences sharedPreferences = getSharedPreferences("MyData",Context.MODE_PRIVATE);
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putInt("H",HighScore);
//            editor.apply();
            private void Gameplay_layout1(){
                setContentView(R.layout.test_gamelayout_1);
                Display_layout1(pos, QuesList_layout1);
                int[] Arr = {R.id.ButtonLayout1AnsA, R.id.ButtonLayout1AnsB,
                        R.id.ButtonLayout1AnsC, R.id.ButtonLayout1AnsD};
                for (int id : Arr) {
                    View v = (View) findViewById(id);
                    v.setOnClickListener(this);
                }
            }

//            private void Display(int i) {
//                ((TextView) findViewById(R.id.textQuesID)).setText("QUESTION " + L.get(i).ID);
//                ((ImageView) findViewById(R.id.imageView)).setImageResource(this.getResources().getIdentifier(L.get(i).Q, null, this.getPackageName()));
//                A.setText(L.get(i).AnswerA);
//                B.setText(L.get(i).AnswerB);
//                C.setText(L.get(i).AnswerC);
//                D.setText(L.get(i).AnswerD);
                private void Gameplay_layout2(){
                    setContentView(R.layout.test_gamelayout_2);
                    Display_layout2(pos, QuesList_layout2);
                    int[] Arr = {R.id.IButtonLayout2AnsA, R.id.IButtonLayout2AnsB,
                            R.id.IButtonLayout2AnsC, R.id.IButtonLayout2AnsD};
                    for (int id : Arr) {
                        View v = (View) findViewById(id);
                        v.setOnClickListener(this);
                    }
                }
                @SuppressLint({"SetTextI18n", "DiscouragedApi"})
                private void Display_layout1(int i, ArrayList<QuestionNare> L) {
                    TextView Result;
                    Result = findViewById(R.id.TextLayout1Result);
                    ((TextView) findViewById(R.id.TextLayout1QuesID)).setText("QUESTION " + L.get(i).ID);
                    ((ImageView) findViewById(R.id.ImgLayout1Question)).setImageResource(this.getResources().getIdentifier(L.get(i).Q, null, this.getPackageName()));
                    ((Button) findViewById(R.id.ButtonLayout1AnsA)).setText(L.get(i).AnswerA);
                    ((Button) findViewById(R.id.ButtonLayout1AnsB)).setText(L.get(i).AnswerB);
                    ((Button) findViewById(R.id.ButtonLayout1AnsC)).setText(L.get(i).AnswerC);
                    ((Button) findViewById(R.id.ButtonLayout1AnsD)).setText(L.get(i).AnswerD);
                    Result.setText("score: " + pro);
                }
                @SuppressLint({"SetTextI18n", "DiscouragedApi"})
                void Display_layout2(int i, ArrayList<QuestionNare> L) {
                    TextView Result;
                    Result = findViewById(R.id.TextLayout2Result);
                    ((TextView) findViewById(R.id.TextLayout2QuesID)).setText("QUESTION " + L.get(i).ID);
                    ((TextView) findViewById(R.id.TextLayout2Question)).setText(L.get(i).Q);
                    ((ImageButton) findViewById(R.id.IButtonLayout2AnsA)).setImageResource(this.getResources().getIdentifier(L.get(i).AnswerA, null, this.getPackageName()));
                    ((ImageButton) findViewById(R.id.IButtonLayout2AnsB)).setImageResource(this.getResources().getIdentifier(L.get(i).AnswerB, null, this.getPackageName()));
                    ((ImageButton) findViewById(R.id.IButtonLayout2AnsC)).setImageResource(this.getResources().getIdentifier(L.get(i).AnswerC, null, this.getPackageName()));
                    ((ImageButton) findViewById(R.id.IButtonLayout2AnsD)).setImageResource(this.getResources().getIdentifier(L.get(i).AnswerD, null, this.getPackageName()));
                    Result.setText("score: " + pro);
                }

//                private void ReadData() {
                    private void ReadData(String filename, ArrayList<QuestionNare> list) {
                        try {
                            DocumentBuilderFactory DBF = DocumentBuilderFactory.newInstance();
                            DocumentBuilder builder = DBF.newDocumentBuilder();
//                            InputStream in = getAssets().open("data_hard_layout1.xml");
                            InputStream in = getAssets().open(filename);
                            Document doc = builder.parse(in);
                            Element root = doc.getDocumentElement();
//                            NodeList list = root.getChildNodes();
//                            for (int i = 0; i < list.getLength(); i++) {
//                                Node node = list.item(i);
                                NodeList nodelist = root.getChildNodes();
                                for (int i = 0; i < nodelist.getLength(); i++) {
                                    Node node = nodelist.item(i);
                                    if (node instanceof Element) {
                                        Element Item = (Element) node;
                                        NodeList listChild = Item.getElementsByTagName("ID");
                                        String ID = listChild.item(0).getTextContent();
                                        listChild = Item.getElementsByTagName("Question");
                                        String Question = listChild.item(0).getTextContent();
                                        listChild = Item.getElementsByTagName("AnswerA");
                                        String AnswerA = listChild.item(0).getTextContent();
                                        listChild = Item.getElementsByTagName("AnswerB");
                                        String AnswerB = listChild.item(0).getTextContent();
                                        listChild = Item.getElementsByTagName("AnswerC");
                                        String AnswerC = listChild.item(0).getTextContent();
                                        listChild = Item.getElementsByTagName("AnswerD");
                                        String AnswerD = listChild.item(0).getTextContent();
                                        listChild = Item.getElementsByTagName("Answer");
                                        String Answer = listChild.item(0).getTextContent();
                                        QuestionNare Q1 = new QuestionNare();
                                        Q1.ID = ID;
                                        Q1.Q = Question;
                                        Q1.AnswerA = AnswerA;
                                        Q1.AnswerB = AnswerB;
                                        Q1.AnswerC = AnswerC;
                                        Q1.AnswerD = AnswerD;
                                        Q1.Answer = Answer;
//                                        L.add(Q1);
//                                    };
                                    list.add(Q1);
                                }
                            }
                        } catch (ParserConfigurationException | SAXException | IOException e) {
                            e.printStackTrace();
                        }
                    }

//                    private void LoadHighScore() {
                        void LoadHighScore(){
                            SharedPreferences sharedPreferences = getSharedPreferences("MyData",
                                    Context.MODE_PRIVATE);
                            if (sharedPreferences !=null){
                                HighScore = sharedPreferences.getInt("H",0);
                            }
                        }
                        void SaveHighScore(){
                            SharedPreferences sharedPreferences = getSharedPreferences("MyData",Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putInt("H",HighScore);
                            editor.apply();
                        }
                    }
