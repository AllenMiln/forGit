package allenmilngroup.activityresult;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Color extends AppCompatActivity implements View.OnClickListener{

    Button btnRed;
    Button btnGreen;
    Button btnBlue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);
        btnRed = (Button) findViewById(R.id.btnRed);
        btnGreen = (Button) findViewById(R.id.btnGreen);
        btnBlue = (Button) findViewById(R.id.btnBlue);
        btnRed.setOnClickListener(this);
        btnGreen.setOnClickListener(this);
        btnBlue.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btnRed:
                intent.putExtra("color", android.graphics.Color.RED);
                break;
            case R.id.btnGreen:
                intent.putExtra("color", android.graphics.Color.GREEN);
                break;
            case R.id.btnBlue:
                intent.putExtra("color", android.graphics.Color.BLUE);
                break;
        }
        setResult(RESULT_OK, intent);
        finish();
    }
}