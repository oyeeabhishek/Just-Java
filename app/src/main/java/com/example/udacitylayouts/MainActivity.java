 package com.example.udacitylayouts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

 public class MainActivity extends AppCompatActivity {

    EditText name;
    TextView textView, number, finalText, reset, order;
    Button button1, button2;
    CheckBox chocoOreo, whippedCream;
    public int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        number = findViewById(R.id.number);
        finalText = findViewById(R.id.summaryText);
        reset = findViewById(R.id.reset);
        order = findViewById(R.id.summary);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        whippedCream = findViewById(R.id.whippedCream);
        chocoOreo = findViewById(R.id.chocoOreo);



        display(1);

    }

    public void display(int num){
        if(num<=0){
            number.setText(""+1);
            Toast.makeText(this, "Minimum 1 order is required", Toast.LENGTH_SHORT).show();
        }
        else if (num>10){
            number.setText(""+10);
            Toast.makeText(this, "Maximum 10 orders at a time", Toast.LENGTH_SHORT).show();
        }
        else {
            number.setText("" + num);
        }
    }

    public void increase(View view) {
        int num = Integer.parseInt(number.getText().toString());
        num+=1;
        display(num);
    }

    public void decrease(View view) {
        int num = Integer.parseInt(number.getText().toString());
        num-=1;
        display(num);
    }

    public void reset(View view) {
        number.setText(""+1);
        finalText.setText("Order summary will be displayed here");
    }

    public void summary(View view) {
        int quantity = Integer.parseInt(number.getText().toString());
        String nameText = name.getText().toString();
        boolean hasWhippedCream = whippedCream.isChecked();
        boolean hasChocoOreo = chocoOreo.isChecked();
        int coffeeCost = 5*quantity;
        int whippedCreamCost = 2*quantity;
        int chocoOreoCost = 2*quantity;
        int totalCost = coffeeCost;

        String orderSummary = "Bon appétit "+nameText +"\n";
        orderSummary += "Your order for "+quantity+" Coffee\n";

        if(whippedCream.isChecked()){
            orderSummary += "with Whipped Cream and\n";
            totalCost+=whippedCreamCost;
        }
        else{
            orderSummary += "without Whipped Cream and\n";
        }

        if(chocoOreo.isChecked()){
            orderSummary += "with Choco Oreo\n";
            totalCost+=chocoOreoCost;
        }
        else{
            orderSummary += "without Choco Oreo\n";
        }

        orderSummary += "is successful for\n";
        orderSummary += "$ "+totalCost;



        finalText.setText(orderSummary);


    }

     public void order(View view) {

         String nameText = name.getText().toString();

         Intent intent = new Intent(Intent.ACTION_SEND);
         intent.setData(Uri.parse("mailto:"));
         intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for "+ nameText);
         intent.putExtra(Intent.EXTRA_TEXT, finalText.getText().toString());
         intent.setType("*/*");

         startActivity(Intent.createChooser(intent,"Choose an Email App"));
//         if (intent.resolveActivity(getPackageManager()) != null) {
//             startActivity(intent);
//         }
         }
 }