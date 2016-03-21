package com.example.android.justjava;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    String name = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void incrementQuantity(View view) {
        quantity += 1;
        displayQuantity(quantity);
    }

    public void decrementQuantity(View view){
        if(quantity!=0){
            quantity -=1;
            displayQuantity(quantity);
        }
        displayQuantity(quantity);
    }


    public void submitOrder(View view){

        // If user wants whipped cream
        CheckBox whippedCreamCheckBox = (CheckBox)  findViewById(R.id.whipped_cream_checkbox);
        boolean needCream = whippedCreamCheckBox.isChecked();

        // If user wants chocolate
        CheckBox chocolateCheckBox = (CheckBox)  findViewById(R.id.chocolate_checkbox);
        boolean chocolate = chocolateCheckBox.isChecked();

        // Calculate the final price
        int price = calculatePrice(needCream,chocolate);

        String priceMessage =createOrderSummary(price, needCream,chocolate);

        displayMessage(priceMessage);
    }

    public String createOrderSummary(int price, boolean whippedCream, boolean chocolate){

        // Find user's name
        EditText nameText = (EditText) findViewById(R.id.personName);
        name = nameText.getText().toString();

        String priceMessage = "Name :"+name;
        priceMessage += "\nQuantity :"+quantity;
        priceMessage += "\n Added wipped cream ? "+whippedCream;
        priceMessage += "\n Added chocolate ? "+chocolate;
        priceMessage +="\nTotal: $"+price;
        priceMessage += "\nThank  you";

        return priceMessage;
    }

    public void displayMessage(String message){
        TextView priceText = (TextView) findViewById(R.id.order_summary_view);
        priceText.setText(message);
    }

    public void displayQuantity(int value) {

        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(""+value);
    }

    public int calculatePrice(boolean whippedCream, boolean chocolate){
        int price =5;

        if(whippedCream)
            price += 1;

        if(chocolate)
            price +=2;

        return quantity * price;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
