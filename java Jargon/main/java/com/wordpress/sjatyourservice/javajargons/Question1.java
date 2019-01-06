package com.wordpress.sjatyourservice.javajargons;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Question1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question1);

        TextView tv1 =(TextView) findViewById(R.id.tv1);

        tv1.setText(("  Fibbonacci_No_Generation\n" +
                "package fibbonacci_no_generation;\n" +
                "import java.util.Scanner;\n" +
                "public class Fibbonacci_No_Generation {\n" +
                "public static void main(String[] args) {\n" +
                "int n1=0,n2=1,n3;\n" +
                "Scanner sc=new Scanner(System.in);\n" +
                "System.out.println(“enter number of terms to get fibonacci series”);\n" +
                "int count=sc.nextInt();\n" +
                "System.out.print(“The numbers are “+n1 +”,”+n2);\n" +
                "for (int i = 2 i count  )<br> {\n" +
                "n3=n1+n2;\n" +
                "System.out.print(“,”+n3);\n" +
                "n1=n2;\n" +
                "n2=n3;\n" +
                "}\n" +
                "}\n" +
                "}\n" +
                "                                                        OUTPUT\n" +
                "\n" +
                "\n" +
                "enter number of terms to get fibonacci series\n" +
                "5\n" +
                "The numbers are 0,1,1,2,3  "));



    }
}
