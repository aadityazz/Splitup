package com.example.splitup;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GroupActivity extends AppCompatActivity {

    private Button addExpenseButton;
    private Button memberButton;
    private Button settleUpButton;
    private Button deleteButton;
    private TextView groupNameTitle;
    private LinearLayout expensesContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_group);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addExpenseButton = findViewById(R.id.addExpenseButton);
        memberButton = findViewById(R.id.membersButton);
        settleUpButton = findViewById(R.id.settleButton);
        deleteButton = findViewById(R.id.deleteButton);
        groupNameTitle = findViewById(R.id.groupNameTitle);
        expensesContainer = findViewById(R.id.groupListLinearLayout);

        String groupName = getIntent().getStringExtra("GROUP_NAME");
        if (groupName != null) {
            groupNameTitle.setText(groupName);
        }

        addExpenseButton.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(GroupActivity.this);

            LayoutInflater inflater = getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.dialog_add_expense, null);
            builder.setView(dialogView);

            EditText amountEditText = dialogView.findViewById(R.id.amountEditText);
            EditText descriptionEditText = dialogView.findViewById(R.id.descriptionEditText);
            Button customizeExpenseButton = dialogView.findViewById(R.id.customizeExpenseButton);
            Button splitButton = dialogView.findViewById(R.id.splitButton);

            AlertDialog dialog = builder.create();

            customizeExpenseButton.setOnClickListener(view -> {
                String amount = amountEditText.getText().toString();
                String description = descriptionEditText.getText().toString();

                Intent intent = new Intent(GroupActivity.this, Customize_Expense_Activity.class);
                intent.putExtra("AMOUNT", amount);
                intent.putExtra("DESCRIPTION", description);
                startActivity(intent);

                Toast.makeText(GroupActivity.this, "Amount: " + amount + ", Description: " + description, Toast.LENGTH_LONG).show();
                dialog.dismiss();
            });

            splitButton.setOnClickListener(view -> {
                String amount = amountEditText.getText().toString();
                String description = descriptionEditText.getText().toString();
                Toast.makeText(GroupActivity.this, "Splitting Amount: " + amount + ", Description: " + description, Toast.LENGTH_LONG).show();

                dialog.dismiss();
            });
            dialog.show();
        });


        memberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = LayoutInflater.from(GroupActivity.this);
                View dialogView = inflater.inflate(R.layout.dialog_member_list, null);

                ListView memberListView = dialogView.findViewById(R.id.memberListView);
                Button addMemberButton = dialogView.findViewById(R.id.addMemberButton);

                AlertDialog.Builder builder = new AlertDialog.Builder(GroupActivity.this);
                builder.setView(dialogView);
                builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
                AlertDialog dialog = builder.create();
                dialog.show();

                addMemberButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Toast.makeText(GroupActivity.this, "Invitation Link has been copied, please invite your friends using the link", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
            }
        });

        settleUpButton.setOnClickListener(v -> {
            Intent intent = new Intent(GroupActivity.this, SettleActivity.class);
            startActivity(intent);
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteGroup();
                Intent intent = new Intent(GroupActivity.this, FullScreenActivity.class);
                startActivity(intent);
                finish();
            }

        });

        String[] allExpenses = getAllExpenses();
        LayoutInflater inflater = LayoutInflater.from(this);
        for (String expense : allExpenses) {
            View groupItemView = inflater.inflate(R.layout.expense_list, this.expensesContainer, false);
            ((TextView) groupItemView.findViewById(R.id.expenseNameTextView)).setText(expense);

            groupItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(GroupActivity.this, "Clicked on: " + expense, Toast.LENGTH_SHORT).show();
                }
            });

            this.expensesContainer.addView(groupItemView);
        }


    }

    private String[] getAllExpenses() {
        return new String[]{"Expense 1", "Expense 2", "Expense 3"};
    }

    private void deleteGroup() {
    }
}