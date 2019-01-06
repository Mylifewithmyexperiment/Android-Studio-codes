package com.wordpress.sjatyourservice.javajargons;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Question8 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question8);
        TextView tv8 =(TextView) findViewById(R.id.tv8);

        tv8.setText(("Armstrong :- a  number whose cubic sum of individual digit is equal to same number\n" +
                "package sum.of.digit;\n" +
                "import java.util.Scanner;\n" +
                "public class SumOfDigit {\n" +
                "void no_of_digit() {\n" +
                "Scanner sc = new Scanner(System.in);\n" +
                "System.out.println(“enter number to know number of digit”);\n" +
                "int no = sc.nextInt();\n" +
                "int nocount = 0;\n" +
                "while (no > 0) {\n" +
                "no = no / 10;\n" +
                "nocount++;\n" +
                "}\n" +
                "System.out.println(“no of digit ” + nocount);\n" +
                "}\n" +
                "/**documentation comment\n" +
                "*here nocount is initialized to count no of digit after each division by 10 \n" +
                "* until number is greater than zero the while loop will continue\n" +
                "* at last when single digit left then further division will result decimal value(0.2 or 0.3) which is lees than zero so loop will fail\n" +
                "* and we will get the count of all digit till single digit left.\n" +
                "*/\n" +
                "void sum_of_digit() {\n" +
                "Scanner sc = new Scanner(System.in);\n" +
                "System.out.println(“enter no to know sum of digit”);\n" +
                "int no = sc.nextInt();\n" +
                "int sum = 0;\n" +
                "while (no > 0)\n" +
                "{\n" +
                "sum = sum + (no % 10);\n" +
                "no = no / 10;\n" +
                "}\n" +
                "System.out.println(“the sum of digit is :” + sum);\n" +
                "}\n" +
                "/** documentation comment\n" +
                "* similarly here, we just add the remainder to sum box \n" +
                "*/\n" +
                "void armstrong_no_check() {\n" +
                "Scanner sc = new Scanner(System.in);\n" +
                "System.out.println(“enter no to check for armstrong no”);\n" +
                "int no = sc.nextInt();\n" +
                "int k = no;\n" +
                "int cubesum = 0;\n" +
                "int rem = 1;\n" +
                "while (no > 0)\n" +
                "{\n" +
                "rem = no % 10;\n" +
                "cubesum = cubesum + rem * rem * rem;\n" +
                "no = no / 10;\n" +
                "}\n" +
                "System.out.println(“the cubesum of digit is :” + cubesum);\n" +
                "if (k == cubesum)\n" +
                "{\n" +
                "System.out.println(“the entered number is an armstrong no”);\n" +
                "}\n" +
                "else\n" +
                "{\n" +
                "System.out.println(“the entered no is not an armstrong no”);\n" +
                "}\n" +
                "}\n" +
                "/**documentation comment\n" +
                "* here instead of adding each digit to sum box, we add their cube of remainder to cubesum box\n" +
                "* finally we compare that with no \n" +
                "* caution we need to re-declear the no. at initial point because after while loop the number is no more the same no.\n" +
                "*/\n" +
                "public static void main(String[] args)\n" +
                "{\n" +
                "SumOfDigit ob = new SumOfDigit();\n" +
                "ob.no_of_digit();                                                //evaluating number of digit\n" +
                "ob.sum_of_digit();                                           //calculating sum of digit\n" +
                "ob.armstrong_no_check();                           //testing of armstrong no\n" +
                "}\n" +
                "}\n" +
                " \n" +
                "                                    OUTPUT\n" +
                "\n" +
                "enter number to know number of digit\n" +
                "153\n" +
                "no of digit 3\n" +
                "enter no to know sum of digit\n" +
                "153\n" +
                "the sum of digit is :9\n" +
                "enter no to check for armstrong no\n" +
                "153\n" +
                "the cubesum of digit is :153\n" +
                "the entered number is an armstrong no\n" +
                "  "));
    }
}
