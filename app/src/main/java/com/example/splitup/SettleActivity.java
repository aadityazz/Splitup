package com.example.splitup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SettleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settle);

        LinearLayout linearLayoutContainer = findViewById(R.id.linearLayoutContainer);

        LayoutInflater inflater = LayoutInflater.from(this);

        int settles = getAllSettleUps();

        for (int i = 0; i < settles; i++) {
            View itemView = inflater.inflate(R.layout.settleup_item_activity, linearLayoutContainer, false);

            TextView settleUpStatement = itemView.findViewById(R.id.settleUpStatement);
            Button settleUpButton = itemView.findViewById(R.id.settleUpButton);
            Button askButton = itemView.findViewById(R.id.askButton);

            settleUpButton.setOnClickListener(v -> {
                Toast.makeText(SettleActivity.this, "Settle Up clicked", Toast.LENGTH_SHORT).show();
            });

            askButton.setOnClickListener(v -> {
                Toast.makeText(SettleActivity.this, "Ask clicked", Toast.LENGTH_SHORT).show();
            });

            linearLayoutContainer.addView(itemView);
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.linearLayoutContainer), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private int getAllSettleUps() {
        return 5;
    }
}
