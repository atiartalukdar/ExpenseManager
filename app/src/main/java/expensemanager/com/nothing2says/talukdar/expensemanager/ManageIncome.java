package expensemanager.com.nothing2says.talukdar.expensemanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ManageIncome extends AppCompatActivity {
    addExpense aE = new addExpense();
    EditText edIncomeAmount,edIncomeNote;
    int DEFAULT = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_income);

        edIncomeAmount = (EditText) findViewById(R.id.incomeAmountED);
        edIncomeNote = (EditText) findViewById(R.id.incomeAmountNoteED);


    }


    public void subtractIncome(View view){
        int previousBalance=DEFAULT,currentBanalce=DEFAULT,newBanalce=DEFAULT;
        previousBalance = aE.readSharedPreference("myBalance","firstIncomeBanalce");
        Toast.makeText(this,"Load Successfull "+previousBalance,Toast.LENGTH_LONG).show();
    }

    public  void addIncome(View view){

    }

    public void showAllIncome(View view){

    }























    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_manage_income, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
