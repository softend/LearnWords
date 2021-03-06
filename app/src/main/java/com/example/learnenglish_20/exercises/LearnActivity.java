package com.example.learnenglish_20.exercises;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learnenglish_20.support.Constants;
import com.example.learnenglish_20.main_activity_fragments.MainActivity;
import com.example.learnenglish_20.main_activity_fragments.CurrentLessonFragment;
import com.example.learnenglish_20.R;
import com.example.learnenglish_20.data.Word;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class LearnActivity extends AppCompatActivity {

    int index, chapter, lesson;
    Button btMemo;
    ImageButton backToLesson;
    TextView englishWord, russianWord;
    public static List<Word> currentLessonWordsArr = CurrentLessonFragment.currentLessonWordsArr;
    List<Integer> usedValues;
    ImageView wordPic;
    ProgressBar progressBar;
    Boolean newLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);

        init(); // Инициализация полей

        setMemoBtnListener(); // Устанавливаем слушатели
        setCtBackClickListener();


    }

    private void setCtBackClickListener() {
        backToLesson.setOnClickListener(b -> {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(Constants.CHAPTER_KEY, chapter);
            intent.putExtra(Constants.LESSON_KEY, lesson);
            startActivity(intent);
        });
    }

    private void setMemoBtnListener() {
        btMemo.setOnClickListener(b -> {
            usedValues.add(index);
            if (usedValues.size() < 10) {
                setNewWords();
            } else {
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("chapter", chapter);
                intent.putExtra("lesson", lesson);
                Toast.makeText(this, "Поздравляю, вы закончили упражнение!", Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });
    }

    private void setNewWords() {
        progressBar.setVisibility(View.VISIBLE);
        if (usedValues.size() != 0) {
            do {
                index = (int) (Math.random() * 10);
            } while (usedValues.contains(index));
        } else {
            index = (int) (Math.random() * 10);
        }
        Word word = currentLessonWordsArr.get(index);
        englishWord.setText(word.getEnglish());
        russianWord.setText(word.getRussian());
        Picasso.get().load(word.getPicture()).into(wordPic, new Callback() {
            @Override
            public void onSuccess() {
                progressBar.setVisibility(View.INVISIBLE); // Убираем ProgressBar когда изображение загружено
            }

            //Callback говорит нам что дейсвие за которое он ответственный совершено
            @Override
            public void onError(Exception e) {

            }
        });
    }

    private void init() {
        getIntentLearn();
        englishWord = findViewById(R.id.english_word_learn);
        russianWord = findViewById(R.id.russian_word_learn);
        usedValues = new ArrayList<>();
        btMemo = findViewById(R.id.bt_next);
        backToLesson = findViewById(R.id.imageButtonToLesson);
        wordPic = findViewById(R.id.word_image);
        progressBar = findViewById(R.id.progress_bar_learn);
        setNewWords();
    }

    private void getIntentLearn() {
        Intent i = getIntent();
        if (i != null) {
            chapter = i.getIntExtra(Constants.CHAPTER_KEY, -1);
            lesson = i.getIntExtra(Constants.LESSON_KEY, -1);
        }
    }
}