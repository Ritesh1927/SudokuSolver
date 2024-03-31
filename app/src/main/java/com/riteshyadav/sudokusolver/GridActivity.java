package com.riteshyadav.sudokusolver;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GridActivity extends AppCompatActivity {

    Button button;

    private char[] result = new char[81];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process();
            }
        });
    }
    private void process(){
        boolean is = on();
        if(is) {
            Intent intent = new Intent(GridActivity.this, Solution.class);
            intent.putExtra("result", result);
            startActivity(intent);
        }else{
            Toast.makeText(GridActivity.this,"Solution does not exit",Toast.LENGTH_SHORT).show();
        }
    }
    public boolean on(){
        char[][] sudoku = new char[9][9];
        int[] editTextIds = {
                R.id.e1, R.id.e2, R.id.e3, R.id.e4, R.id.e5, R.id.e6, R.id.e7, R.id.e8, R.id.e9,
                R.id.e10, R.id.e11, R.id.e12, R.id.e13, R.id.e14, R.id.e15, R.id.e16, R.id.e17, R.id.e18,
                R.id.e19, R.id.e20, R.id.e21, R.id.e22, R.id.e23, R.id.e24, R.id.e25, R.id.e26, R.id.e27,
                R.id.e28, R.id.e29, R.id.e30, R.id.e31, R.id.e32, R.id.e33, R.id.e34, R.id.e35, R.id.e36,
                R.id.e37, R.id.e38, R.id.e39, R.id.e40, R.id.e41, R.id.e42, R.id.e43, R.id.e44, R.id.e45,
                R.id.e46, R.id.e47, R.id.e48, R.id.e49, R.id.e50, R.id.e51, R.id.e52, R.id.e53, R.id.e54,
                R.id.e55, R.id.e56, R.id.e57, R.id.e58, R.id.e59, R.id.e60, R.id.e61, R.id.e62, R.id.e63,
                R.id.e64, R.id.e65, R.id.e66, R.id.e67, R.id.e68, R.id.e69, R.id.e70, R.id.e71, R.id.e72,
                R.id.e73, R.id.e74, R.id.e75, R.id.e76, R.id.e77, R.id.e78, R.id.e79, R.id.e80, R.id.e81
        };

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                // Use the predefined resource ID
                int resId = editTextIds[i * 9 + j];
                EditText e = (EditText) findViewById(resId);

                String s = e.getText().toString();

                if(s.length()>1){
                    return false;
                }

                // Check if editText is not null to avoid NullPointerException
                if (!s.equals("")) {
                    char ch = s.charAt(0);

                    if(Integer.parseInt(ch+"0")==0){
                        return false;
                    }

                    sudoku[i][j] = ch;
                } else {
                    // Handle the case where editText or its text is null
                    sudoku[i][j] = '.'; // Or some default value
                }
            }
        }
        return solution(sudoku);
    }

    private boolean isSafe(char[][] board,int i,int j,int target){
        char t = (char)(target+'0');

        for(int x=0;x<board.length;x++){
            if(board[x][j]==t && x!=i){
                return false;
            }
            if(board[i][x]==t && x!=j){
                return false;
            }
        }

        int sr = (i/3)*3;
        int sc = (j/3)*3;

        for(int p=sr;p<sr+3;p++){
            for(int q=sc;q<sc+3;q++){
                if(board[p][q] == t && p!=i && q!=j){
                    return false;
                }
            }
        }

        return true;
    }

    private boolean helper(char[][] board, int row, int col){
        if(row==board.length){
            return true;
        }
        int nrow = 0;
        int ncol = 0;
        if(col != board.length-1){
            nrow = row;
            ncol = col+1;
        }else{
            nrow = row+1;
            ncol = 0;
        }
        if(board[row][col]!='.'){
            if(helper(board, nrow, ncol)){
                return true;
            }
        }else{
            for(int i=1;i<=9;i++){
                if(isSafe(board, row, col, i)){
                    char curr = (char)(i+'0');
                    board[row][col]=curr;
                    if(helper(board, nrow, ncol)){
                        return true;
                    }else{
                        board[row][col]='.';
                    }
                }
            }
        }
        return false;
    }

    private  boolean solution(char[][] sudoku){
        boolean found = exist(sudoku,0,0);
        if(found){
            helper(sudoku,0,0);
            for(int i=0;i<9;i++){
                for(int j=0;j<9;j++){
                    result[i*9+j] = sudoku[i][j];
                }
            }
        }
        return found;
    }
    private boolean exist(char[][] sudoku,int r,int c){
        if(r==sudoku.length){
            return true;
        }
        int nr=0;
        int nc=0;
        if(c==sudoku.length-1){
            nr=r+1;
        }else{
            nr=r;
            nc=c+1;
        }
        if(sudoku[r][c]=='.'){
            return exist(sudoku, nr, nc);
        }else{
            String s = String.valueOf(sudoku[r][c]);
            int curr = Integer.parseInt(s);
            if(!isSafe(sudoku, r, c, curr)){
                return false;
            }else{
                return exist(sudoku, nr, nc);
            }
        }
    }
}