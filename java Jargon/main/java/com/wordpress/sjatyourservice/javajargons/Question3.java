package com.wordpress.sjatyourservice.javajargons;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Question3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question3);
        TextView tv3=(TextView) findViewById(R.id.tv3);

        tv3.setText(("     FACTORIAL OF ANY NUMBER     " +
                "import java.util.Scanner;\n" +
                "public class factorial_of_any_no\n" +
                "{\n" +
                "public static void main(String args[])\n" +
                "{\n" +
                "int fa=1;\n" +
                "Scanner sc =new Scanner(System.in);\n" +
                "System.out.println(“enter the no”);\n" +
                "int n=sc.nextInt();\n" +
                "if(n<0)\n" +
                "System.out.println(“not defined”);\n" +
                "else\n" +
                "{\n" +
                "for(int i=1;i<=n;i++)\n" +
                "{\n" +
                "fa=fa*i;\n" +
                "}\n" +
                "System.out.println(fa);\n" +
                "}\n" +
                "}\n" +
                "}\n" +
                "                                                        OUTPUT\n" +
                "\n" +
                "\n" +
                "enter number of terms to get its factorial\n" +
                "5\n" +
                "The factorial is 120 \n" +
                "\n "));
    }
}
