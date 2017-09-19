package allenmilngroup.listener;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvOut;
    Button btnOk;
    Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Поиск View элементов.
        tvOut = (TextView) findViewById(R.id.tvOut);
        btnOk = (Button) findViewById(R.id.btnOk);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        //Слушатель события.
        View.OnClickListener oclBtn = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btnOk:
                        tvOut.setText("Нажата кнопка Ok.");
                        break;
                    case R.id.btnCancel:
                        tvOut.setText("Нажата кнопка Cancel.");
                        break;
                }
            }
        };

        //присваиваем кнопке слушатель.
        btnOk.setOnClickListener(oclBtn);
        btnCancel.setOnClickListener(oclBtn);

    }
}