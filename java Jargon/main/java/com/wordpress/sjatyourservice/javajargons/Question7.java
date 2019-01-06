package com.wordpress.sjatyourservice.javajargons;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Question7 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question7);
        TextView tv7 =(TextView) findViewById(R.id.tv7);

        tv7.setText((" import java.util.Scanner;\n" +
                "lic class Floyd_Triangle {\n" +
                "public static void main(String[] args) {\n" +
                "Scanner sc = new Scanner(System.in);\n" +
                "System.out.println(“Enter no of row “);\n" +
                "int n = sc.nextInt();\n" +
                "System.out.println(“The floyd triangle is :”);\n" +
                "int a = 1;\n" +
                "for (int i = 1; i <= n; i++) {\n" +
                "for (int j = 1; j <= i; j++) {\n" +
                "System.out.print(a++);\n" +
                "}\n" +
                "System.out.println(“”);\n" +
                "}\n" +
                "}\n" +
                "}\n" +
                "/** DOCUMENTATION COMMENT\n" +
                "*Floyd triangle\n" +
                "* right angle triangle which is filled consecutive natural number starting with 1\n" +
                "* here we enter row no \n" +
                "* and the series is generated accordingly\n" +
                "* e.g  1\n" +
                "*         2 3 \n" +
                "*         4 5 6\n" +
                "* and so on …\n" +
                "*/\n" +
                " \n" +
                "                                       OUTPUT\n" +
                "\n" +
                "Enter no of row\n" +
                "4\n" +
                "The floyd triangle is :\n" +
                "1\n" +
                "23\n" +
                "456\n" +
                "78910"));
    }
}
