package com.example.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception
import kotlin.math.sqrt
import kotlin.properties.Delegates


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        one.setOnClickListener { appendOnExpression("1",true) }
        two.setOnClickListener { appendOnExpression("2",true) }
        three.setOnClickListener { appendOnExpression("3",true) }
        four.setOnClickListener { appendOnExpression("4",true) }
        five.setOnClickListener { appendOnExpression("5",true) }
        six.setOnClickListener { appendOnExpression("6",true) }
        seven.setOnClickListener { appendOnExpression("7",true) }
        eight.setOnClickListener { appendOnExpression("8",true) }
        nine.setOnClickListener { appendOnExpression("9",true) }
        zero.setOnClickListener { appendOnExpression("0",true) }
        point.setOnClickListener { appendOnExpression(".",true) }
        bracketopen.setOnClickListener { appendOnExpression("(",false) }
        bracketclose.setOnClickListener { appendOnExpression(")",false) }
        divide.setOnClickListener { appendOnExpression("/",false) }
        multiply.setOnClickListener { appendOnExpression("*",false) }
        sub.setOnClickListener { appendOnExpression("-",false) }
        add.setOnClickListener { appendOnExpression("+",false) }
        pow.setOnClickListener { appendOnExpression("^",false) }
        pi.setOnClickListener { appendOnExpression("3.14",true) }
        SQroot.setOnClickListener { appendOnExpression("^(1/2)",false) }

        AC.setOnClickListener {
            input.text=""
            result.text=""
        }

        backspace.setOnClickListener {
            val string=input.text.toString()
            if (string.isNotEmpty()){
                input.text=string.substring(0,string.length-1)
            }
            result.text=""
        }


        equal.setOnClickListener {
            try {
                val inputExpression= ExpressionBuilder(input.text.toString()).build()
                val finalResult=inputExpression.evaluate()
                val longFinalResult=finalResult.toLong()
                if(finalResult==longFinalResult.toDouble()){
                    result.text=longFinalResult.toString()
                } else {
                    result.text=finalResult.toString()
                }
            } catch (e:Exception){
                Log.d("Exception","Invalid"+e.message)
            }
        }

    }

    fun appendOnExpression(string:String,canClr:Boolean){

        if (result.text.isNotEmpty()){
            result.text=""
        }

            if(canClr==true){
                result.setText("")
                input.append(string)
            }
        else{
                input.append(result.text)
                input.append(string)
                result.append("")
            }

    }
}




