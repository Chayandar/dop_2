package com.example.dop_2;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    final int N = 10;
    int x = N - 2;
    int y = 1;
    int score = 0;

    private ImageView[][] map;
    private int[][] mas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        map = new ImageView[3][3];
        map[0][0] = findViewById(R.id.imageView1);
        map[0][1] = findViewById(R.id.imageView2);
        map[0][2] = findViewById(R.id.imageView3);
        map[1][0] = findViewById(R.id.imageView4);
        map[1][1] = findViewById(R.id.imageView5);
        map[1][2] = findViewById(R.id.imageView6);
        map[2][0] = findViewById(R.id.imageView7);
        map[2][1] = findViewById(R.id.imageView8);
        map[2][2] = findViewById(R.id.imageView9);

        mas = new int[N][N];
        Random random = new Random();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                mas[i][j] = random.nextInt(2);
            }
        }

        refresh();
    }

    public void refresh() {
        TextView coordsT = findViewById(R.id.coord);
        TextView scoreT = findViewById(R.id.scoreText);
        StringBuilder text = new StringBuilder();
        for (int row = 0; row < 10; row++) {
            for (int i = 0; i < 10; i++) {
                if (row == x && i == y)
                    text.append("x");
                else
                    text.append(mas[row][i]);
                text.append(" ");
            }
            text.append("\n");
        }
        coordsT.setText(text.toString());

        int xMap = x - 1;
        int yMap = y - 1;

        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 3; i++) {
                if (mas[xMap + i][yMap + j] == 0)
                    map[i][j].setImageResource(R.drawable.clean_way_background);
                else
                    map[i][j].setImageResource(R.drawable.wall_background);
            }
        }
        map[1][1].setImageResource(R.drawable.player_background);
        scoreT.setText("Счет\n" + score);
    }

    public void pressOnButton(View v) {
        Button bUp = findViewById(R.id.buttonUp);
        Button bDown = findViewById(R.id.buttonDown);
        Button bRight = findViewById(R.id.buttonRight);
        Button bLeft = findViewById(R.id.buttonLeft);

        if (v.getId() == bUp.getId() && x > 1 && mas[x - 1][y] != 1) {
            x--;
            score++;
        }
        if (v.getId() == bDown.getId() && x <= 7 && mas[x + 1][y] != 1) {
            x++;
            score++;
        }
        if (v.getId() == bLeft.getId() && y > 1 && mas[x][y - 1] != 1) {
            y--;
            score++;
        }
        if (v.getId() == bRight.getId() && y <= 7 && mas[x][y + 1] != 1) {
            y++;
            score++;
        }
        refresh();
    }
}