package com.wordpress.sjatyourservice.javajargons;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Question9 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question9);
        TextView tv9 =(TextView) findViewById(R.id.tv9);

        tv9.setText(("  import java.util.Scanner;\n" +
                "public class RegularExpressionCheck {\n" +
                "public static void main(String[] args) {\n" +
                "Scanner sc = new Scanner(System.in);\n" +
                "System.out.println(“The R.E is (aa)*+(aaa)*”);\n" +
                "System.out.println(“Enter the string”);\n" +
                "String str = sc.nextLine();\n" +
                "System.out.println(“The condition for R.E is to have a in multiple of 2 or 3 or no a and no other symbol “);\n" +
                "int length = str.length();\n" +
                "int flag=0;\n" +
                "System.out.println(“length =” + length);\n" +
                "for (int i = 0; i < length; i++) {\n" +
                "if(str.charAt(i)!=’a’)\n" +
                "{\n" +
                "flag=1;\n" +
                "}\n" +
                "else\n" +
                "{ if(length%2==0||length%3==0||str.isEmpty())\n" +
                "{\n" +
                "flag=2;\n" +
                "}\n" +
                "else\n" +
                "{\n" +
                "flag=1;\n" +
                "}\n" +
                "}\n" +
                "}\n" +
                "if(flag==2||str.contains(“e”)|| str.isEmpty())\n" +
                "{\n" +
                "System.out.println(“RE ACCEPTED”);\n" +
                "}\n" +
                "else\n" +
                "{\n" +
                "System.out.println(“RE REJECTED”);\n" +
                "}\n" +
                "                                                        OUTPUT\n" +
                "\n" +
                "The R.E is (aa)*+(aaa)*\n" +
                "Enter the string\n" +
                "The condition for R.E is to have a in multiple of 2 or 3 or no a and no other symbol\n" +
                "length =0\n" +
                "RE ACCEPTED             //since no char inserted\n" +
                " \n" +
                " \n" +
                "The R.E is (aa)*+(aaa)*\n" +
                "Enter the string\n" +
                "e\n" +
                "The condition for R.E is to have a in multiple of 2 or 3 or no a and no other symbol\n" +
                "length =1\n" +
                "RE ACCEPTED      //e represents epsilline\n" +
                " \n" +
                " \n" +
                "The R.E is (aa)*+(aaa)*\n" +
                "Enter the string\n" +
                "aaaaa\n" +
                "The condition for R.E is to have a in multiple of 2 or 3 or no a and no other symbol\n" +
                "length =5\n" +
                "RE REJECTED"));
    }
}
