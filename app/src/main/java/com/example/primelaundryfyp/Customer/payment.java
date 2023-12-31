package com.example.primelaundryfyp.Customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.primelaundryfyp.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;

public class payment extends AppCompatActivity {

    private TextView typeCall, laundryShopCall, pickupDateCall, deliveryDateCall, pickupTimeCall, deliveryTimeCall, subtotalCall, pickupDeliveryFeeCall, taxCall, total;
    private Button proceedPayment;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebasefirestore;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);

        typeCall = findViewById(R.id.typeCall);
        laundryShopCall = findViewById(R.id.laundryShopCall);
        pickupDateCall = findViewById(R.id.pickupDateCall);
        deliveryDateCall = findViewById(R.id.deliveryDateCall);
        pickupTimeCall = findViewById(R.id.pickupTimeCall);
        deliveryTimeCall = findViewById(R.id.deliveryTimeCall);
        subtotalCall = findViewById(R.id.subtotalCall);
        pickupDeliveryFeeCall = findViewById(R.id.pickupDeliveryFeeCall);
        taxCall = findViewById(R.id.taxCall);
        total = findViewById(R.id.total);
        proceedPayment = findViewById(R.id.proceedPayment);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseApp.initializeApp(this);
        firebasefirestore = FirebaseFirestore.getInstance();
        Intent intent = getIntent();
        user = firebaseAuth.getCurrentUser();
        ArrayList<String> booking = intent.getStringArrayListExtra("booking");


        DocumentReference documentReference = firebasefirestore.collection("Users").document(user.getUid());
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                typeCall.setText(documentSnapshot.getString("type"));
                laundryShopCall.setText(documentSnapshot.getString("laundryShop"));
                pickupDateCall.setText(documentSnapshot.getString("PickupDate"));
                deliveryDateCall.setText(documentSnapshot.getString("DeliveryDate"));
                pickupTimeCall.setText(documentSnapshot.getString("PickupTime"));
                deliveryTimeCall.setText(documentSnapshot.getString("DeliveryTime"));
                subtotalCall.setText(documentSnapshot.getString("subTotal"));
                pickupDeliveryFeeCall.setText(documentSnapshot.getString("pickupDeliveryFee"));
                taxCall.setText(documentSnapshot.getString("tax"));
                total.setText(documentSnapshot.getString("total"));
            }
        });

        proceedPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(payment.this, payment2.class);
                intent.putExtra("booking", booking); //recheck semula sebab confuse??
                startActivity(intent);
                proceedPayment();
            }
        });
    }

    private void proceedPayment() {
        Toast.makeText(this, "PROCEED PAYMENT button clicked!", Toast.LENGTH_SHORT).show();
    }
}
