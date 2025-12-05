package com.example.quiz_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class QuestionActivity3 extends AppCompatActivity {

    int flag = 0;
    public static int correct = 0;
    int wrong = 0;

    String[] questions = {
            "Quelle est la sortie de print(2**3) en Python ?",
            "Quelle est la différence entre list et tuple ?",
            "Comment définit-on une fonction en Python ?"
    };

    String[] options = {
            "5","6","8","9",
            "list est immuable, tuple est mutable","list est mutable, tuple est immuable","aucune différence","tuple n'existe pas",
            "def ma_fonction():","function ma_fonction():","fun ma_fonction():","func ma_fonction():"
    };

    String[] answers = {
            "8",
            "list est mutable, tuple est immuable",
            "def ma_fonction():"
    };

    TextView quitBtn, dispNo, score, question;
    Button next;
    RadioGroup radio_g;
    RadioButton rb1, rb2, rb3, rb4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_question);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        quitBtn = findViewById(R.id.quitBtn);
        question = findViewById(R.id.question);
        score = findViewById(R.id.score);
        dispNo = findViewById(R.id.dispNo);
        next = findViewById(R.id.nextBtn);
        radio_g = findViewById(R.id.answerGroup);
        rb1 = findViewById(R.id.radioBtn1);
        rb2 = findViewById(R.id.radioBtn2);
        rb3 = findViewById(R.id.radioBtn3);
        rb4 = findViewById(R.id.radioBtn4);

        loadQuestion();

        next.setOnClickListener(v -> {
            if (radio_g.getCheckedRadioButtonId() == -1) {
                Toast.makeText(QuestionActivity3.this, "Please select an option", Toast.LENGTH_SHORT).show();
                return;
            }

            RadioButton uAnswer = findViewById(radio_g.getCheckedRadioButtonId());
            String ansText = uAnswer.getText().toString();

            if (ansText.equals(answers[flag])) correct++;
            else wrong++;

            flag++;
            if (flag < questions.length) {
                loadQuestion();
            } else {
                Intent intent = new Intent(QuestionActivity3.this, ResultActivity3.class);
                intent.putExtra("attempted", flag);
                intent.putExtra("correct", correct);
                intent.putExtra("wrong", wrong);
                startActivity(intent);
                finish();
            }

            radio_g.clearCheck();
        });

        quitBtn.setOnClickListener(v -> {
            Intent intent = new Intent(QuestionActivity3.this, ResultActivity3.class);
            intent.putExtra("attempted", flag);
            intent.putExtra("correct", correct);
            intent.putExtra("wrong", wrong);
            startActivity(intent);
            finish();
        });
    }

    private void loadQuestion() {
        question.setText(questions[flag]);
        rb1.setText(options[flag*4]);
        rb2.setText(options[flag*4 + 1]);
        rb3.setText(options[flag*4 + 2]);
        rb4.setText(options[flag*4 + 3]);
        dispNo.setText((flag+1) + "/" + questions.length);
        score.setText(String.valueOf(correct));
    }
}
