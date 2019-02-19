package com.example.mycalculator;

import android.media.VolumeShaper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import static android.view.View.OnClickListener;
import android.content.Intent;


public class MainActivity extends AppCompatActivity {
    private double NumberBf;
    private String operation;
    private EditText Scr;
    private ButtonClickLisner bttnClick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Scr = (EditText) findViewById(R.id.resultText);
        Scr.setEnabled(false);
        bttnClick = new ButtonClickLisner();



        int ViewList[] = {R.id.bttn0, R.id.bttn1, R.id.bttn2, R.id.bttn3, R.id.bttn4, R.id.bttn5,
                R.id.bttn6, R.id.bttn7, R.id.bttn8, R.id.bttn9, R.id.bttnAdd, R.id.bttnSub, R.id.bttnDiv,
                R.id.bttnMult, R.id.bttnEqual, R.id.bttnDec, R.id.bttnClr, R.id.measureScreen};

        for (int bttn : ViewList)
        {
            View v = (View) findViewById(bttn);

            v.setOnClickListener(bttnClick);
        }
    }//OnCreate



    private class ButtonClickLisner implements OnClickListener
    {
        public void onClick (View v)
        {
            switch (v.getId())
            {
                case R.id.bttnClr :
                    Scr.setText("0");
                    NumberBf = 0;
                    operation = "";
                    break;
                case R.id.bttnAdd:
                    MathFunction("+");
                    break;
                case R.id.bttnEqual:
                    mathResult();
                    break;
                case R.id.measureScreen:
                    /// create a nd intenet
                    Intent intent = new Intent(MainActivity.this, Measurement.class);
                    startActivity(intent);
                    break;
                case R.id.bttn0:
                case R.id.bttn1:
                case R.id.bttn2:
                case R.id.bttn3:
                case R.id.bttn4:
                case R.id.bttn5:
                case R.id.bttn6:
                case R.id.bttn7:
                case R.id.bttn8:
                case R.id.bttn9:
                    String number ;
                    number = ((Button)v).getText().toString();
                    InputNumber(number);
                    break;


            }//switch


        }//onclick
    }//ButtonClickLisner class


    public  void MathFunction(String str)
    {
        NumberBf = Float.parseFloat(Scr.getText().toString());
        operation= str;
        Scr.setText("0");

    }
    public void mathResult()
    {
        float NumAf = Float.parseFloat(Scr.getText().toString());
        double result = 0;
        if (operation.equals("+"))
        {
            result = NumberBf + NumAf;
        }
        Scr.setText(String.valueOf(result));

    }

    public void InputNumber(String num)
    {
        String EnteredValue = Scr.getText().toString();
        if (EnteredValue.equals("0"))
            EnteredValue = "";


        EnteredValue += num;
        Scr.setText(EnteredValue);

    }









}//MainActivity Class
