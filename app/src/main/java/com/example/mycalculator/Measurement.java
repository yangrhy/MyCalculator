package com.example.mycalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Measurement extends AppCompatActivity
{
    private double NumberBf;
    private EditText calculation;
    private ButtonClickListener bttnClick;
    private RadioGroup radioGroupUnit1;
    private RadioGroup radioGroupUnit2;
    private RadioButton radioButtonUnit1;
    private RadioButton radioButtonUnit2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measurement);
        calculation = (EditText) findViewById(R.id.resultText);
        calculation.setEnabled(false);
        bttnClick = new ButtonClickListener();


        int numButtonArray[] = {R.id.bttn0, R.id.bttn1, R.id.bttn2, R.id.bttn3, R.id.bttn4, R.id.bttn5,
                R.id.bttn6, R.id.bttn7, R.id.bttn8, R.id.bttn9, R.id.bttnConvert, R.id.bttnDec,
                R.id.bttnClr, R.id.calcScreen, R.id.tipCalc};

        for (int bttn : numButtonArray)
        {
            View v = (View) findViewById(bttn);

            v.setOnClickListener(bttnClick);
        }
    }

    private class ButtonClickListener implements OnClickListener
    {
        public void onClick (View v)
        {
            switch (v.getId())
            {
                case R.id.bttnClr :
                    calculation.setText("0");
                    NumberBf = 0;
                    break;
                case R.id.bttnConvert:
                    Convert();
                    break;
                case R.id.calcScreen:
                    /// create a new intent
                    Intent intent = new Intent(Measurement.this, MainActivity.class);
                    startActivity(intent);
                    break;
                case R.id.tipCalc:
                    /// create a new intent
                    Intent intentTip = new Intent(Measurement.this, TipCalculator.class);
                    startActivity(intentTip);
                    break;
                case R.id.bttnDec:
                    String result = calculation.getText().toString();
                    if (!checkForDec())
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
            }
        }
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
    public boolean checkForDec()
    {
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

    public void Convert ()
    {
        radioGroupUnit1 = (RadioGroup)findViewById(R.id.radioGroup1);
        radioGroupUnit2 = (RadioGroup)findViewById(R.id.radioGroup2);
        int selectedId1 = radioGroupUnit1.getCheckedRadioButtonId();
        int selectedId2 = radioGroupUnit2.getCheckedRadioButtonId();

        radioButtonUnit1 = (RadioButton) findViewById(selectedId1);
        radioButtonUnit2 = (RadioButton) findViewById(selectedId2);

        String rbId1 = radioButtonUnit1.getText().toString();
        String rbId2 = radioButtonUnit2.getText().toString();

        if ((rbId1.equals("Feet")) && (rbId2.equals("Inches")))
        {
            FeetToInches();
        }
        else if ((rbId1.equals("Feet")) && (rbId2.equals("Centimeters")))
        {
            FeetToCm();
        }
        else if ((rbId1.equals("Inches")) && (rbId2.equals("Feet")))
        {
            InchesToFeet();
        }
        else if ((rbId1.equals("Inches")) && (rbId2.equals("Centimeters")))
        {
            InchesToCm();
        }
        else if ((rbId1.equals("Centimeters")) && (rbId2.equals("Feet")))
        {
            CmToFeet();
        }
        else if ((rbId1.equals("Centimeters")) && (rbId2.equals("Inches")))
        {
            CmToInches();
        }
        else
        {
            Toast.makeText(Measurement.this, "You selected the same units", Toast.LENGTH_LONG).show();
        }

    }

    public void FeetToInches ()
    {
        float NumAf = Float.parseFloat(calculation.getText().toString());

        NumAf *= 12;
        calculation.setText(String.valueOf(NumAf));
    }

    public void FeetToCm ()
    {
        float NumAf = Float.parseFloat(calculation.getText().toString());

        NumAf *= 30.48;
        calculation.setText(String.valueOf(NumAf));
    }

    public void InchesToFeet ()
    {
        float NumAf = Float.parseFloat(calculation.getText().toString());

        NumAf /= 12;
        calculation.setText(String.valueOf(NumAf));
    }

    public void InchesToCm ()
    {
        float NumAf = Float.parseFloat(calculation.getText().toString());

        NumAf *= 2.54;
        calculation.setText(String.valueOf(NumAf));
    }

    public void CmToFeet ()
    {
        float NumAf = Float.parseFloat(calculation.getText().toString());

        NumAf /= 30.48;
        calculation.setText(String.valueOf(NumAf));
    }

    public void CmToInches ()
    {
        float NumAf = Float.parseFloat(calculation.getText().toString());

        NumAf /= 2.54;
        calculation.setText(String.valueOf(NumAf));
    }
}
