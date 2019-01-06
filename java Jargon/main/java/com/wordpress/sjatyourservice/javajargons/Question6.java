package com.wordpress.sjatyourservice.javajargons;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Question6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question6);
        TextView tv6 =(TextView) findViewById(R.id.tv6);

        tv6.setText((" public class Square_of_matrix\n" +
                "{\n" +
                "public static void main(String[] args)\n" +
                "{\n" +
                "int a[][] = {{1, 2}, {2, 3}};\n" +
                "int sum;\n" +
                "for (int i = 0; i < 2; i++)\n" +
                "{\n" +
                "for (int j = 0; j < 2; j++)\n" +
                "{\n" +
                "sum = 0;\n" +
                "for (int k = 0; k < 2; k++)\n" +
                "{\n" +
                "sum = sum + a[i][k] * a[k][j];\n" +
                "}\n" +
                "System.out.println(“square : position of matrix[” + i + “][” + j + “]” + sum);\n" +
                "}\n" +
                "}\n" +
                "}\n" +
                "}\n" +
                "/** documentation comment\n" +
                "* take care while taking value of k try to show in output\n" +
                "* the position in terms of i and j (that gives more visualisation )\n" +
                "*\n" +
                "*/\n" +
                "                               OUTPUT\n" +
                "\n" +
                " \n" +
                "square : position of matrix[0][0]5\n" +
                "square : position of matrix[0][1]8\n" +
                "square : position of matrix[1][0]8\n" +
                "square : position of matrix[1][1]13"));
    }
}
