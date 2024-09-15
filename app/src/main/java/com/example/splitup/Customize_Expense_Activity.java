package com.example.splitup;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Customize_Expense_Activity extends AppCompatActivity {

    private Button customSplitButton;
    private TextView customSplitDescription;
    private TextView customSplitCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize_expense);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();

        String amount = intent.getStringExtra("AMOUNT");
        String description = intent.getStringExtra("DESCRIPTION");

        if (amount == null) {
            amount = "0";
        }
        if (description == null) {
            description = "Description ...";

            customSplitButton = findViewById(R.id.splitCustomButton);
            customSplitDescription = findViewById(R.id.expenseDescriptionTitle);
            customSplitCost = findViewById(R.id.splitAmountCustomValue);

            customSplitCost.setText(amount);
            customSplitDescription.setText(description);

            customSplitCost.setFocusable(false);

            customSplitButton.setOnClickListener(v -> {
                //Toast.makeText("Custom Split", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(Customize_Expense_Activity.this, GroupActivity.class);
                startActivity(intent1);
            });

            LinearLayout showMembersLinearLayout = findViewById(R.id.showMembersLinearLayout);

            if (showMembersLinearLayout != null) {
                String[] members = {"Alice", "Bob", "Charlie"};  // Sample data for testing

                for (String member : members) {
                    if (member != null) {
                        TextView memberTextView = new TextView(this);
                        memberTextView.setLayoutParams(new LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT));
                        memberTextView.setGravity(Gravity.CENTER_VERTICAL);
                        memberTextView.setPadding(16, 16, 16, 16);
                        memberTextView.setText(member);  // Set member name
                        showMembersLinearLayout.addView(memberTextView);
                    }
                }
            } else {
                // Log an error in case the LinearLayout is not found
                Log.e("CustomizeExpense", "LinearLayout not found");
            }


        }
    }
}
