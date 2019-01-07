package com.wordpress.sjatyourservice.studentdatabase;


import android.app.Activity;
import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class StudentBriefcase extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_briefcase);
        
        // Create object of DBAdapter
        final DBAdapter db = new DBAdapter(this);
        
        Button btnSubmit = (Button) findViewById(R.id.btn1Submit);
        
        // Submit Button Code
        btnSubmit.setOnClickListener(new OnClickListener() 
        {
			
			public void onClick(View v) 
			{
				EditText et1 = (EditText)findViewById(R.id.et1);
				EditText et2 = (EditText)findViewById(R.id.et2);
				EditText et3 = (EditText)findViewById(R.id.et3);
				EditText et4 = (EditText)findViewById(R.id.et4);
				EditText et5 = (EditText)findViewById(R.id.et5);
				EditText et6 = (EditText)findViewById(R.id.et6);
				EditText et7 = (EditText)findViewById(R.id.et7);
				EditText et8 = (EditText)findViewById(R.id.et8);
				
				String str1 , str2 , str3 , str4 , str5 , str6 , str7 , str8 ;
				
				str1= et1.getText().toString();
				str2= et2.getText().toString();
				str3= et3.getText().toString();
				str4= et4.getText().toString();
				str5= et5.getText().toString();
				str6= et6.getText().toString();
				str7= et7.getText().toString();
				str8= et8.getText().toString();
				
				// Open Database, insert data, close database
				db.open();
				
				long id = db.insertContact(str1, str2, str3, str4, str5, str6, str7, str8);
				db.close();
			}
		});
        
        // Show Button code
        Button btn2 = (Button) findViewById(R.id.btn2Show);
        btn2.setOnClickListener(new OnClickListener()
        {
			
			public void onClick(View v) 
			{
				db.open();
				Cursor c1 = db.getall();
				// always move cursor to first location
				if (c1.moveToFirst())
				{
					do{
						Toast.makeText(getApplicationContext(), c1.getString(0)+c1.getString(1)+c1.getString(2)+c1.getString(3)+c1.getString(4)+c1.getString(5)+c1.getString(6)+c1.getString(7),Toast.LENGTH_SHORT).show();
					}while (c1.moveToNext());
				}
				
			}
		});
        
//        Code for Delete button
        Button btn4 = (Button) findViewById(R.id.btn4Delete);
        btn4.setOnClickListener(new OnClickListener()
        {
			
			public void onClick(View v) 
			{
				final Dialog dialog = new Dialog(StudentBriefcase.this);
				
				dialog.setContentView(R.layout.dialogone);
				dialog.setTitle("Delete");
				dialog.setCancelable(true);
				
				EditText  det1 = (EditText) dialog.findViewById(R.id.det1);
				det1.setText(det1.getText().toString());
				
				ImageView dimg1 = (ImageView) dialog.findViewById(R.id.dimg1);
				
				dimg1.setImageResource(R.drawable.ic_launcher);
				Button close = (Button) dialog.findViewById(R.id.close);
				dialog.show();
				 close.setOnClickListener(new OnClickListener() 
				 {
						public void onClick(View v) 
						{
							dialog.cancel();
						}
				});
		
				 Button subm = (Button) dialog.findViewById(R.id.subm);
				 subm.setOnClickListener(new OnClickListener() 
				 {
					
					public void onClick(View v) 
					{
						
						EditText det1 = (EditText)dialog.findViewById(R.id.det1);
						String st = det1.getText().toString();
						db.open();
						Boolean b = db.deleteContact(st);	// deletes data/record
						 if (b==false)
						 {
							 Toast.makeText(getApplicationContext(), "Item deleted", Toast.LENGTH_SHORT).show();
						 }
						 else
						 {
							 Toast.makeText(getApplicationContext(), "Item not found", Toast.LENGTH_SHORT).show();
						 }
						db.close();
					}
						
					
				});
			
			}
		});
        
