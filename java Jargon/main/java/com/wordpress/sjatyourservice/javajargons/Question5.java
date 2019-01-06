package com.wordpress.sjatyourservice.javajargons;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Question5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question5);

        TextView tv5 =(TextView) findViewById(R.id.tv5);

        tv5.setText((" import java.util.Scanner;\n" +
                "public class PrimeNoBtwnAnyTwoNo {\n" +
                "void prime_no_cll()\n" +
                "{\n" +
                "Scanner sc = new Scanner(System.in);\n" +
                "System.out.println(“Enter 1st no”);\n" +
                "int n1 = sc.nextInt();\n" +
                "System.out.println(“Enter 2nd no”);\n" +
                "int n2 = sc.nextInt();\n" +
                "int rem = 0;\n" +
                "if(n1>n2)                                         //swapping of digit in cace of descending order user input\n" +
                "{\n" +
                "int valuestorebox=0;\n" +
                "valuestorebox=n2; \n" +
                "n2=n1;\n" +
                "n1=valuestorebox;\n" +
                "System.out.println(” now n1 is : “+ n1 +”\\n n2 is : “+ n2+” \\nand temp value store box is : “+ valuestorebox);\n" +
                "}\n" +
                "for (int i = n1; i <= n2; i++) \n" +
                "{\n" +
                "int count = 0;\n" +
                "for (int j = 2; j < i; j++)\n" +
                "{\n" +
                "rem = (i % j);\n" +
                "if (rem == 0)\n" +
                "count = 1;\n" +
                "}\n" +
                "if (count == 0) \n" +
                "System.out.println(“nmbr is prime” + i);\n" +
                "if (count == 1) \n" +
                "System.out.println(“nmbr. is non-prime” + i);\n" +
                "}\n" +
                "}\n" +
                "public static void main(String[] args)\n" +
                "{\n" +
                "PrimeNoBtwnAnyTwoNo ob = new PrimeNoBtwnAnyTwoNo();\n" +
                "ob.prime_no_cll();\n" +
                "}\n" +
                "}\n" +
                "/** documentation comment\n" +
                "* 1st : check that you have to run loop from n1 to n2 \n" +
                "* for prime number loop should run one less than the i\n" +
                "* caution : counter to be declare after for loop because we have to reset it every time\n" +
                "* if user enter larger number before for that apply if condition to swap the digit before only \n" +
                "*/\n" +
                " \n" +
                "                 OUTPUT\n" +
                "\n" +
                "Enter 1st no\n" +
                "2\n" +
                "Enter 2nd no\n" +
                "11\n" +
                "nmbr is prime2\n" +
                "nmbr is prime3\n" +
                "nmbr. is non-prime4\n" +
                "nmbr is prime5\n" +
                "nmbr. is non-prime6\n" +
                "nmbr is prime7\n" +
                "nmbr. is non-prime8\n" +
                "nmbr. is non-prime9\n" +
                "nmbr. is non-prime10\n" +
                "nmbr is prime11 "));
    }
}
