package com.ruslan.kvadratnoe_uravnenie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    /* Глобальные переменные для доступа к компонентам окна */
    public EditText editText_a, editText_b, editText_c;
    public TextView textView_x1, textView_x2, textView_diskriminant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /* Инициализация переменных доступа к компонентам окна */
        editText_a = findViewById(R.id.editText_a);
        editText_b = findViewById(R.id.editText_b);
        editText_c = findViewById(R.id.editText_c);
        textView_x1 = findViewById(R.id.textView_x1);
        textView_x2 = findViewById(R.id.textView_x2);
        textView_diskriminant = findViewById(R.id.textView_diskriminant);
    }

    public void onCalc(View view) {
        // Локальные переменные
        double a, b, c, d, x1, x2;
        try {
            // Считывание введённых входных значений в переменные
            a = Double.parseDouble(editText_a.getText().toString());
            b = Double.parseDouble(editText_b.getText().toString());
            c = Double.parseDouble(editText_c.getText().toString());
        } catch (Exception e) {
            // Выдача всплывающего сообщения на экран об ошибке
            Toast toast = Toast.makeText(MainActivity.this, "Неверные входные данные!", Toast.LENGTH_LONG);
            toast.show();
            return;
        }

        // Расчёт дискриминанта
        d = (b * b) - (4 * a * c);
        textView_diskriminant.setText(String.format("D = %.3f", d));
        // Расчёт значений x1 и x2
        try {
            x1 = (-b + Math.sqrt(d)) / (2.0 * a);
            x2 = (-b - Math.sqrt(d)) / (2.0 * a);

            if (Double.isNaN(x1) || Double.isNaN(x2) || Double.isInfinite(x1) || Double.isInfinite(x2)) {
                throw new Exception();
            }
            if (x1 == -0) x1 = Math.abs(x1);
            if (x2 == -0) x2 = Math.abs(x2);

            textView_x1.setText(String.format("x1 = %.3f", x1));
            textView_x2.setText(String.format("x2 = %.3f", x2));
        } catch (Exception e) {
            String err = "Нет решения!";
            textView_x1.setText(err);
            textView_x2.setText(err);
        }
    }

    public void onClick_erase(View view) {
        editText_a.getText().clear();
        editText_b.getText().clear();
        editText_c.getText().clear();
    }
}
