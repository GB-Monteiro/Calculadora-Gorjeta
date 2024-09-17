package com.example.calculadora;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText totalAmountEditText;
    private EditText numberOfPeopleEditText;
    private RadioGroup tipPercentageGroup;
    private TextView resultTextView;
    private Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Definir inserções para a visualização
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Encontrar visualizações
        totalAmountEditText = findViewById(R.id.totalAmountEditText);
        numberOfPeopleEditText = findViewById(R.id.numberOfPeopleEditText);
        tipPercentageGroup = findViewById(R.id.tipPercentageGroup);
        resultTextView = findViewById(R.id.resultTextView);
        calculateButton = findViewById(R.id.calculateButton);

        // Defina onClickListener para o botão calcular
        calculateButton.setOnClickListener(v -> calculateTip());
    }

    private void calculateTip() {
        // Obtenha o valor total e o número de pessoas
        String totalAmountStr = totalAmountEditText.getText().toString();
        String numberOfPeopleStr = numberOfPeopleEditText.getText().toString();

        if (totalAmountStr.isEmpty() || numberOfPeopleStr.isEmpty()) {
            resultTextView.setText("Por favor, insira todos os valores.");
            return;
        }

        double totalAmount = Double.parseDouble(totalAmountStr);
        int numberOfPeople = Integer.parseInt(numberOfPeopleStr);

        // Obtenha a porcentagem de gorjeta selecionada
        int selectedTipId = tipPercentageGroup.getCheckedRadioButtonId();
        double tipPercentage = 0;

        // Substituindo switch-case por if-else
        if (selectedTipId == R.id.tip10) {
            tipPercentage = 0.10;
        } else if (selectedTipId == R.id.tip15) {
            tipPercentage = 0.15;
        } else if (selectedTipId == R.id.tip20) {
            tipPercentage = 0.20;
        } else if (selectedTipId == R.id.tip25) {
            tipPercentage = 0.25;
        } else if (selectedTipId == R.id.tip30) {
            tipPercentage = 0.30;
        } else {
            resultTextView.setText("Por favor, selecione uma porcentagem de gorjeta.");
            return;
        }

        // Calcule a gorjeta total e divida pelo número de pessoas
        double totalTip = totalAmount * tipPercentage;
        double tipPerPerson = totalTip / numberOfPeople;

        // Exibir o resultado
        resultTextView.setText(String.format("Gorjeta total: R$%.2f\nGorjeta por pessoa: R$%.2f", totalTip, tipPerPerson));
    }
}