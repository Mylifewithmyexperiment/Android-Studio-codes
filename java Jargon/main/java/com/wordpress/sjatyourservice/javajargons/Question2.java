package com.wordpress.sjatyourservice.javajargons;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Question2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question2);
        TextView tv2 =(TextView) findViewById(R.id.tv2);

        tv2.setText(("public class PalinDromeInJava {\n" +
                "public static void main(String[] args) {\n" +
                "Scanner sc=new Scanner(System.in);\n" +
                "System.out.println(“Palindrome please !!! “);\n" +
                "String input=sc.nextLine();\n" +
                "int len = input.length();\n" +
                "for (int i = 0; i < len; i++)\n" +
                "{\n" +
                "if (input.charAt(i)==input.charAt((len-1)-i))\n" +
                "{\n" +
                "System.out.println(“String is a Palindrome”);\n" +
                "break;\n" +
                "}\n" +
                "else\n" +
                "{\n" +
                "System.out.println(“String is non-Palindrome”);\n" +
                "break;\n" +
                "}\n" +
                "}\n" +
                "}\n" +
                "}\n" +
                "                         OUTPUT\n" +
                "\n" +
                "Palindrome please !!!\n" +
                "madam\n" +
                "String is a Palindrome\" "));


    }
}
