package com.wordpress.sjatyourservice.javajargons;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Question10 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question10);
        TextView tv10 =(TextView) findViewById(R.id.tv10);

        tv10.setText(("  Import java.util.Scanner;\n" +
                "lic class Re_Check_3 {\n" +
                "public static void main(String[] args) {\n" +
                "Scanner sc = new Scanner(System.in);\n" +
                "System.out.println(“The R.E is a+b(a+b)*”);\n" +
                "System.out.println(“Enter the string”);\n" +
                "String str = sc.nextLine();\n" +
                "System.out.println(“The condition for R.E is to have either a or start with b “);\n" +
                "int length = str.length();\n" +
                "if (length == 1) {\n" +
                "if (str.charAt(0) == ‘a’ || str.charAt(0) == ‘b’) {\n" +
                "System.out.println(“RE ACCEPTED”);\n" +
                "} else {\n" +
                "System.out.println(“RE REJECTED”);\n" +
                "}\n" +
                "} else {\n" +
                "for (int i = 1; i < length; i++) {\n" +
                "if ((str.charAt(0) == ‘b’) && (str.charAt(i) == ‘a’ || str.charAt(i) == ‘b’)) {\n" +
                "System.out.println(“RE ACCEPTED”);\n" +
                "break;\n" +
                "} else {\n" +
                "System.out.println(“RE REJECTED”);\n" +
                "break;\n" +
                "}\n" +
                "}\n" +
                "}\n" +
                "}\n" +
                "}\n" +
                "                    OUTPUT\n" +
                "\n" +
                "The R.E is a+b(a+b)*\n" +
                "Enter the string\n" +
                "baab\n" +
                "The condition for R.E is to have either a or start with b\n" +
                "RE ACCEPTED"));
    }
}
