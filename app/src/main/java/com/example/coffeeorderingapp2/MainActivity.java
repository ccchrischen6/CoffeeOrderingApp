package com.example.coffeeorderingapp2;

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

    //set the static variable of number
    private static int q = 0;

    //set the listener for "-" and "+" buttons, once pressing the button, the value of q
    //will be changed, and display the value of quantity
    private View.OnClickListener buttonListener = new View.OnClickListener() {
        public void onClick(View v) {
            Button button = (Button) v;
            TextView quantity = (TextView) viewObj("quantity");

            if (button.getText().equals("-")) q--;
            else q++;

            //q can not below 0
            if (q < 1) {
                q = 1;
                Toast.makeText(MainActivity.this, R.string.toast_1, Toast.LENGTH_LONG).show();
            }
            if (q > 99) {
                q = 99;
                Toast.makeText(MainActivity.this, R.string.toast_1, Toast.LENGTH_LONG).show();
            }
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
        minus.setOnClickListener(buttonListener);
        plus.setOnClickListener(buttonListener);

    }

    //the function when pressing the "order" button
    public void submitOrder(View view) {
        composeEmail("", "Coffee Chirs Oder for ");
    }


    //get View by its ID
    private View viewObj(String sID) {
        int ID = getResources().getIdentifier(sID, "id", getPackageName());
        return findViewById(ID);
    }

    //show the quantity
    private void display(TextView textView) {
        textView.setText("" + q);
    }

    //show the order information
//    private void display() {
//        TextView detail = (TextView) viewObj("detail");
//        EditText editText = (EditText) viewObj("textInput");
//        boolean addCream = ((CheckBox) viewObj("cream")).isChecked();
//        boolean addChocolate = ((CheckBox) viewObj("chocolate")).isChecked();
//
//        detail.setText(
//                "Name: " + editText.getText().toString() +
//                        "\nAdd whipped cream? " + addCream +
//                        "\nAdd chocolate? " + addChocolate +
//                        "\nQuantity: " + q +
//                        "\nTotal: $" + q * (5 + (addCream ? 1 : 0) + (addChocolate ? 2 : 0)) +
//                        "\nThank you!");
//    }

    private void composeEmail(String addresses, String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
//        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject + infoGenerator()[0]);
        intent.putExtra(Intent.EXTRA_TEXT, infoGenerator()[1]);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private String[] infoGenerator() {
        EditText editText = (EditText) viewObj("textInput");
        boolean addCream = ((CheckBox) viewObj("cream")).isChecked();
        boolean addChocolate = ((CheckBox) viewObj("chocolate")).isChecked();
        String cusName = editText.getText().toString();
        String[] info = new String[2];
        info[0] = cusName;
        info[1] = ("Name: " + cusName +
                "\nAdd whipped cream? " + addCream +
                "\nAdd chocolate? " + addChocolate +
                "\nQuantity: " + q +
                "\nTotal: $" + q * (5 + (addCream ? 1 : 0) + (addChocolate ? 2 : 0)) +
                "\nThank you!");
        return info;
    }
}
