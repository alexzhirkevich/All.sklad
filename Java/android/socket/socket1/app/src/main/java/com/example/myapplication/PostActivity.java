package com.example.myapplication;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import application.client.Client;
import application.message.order.MessageOrder;
import application.message.order.MessageOrderResult;


public class PostActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
    }

    public void sendMessage(View view) {
        try {

            EditText address = findViewById(R.id.address);
            EditText order = findViewById(R.id.order);

            String sAddress = address.getText().toString().trim();
            String sOrder = order.getText().toString().trim();

            if (sAddress == ""){
                new AlertActivity("Введите адрес").show(getSupportFragmentManager(),"Error");
                return;
            }

            if (sOrder == ""){
                new AlertActivity("Введите заказ").show(getSupportFragmentManager(),"Error");
                return;
            }

            new Thread( () -> {
                try {
                    String sOrderEdited = sOrder.replaceAll("\n\n","\n").replaceAll("\n",",");
                    MessageOrderResult mor = (MessageOrderResult)MainActivity.client.sendMessage(
                            new MessageOrder(sAddress,sOrderEdited));
                    if (!mor.checkError())
                        new AlertActivity(Client.sOrderNumber+ mor.getNumber()).show(getSupportFragmentManager(),"Error");
                    else
                        new AlertActivity(Client.sResponseError).show(getSupportFragmentManager(),"Error");
                }
                catch (Exception e) {}
            }).start();


            address.setText("");
            order.setText("");


        } catch (Exception e){
        }
    }

}