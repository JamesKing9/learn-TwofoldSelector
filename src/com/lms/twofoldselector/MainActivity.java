package com.lms.twofoldselector;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements OnClickListener{

	private String leftValue;
	private String rightValue;
	private TextView tvTest;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tvTest = (TextView)findViewById(R.id.tvTest);
		
		Button btnTest = (Button)findViewById(R.id.btnTest);
		btnTest.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View arg0) {
		Intent intent = new Intent(this, ValuePickerMockActivity.class);
		intent.putExtra(ValuePickerMockActivity.SELECTED_LEFT, leftValue);
		intent.putExtra(ValuePickerMockActivity.SELECTED_RIGHT, rightValue);
		startActivityForResult(intent, 1);
	}	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(1 == requestCode){
			if(RESULT_OK == resultCode){
				//选择车牌
				leftValue = data.getStringExtra(ValuePickerMockActivity.SELECTED_LEFT);
				rightValue = data.getStringExtra(ValuePickerMockActivity.SELECTED_RIGHT);
				tvTest.setText(leftValue + " - " + rightValue);				
			}
		}
	}
	
	
}
