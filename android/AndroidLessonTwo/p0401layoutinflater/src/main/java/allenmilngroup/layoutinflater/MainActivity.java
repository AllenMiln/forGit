package allenmilngroup.layoutinflater;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";
    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LayoutInflater layoutInflater = getLayoutInflater();

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linLayout);
        View viewOne = layoutInflater.inflate(R.layout.text, linearLayout, true);
        ViewGroup.LayoutParams layoutParams = viewOne.getLayoutParams();

        Log.d(LOG_TAG, "Class of view1: " + viewOne.getClass().toString());
        Log.d(LOG_TAG, "Class of layoutParams of view1: " + layoutParams.getClass().toString());

        RelativeLayout relLayout = (RelativeLayout) findViewById(R.id.relLayout);
        View view2 = layoutInflater.inflate(R.layout.text, relLayout, true);
        ViewGroup.LayoutParams lp2 = view2.getLayoutParams();

        Log.d(LOG_TAG, "Class of view2: " + view2.getClass().toString());
        Log.d(LOG_TAG, "Class of layoutParams of view2: " + lp2.getClass().toString());
    }
}

