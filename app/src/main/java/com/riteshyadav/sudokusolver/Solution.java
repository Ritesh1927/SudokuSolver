package com.riteshyadav.sudokusolver;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Solution extends AppCompatActivity {

    private char[] result = new char[81];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solution);

        Intent intent = getIntent();
        result = intent.getCharArrayExtra("result");
        display();
    }

    public void display(){
        int[] textViewIds = {
                R.id.t1, R.id.t2, R.id.t3, R.id.t4, R.id.t5, R.id.t6, R.id.t7, R.id.t8, R.id.t9, R.id.t10,
                R.id.t11, R.id.t12, R.id.t13, R.id.t14, R.id.t15, R.id.t16, R.id.t17, R.id.t18, R.id.t19, R.id.t20,
                R.id.t21, R.id.t22, R.id.t23, R.id.t24, R.id.t25, R.id.t26, R.id.t27, R.id.t28, R.id.t29, R.id.t30,
                R.id.t31, R.id.t32, R.id.t33, R.id.t34, R.id.t35, R.id.t36, R.id.t37, R.id.t38, R.id.t39, R.id.t40,
                R.id.t41, R.id.t42, R.id.t43, R.id.t44, R.id.t45, R.id.t46, R.id.t47, R.id.t48, R.id.t49, R.id.t50,
                R.id.t51, R.id.t52, R.id.t53, R.id.t54, R.id.t55, R.id.t56, R.id.t57, R.id.t58, R.id.t59, R.id.t60,
                R.id.t61, R.id.t62, R.id.t63, R.id.t64, R.id.t65, R.id.t66, R.id.t67, R.id.t68, R.id.t69, R.id.t70,
                R.id.t71, R.id.t72, R.id.t73, R.id.t74, R.id.t75, R.id.t76, R.id.t77, R.id.t78, R.id.t79, R.id.t80,
                R.id.t81
        };

        for(int i=0;i<81;i++){
            int textId = textViewIds[i];
            TextView textView = findViewById(textId);

            if(textView != null){
                String s = Character.toString(result[i]);
                textView.setText(s);
            }
        }
    }
}