package com.example.coffeeorderingapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //set the static variable of number
    private static int q=0;

    //set the listener for "-" and "+" buttons, once pressing the button, the value of q
    //will be changed, and display the value of quantity
    private View.OnClickListener listener = new View.OnClickListener() {
        public void onClick(View v) {
            Button button = (Button) v;
            TextView quantity = (TextView) viewObj("quantity");

            if(button.getText().equals("-")) q--;
            else q++;

            //q can not below 0
            if (q<0) q=0;
            display(quantity);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //register the listener for minus and plus
        Button minus = (Button) viewObj("minus");
        Button plus = (Button) viewObj("plus");
        minus.setOnClickListener(listener);
        plus.setOnClickListener(listener);

    }

    //the function when pressing the "order" button
    public void submitOrder(View view){
        display();
    }


    //get View by its ID
    private View viewObj(String sID){
        int ID = getResources().getIdentifier(sID, "id", getPackageName());
        return findViewById(ID);
    }

    //show the quantity
    public void display(TextView textView){
        textView.setText("" + q);
    }

    //show the order information
    public void display(){
        TextView detail = (TextView) viewObj("detail");
        detail.setText("Total: " + 5*q + "\nThank you!");
    }
}
