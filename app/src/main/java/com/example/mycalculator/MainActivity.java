package com.example.mycalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import static android.view.View.OnClickListener;
import android.content.Intent;


public class MainActivity extends AppCompatActivity {
    private double NumberBf;
    private String operation;
    private EditText calculation;
    private ButtonClickListener bttnClick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calculation = (EditText) findViewById(R.id.resultText);
        calculation.setEnabled(false);
        bttnClick = new ButtonClickListener();



        int ViewList[] = {R.id.bttn0, R.id.bttn1, R.id.bttn2, R.id.bttn3, R.id.bttn4, R.id.bttn5,
                R.id.bttn6, R.id.bttn7, R.id.bttn8, R.id.bttn9, R.id.bttnAdd, R.id.bttnSub, R.id.bttnDiv,
                R.id.bttnMult, R.id.bttnEqual, R.id.bttnDec, R.id.bttnClr, R.id.measureScreen, R.id.tipCalc};

        for (int bttn : ViewList)
        {
            View v = (View) findViewById(bttn);

            v.setOnClickListener(bttnClick);
        }
    }//OnCreate



    private class ButtonClickListener implements OnClickListener
    {
        public void onClick (View v)
        {
            switch (v.getId())
            {
                case R.id.bttnClr :
                    calculation.setText("0");
                    NumberBf = 0;
                    operation = "";
                    break;
                case R.id.bttnAdd:
                    MathFunction("+");
                    break;
                case R.id.bttnSub:
                    MathFunction("-");
                    break;
                case R.id.bttnMult:
                    MathFunction("*");
                    break;
                case R.id.bttnDiv:
                    MathFunction("/");
                    break;
                case R.id.bttnEqual:
                    MathResult();
                    break;
                case R.id.measureScreen:
                    /// create a new intent
                    Intent intent = new Intent(MainActivity.this, Measurement.class);
                    startActivity(intent);
                    break;
                case R.id.tipCalc:
                    /// create a new intent
                    Intent intentTip = new Intent(MainActivity.this, TipCalculator.class);
                    startActivity(intentTip);
                    break;
                case R.id.bttnDec:
                    String result = calculation.getText().toString();
                    if (checkForDec() == false)
                    {
                        result += '.';
                    }
                    else
                        break;
                    calculation.setText(result);
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
    }//ButtonClickListener class


    public  void MathFunction(String str)
    {
        NumberBf = Float.parseFloat(calculation.getText().toString());
        operation= str;
        calculation.setText("0");

    }
    public void MathResult() {
        float NumAf = Float.parseFloat(calculation.getText().toString());
        double result = 0;
        switch (operation)
        {
            case "+":
                result = NumberBf + NumAf;
                break;
            case "-":
                result = NumberBf - NumAf;
                break;
            case "*":
                result = NumberBf * NumAf;
                break;
            case "/":
                result = NumberBf / NumAf;
                break;
        }

        calculation.setText(String.valueOf(result));

    }

    public void InputNumber(String num)
    {
        String sum = calculation.getText().toString();
        if (sum.equals("0"))
            sum = "";

        sum += num;
        calculation.setText(sum);

    }

    // only allows one decimal to be used so user can't input num.num.num.num etc.
    public boolean checkForDec() {
        String currentNumString = calculation.getText().toString();
        char[] numCharArray = currentNumString.toCharArray();

        for (char i : numCharArray)
        {
            if (i == '.')
            {
                return true;
            }
        }
        return false;
    }








}//MainActivity Class
