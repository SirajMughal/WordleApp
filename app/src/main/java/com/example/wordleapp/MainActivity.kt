package com.example.wordleapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

var guessCount = 0
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textBox = findViewById<EditText>(R.id.guessEditText)
        val button = findViewById<Button>(R.id.button)
        val guess1 = findViewById<TextView>(R.id.guess1)
        val guess2 = findViewById<TextView>(R.id.guess2)
        val guess3 = findViewById<TextView>(R.id.guess3)
        val word = FourLetterWordList.getRandomFourLetterWord()
        val wordBox = findViewById<TextView>(R.id.Answer)
        wordBox.text = word

        wordBox.visibility = View.INVISIBLE

        button.setOnClickListener {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(button.windowToken, 0)

            if (guessCount < 4) {
                guessCount += 1

                Toast.makeText(this, "Guess #" + guessCount, Toast.LENGTH_SHORT).show()
                var guess = textBox.text
                var wordCheck = ""

                for (i in 0..3) {

                    if (guess[i].uppercase() == word[i].uppercase()) {
                        wordCheck += 'O'
                    }
                    else if (guess[i].uppercase() in word) {
                        wordCheck += "+"
                    }
                    else {
                        wordCheck += "X"
                    }
                }

                if (guessCount == 1) {
                    guess1.text = textBox.text
                    textBox.text.clear()
                    findViewById<TextView>(R.id.hint1).text = wordCheck
                }
                else if (guessCount == 2) {
                    guess2.text = textBox.text
                    textBox.text.clear()
                    findViewById<TextView>(R.id.hint2).text = wordCheck
                }
                else if (guessCount == 3) {
                    guess3.text = textBox.text
                    textBox.text.clear()
                    findViewById<TextView>(R.id.hint3).text = wordCheck
                    wordBox.visibility = View.VISIBLE
                    button.visibility = View.INVISIBLE
                }


            }


        }
    }


}
