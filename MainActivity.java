package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView display;
    double firstNumber = 0;
    String operator = "";
    boolean isNew = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        int[] numberIds = {
            R.id.btn_0,
            R.id.btn_1,
            R.id.btn_2,
            R.id.btn_3,
            R.id.btn_4,
            R.id.btn_5,
            R.id.btn_6,
            R.id.btn_7,
            R.id.btn_8,
            R.id.btn_9,
            R.id.btn_dot
        };

        int[] opIds = {
            R.id.btn_add,
            R.id.btn_subtract,
            R.id.btn_multiply,
            R.id.btn_divide
        };

        View.OnClickListener numberListener = v -> {

            Button b = (Button) v;
            String text = b.getText().toString();
            String current = display.getText().toString();

            if (isNew) {
                display.setText("");
                isNew = false;
                current = "";
            }

            if (text.equals(".") && current.contains(".")) {
                return;
            }

            if (current.equals("0") && !text.equals(".")) {
                display.setText(text);
            } else {
                display.append(text);
            }
        };

        for (int id : numberIds) {
            findViewById(id).setOnClickListener(numberListener);
        }

        View.OnClickListener opListener = v -> {

            Button b = (Button) v;

            try {
                firstNumber =
                        Double.parseDouble(display.getText().toString());

                operator = b.getText().toString();

                isNew = true;

            } catch (Exception e) {

                Toast.makeText(
                        this,
                        "Enter number first",
                        Toast.LENGTH_SHORT
                ).show();
            }
        };

        for (int id : opIds) {
            findViewById(id).setOnClickListener(opListener);
        }

        findViewById(R.id.btn_equals).setOnClickListener(v -> {

            try {

                double secondNumber =
                        Double.parseDouble(display.getText().toString());

                double result = 0;

                switch (operator) {

                    case "+":

                        result = firstNumber + secondNumber;

                        break;

                    case "-":

                        result = firstNumber - secondNumber;

                        break;

                    case "*":

                        result = firstNumber * secondNumber;

                        break;

                    case "/":

                        if (secondNumber == 0) {

                            Toast.makeText(
                                    this,
                                    "Cannot divide by zero",
                                    Toast.LENGTH_LONG
                            ).show();

                            return;
                        }

                        result = firstNumber / secondNumber;

                        break;

                    default:

                        return;
                }

                if (result == (int) result) {

                    display.setText(
                            String.valueOf((int) result)
                    );

                } else {

                    display.setText(
                            String.valueOf(result)
                    );
                }

                isNew = true;

            } catch (Exception e) {

                Toast.makeText(
                        this,
                        "Error",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        findViewById(R.id.btn_clear).setOnClickListener(v -> {

            display.setText("0");

            firstNumber = 0;

            operator = "";

            isNew = true;
        });

        findViewById(R.id.btn_del).setOnClickListener(v -> {

            String s = display.getText().toString();

            if (s.length() > 1) {

                display.setText(
                        s.substring(0, s.length() - 1)
                );

            } else {

                display.setText("0");

                isNew = true;
            }
        });

    }

}
