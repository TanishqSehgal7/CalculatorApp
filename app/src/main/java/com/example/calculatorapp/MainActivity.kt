package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.annotation.IntegerRes
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ResourceBundle.clearCache
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        updateText("")

    }
    val operationList: MutableList<String> = arrayListOf()
    val numCache: MutableList<String> = arrayListOf()

    private fun getString(items: List<String>, connect: String=""):String {
        if(items.isEmpty())
            return ""
        return items.reduce { acc, s -> acc+connect+s }
    }

    private fun updateText(mainUIString: String){
        val calculationString=getString(operationList,"")
        var calculationTxtView=findViewById<View>(R.id.tv2) as TextView
        calculationTxtView.text=calculationString

        val ans=findViewById<View>(R.id.tv1) as TextView
        ans.text=mainUIString

    }

    fun pressnum (view: View){
        val button = view as Button
        val numString=button.text
        numCache.add (numString.toString())
        val text = getString(numCache)
        updateText(text)

    }

    fun operatorPress(view: View){
        val button= view as Button
        if(numCache.isEmpty())
            return
        operationList.add(getString(numCache))
        numCache.clear()
        operationList.add(button.text.toString())
        updateText(button.text.toString())

    }

    private fun clearText(view: View){
        operationList.clear()
        numCache.clear()
    }

    fun allClear (view: View) {
        clearText(view)
        updateText("")
    }

    fun equalSmash (view: View) {
        operationList.add (getString(numCache))
        numCache.clear()

        val calculator = ExpressionCalc()
        val answer = calculator.calculate(operationList)

        updateText("=" + answer.toString())
        clearCache()

    }
}




