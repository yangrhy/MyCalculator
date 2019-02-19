package com.example.mycalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import static android.view.View.OnClickListener;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    private EditText result;
    private ButtonClickListener getBttnClick;
    private double numBuffer; // store num that is entered
    private String operation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result = (EditText)findViewById(R.id.resultText);
        result.setEnabled(false);
        getBttnClick = new ButtonClickListener();

        int viewList[] = {R.id.bttn0, R.id.bttn1, R.id.bttn2, R.id.bttn3, R.id.bttn4,
                R.id.bttn5, R.id.bttn6, R.id.bttn7,R.id.bttn8, R.id.bttn9, R.id.bttnDec,
                R.id.bttnAdd, R.id.bttnSub, R.id.bttnMult, R.id.bttnDiv, R.id.bttnClr,
                R.id.measureScreen};

        for (int bttn : viewList) {
            View v = (View) findViewById(bttn);

            v.setOnClickListener(getBttnClick);
        }

    }

    private class ButtonClickListener implements OnClickListener {

        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.measureScreen:
                    // create an intent for the measurement class
                    Intent intent = new Intent(MainActivity.this, Measurement.class);
                    startActivity(intent);
                    break;
                case R.id.bttnClr:
                    result.setText("0");
                    numBuffer = 0;
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

    private void MathFunction (String str) {
        numBuffer = Float.parseFloat(result.getText().toString());
        operation = str;
        result.setText("0");

    }

    private void MathResult () {
        float numAF = Float.parseFloat(result.getText().toString());
        double sum = 0;
        if (operation.equals("+")) {
            sum = numBuffer + numAF;
        }
        result.setText(String.valueOf(sum));

    }

    private void InputNumber (String num) {
        String value = result.getText().toString();
        if (value.equals("0")) {
            value = "";
        }

        value += num;
        result.setText(value);
    }
}