//        Delete all
        Button btn5 = (Button) findViewById(R.id.btn5DelAll);
        btn5.setOnClickListener(new OnClickListener() 
        {
        	public void onClick(View v) 
			{	
        		// Deletes all 
        		db.open();
				Boolean b = db.deleteAllContact();	// deletes all data/records
				 if (b==false)
				 {
					 Toast.makeText(getApplicationContext(), "All Items deleted", Toast.LENGTH_SHORT).show();
				 }
				 else
				 {
					 Toast.makeText(getApplicationContext(), "Could not delete all items", Toast.LENGTH_SHORT).show();
				 }
				db.close();
				
			}
		});
        
//        View one or Search
       	Button btnViewOne = (Button)findViewById(R.id.btn3ViewOne);
       	btnViewOne.setOnClickListener(new OnClickListener() 
       	{
		
				public void onClick(View v) 
				{
					final Dialog dialog = new Dialog(StudentBriefcase.this);
					dialog.setContentView(R.layout.dialogone);
					dialog.setTitle("Search");
					dialog.setCancelable(true);
					EditText  det1 = (EditText) dialog.findViewById(R.id.det1);
					det1.setText(det1.getText().toString());
					ImageView dimg1 = (ImageView) dialog.findViewById(R.id.dimg1);
					dimg1.setImageResource(R.drawable.ic_launcher);
					Button close = (Button) dialog.findViewById(R.id.close);
					dialog.show();
					 close.setOnClickListener(new OnClickListener() 
					 {
							public void onClick(View v) 
							{
								dialog.cancel();
							}
					});
					 Button subm = (Button) dialog.findViewById(R.id.subm);
					 subm.setOnClickListener(new OnClickListener() 
					 {
						public void onClick(View v) 
						{
							EditText et1 = (EditText)findViewById(R.id.et1);
							EditText et2 = (EditText)findViewById(R.id.et2);
							EditText et3 = (EditText)findViewById(R.id.et3);
							EditText et4 = (EditText)findViewById(R.id.et4);
							EditText et5 = (EditText)findViewById(R.id.et5);
							EditText et6 = (EditText)findViewById(R.id.et6);
							EditText et7 = (EditText)findViewById(R.id.et7);
							EditText et8 = (EditText)findViewById(R.id.et8);
							EditText det1 = (EditText)dialog.findViewById(R.id.det1);
							String st = det1.getText().toString();
							db.open();
							dialog.cancel();
							Cursor c1 = db.getContact(st);
							
							if (c1 != null)
							{
							et1.setText(c1.getString(0));
							et2.setText(c1.getString(1));
							et3.setText(c1.getString(2));
							et4.setText(c1.getString(3));
							et5.setText(c1.getString(4));
							et6.setText(c1.getString(5));
							et7.setText(c1.getString(6));
							et8.setText(c1.getString(7));
							}
							
							db.close();
						}
							
					});
			}
		});
		
		  Button btnUpdate = (Button) findViewById(R.id.btn6Update);
		  btnUpdate.setOnClickListener(new OnClickListener() 
		  {
			public void onClick(View v) 
			{
				EditText et1 = (EditText)findViewById(R.id.et1);
				EditText et2 = (EditText)findViewById(R.id.et2);
				EditText et3 = (EditText)findViewById(R.id.et3);
				EditText et4 = (EditText)findViewById(R.id.et4);
				EditText et5 = (EditText)findViewById(R.id.et5);
				EditText et6 = (EditText)findViewById(R.id.et6);
				EditText et7 = (EditText)findViewById(R.id.et7);
				EditText et8 = (EditText)findViewById(R.id.et8);
				String str1 , str2 , str3 , str4 , str5 , str6 , str7 , str8 ;
				str1= et1.getText().toString();
				str2= et2.getText().toString();
				str3= et3.getText().toString();
				str4= et4.getText().toString();
				str5= et5.getText().toString();
				str6= et6.getText().toString();
				str7= et7.getText().toString();
				str8= et8.getText().toString();
				db.open();
				
			double id=	db.UpdateContact(str1, str2, str3, str4, str5, str6, str7, str8);
				db.close();	
			}
		});
    }
}


