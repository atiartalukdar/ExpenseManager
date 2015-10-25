package expensemanager.com.nothing2says.talukdar.expensemanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class addExpense extends AppCompatActivity {
    TextView tvMonth,tvFirstCurrentBalance;
    EditText expenseAmmountEd,expenseNoteEd;
    Spinner ctgrySpinner;

    private String[] categoryString;
    private String selectedCategory="";
    protected final static int DEFAULT = 0;
    int temp = DEFAULT,currentMonthIncome = DEFAULT ;
    String[] month = {"January","February","March","April","May","June","Julay","August","September","October","November","December"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        initialize();
        spinner();



        int yy,mm,dd;
        final Calendar c = Calendar.getInstance();
        //  yy = c.get(Calendar.YEAR);
        mm = c.get(Calendar.MONTH);
        //  dd = c.get(Calendar.DAY_OF_MONTH);

        // set current Month into textview
        tvMonth.setText(month[mm]);



        SharedPreferences sharedPreferences = getSharedPreferences("FirstIncome", Context.MODE_PRIVATE);

        temp = sharedPreferences.getInt("firstIncomeBalance",DEFAULT);
        if (temp==DEFAULT){
            Toast.makeText(this, "Data can't Load", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this,"Load Successfull",Toast.LENGTH_LONG).show();
            tvFirstCurrentBalance.setText("Current Balance "+temp+"Tk");
        }

    }



    //work with the spinners
    public void spinner() {

        ctgrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                                                     @Override
                                                     public void onItemSelected(AdapterView<?> arg0, View view, int arg2, long arg3) {

                                                         //indexExam = examSpinnerSE.getSelectedItemPosition();
                                                         selectedCategory = arg0.getSelectedItem().toString();
                                                         //Toast.makeText(ChalangeView.this, mSelectedItemExam+" is Selected", Toast.LENGTH_LONG).show();
                                                     }

                                                     @Override
                                                     public void onNothingSelected(AdapterView<?> arg0) {
                                                     }
                                                 }
        );

    }
    public void initialize(){
        //initialize view

        ctgrySpinner = (Spinner)findViewById(R.id.categorySpinner);
        tvMonth = (TextView) findViewById(R.id.monthTV);
        tvFirstCurrentBalance = (TextView) findViewById(R.id.currentBalanceTV);
        expenseAmmountEd= (EditText) findViewById(R.id.expenseAdd);
        expenseNoteEd= (EditText) findViewById(R.id.noteExpense);

        //initialize data source
        categoryString = getResources().getStringArray(R.array.initialCategory);

        // confirmButtonSE


        //initialize view
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, categoryString);

        //bind adapter and view
        ctgrySpinner.setAdapter(adapter);

    }













    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_expense, menu);
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
