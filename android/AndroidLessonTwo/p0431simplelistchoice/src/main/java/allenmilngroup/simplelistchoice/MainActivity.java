package allenmilngroup.simplelistchoice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    public void onClick(View view) {

    }

    /*
    final String LOG_TAG = "myLogs";
    ListView lvMain;
    String[] names;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvMain = (ListView) findViewById(R.id.lvMain);

        // устанавливаем режим выбора пунктов списка
        lvMain.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        // Создаем адаптер, используя массив из файла ресурсов
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.names,
                android.R.layout.simple_list_item_multiple_choice);
        lvMain.setAdapter(adapter);
        Button btnChecked = (Button) findViewById(R.id.btnChecked);
        btnChecked.setOnClickListener(this);
        // получаем массив из файла ресурсов
        names = getResources().getStringArray(R.array.names);
    }

    public void onClick(View arg0) {
        // пишем в лог выделенный элемент
        Log.d(LOG_TAG, "checked: ");
        SparseBooleanArray sbArray = lvMain.getCheckedItemPositions();
        for (int i = 0, size = sbArray.size(); i < size; i++) {
            int key = sbArray.keyAt(i);
            if (sbArray.get(key)) {
                Log.d(LOG_TAG, names[key]);
            }
        }
    }
    */
}