package com.lms.twofoldselector;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

// 模仿（Mock） 数据采集者（Picker） 的界面
public class ValuePickerMockActivity extends Activity implements OnClickListener{

	public static final String SELECTED_LEFT = "SELECTED_LEFT";
	public static final String SELECTED_RIGHT = "SELECTED_RIGHT";
	
	private ValuePicker vpTest;
		
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vp_mock);
		
		vpTest = (ValuePicker) findViewById(R.id.vpTest);
		vpTest.setButtonOnClickListener(this);
		
		//set the selected view
		Intent intent = getIntent();
		String leftValue = intent.getStringExtra(SELECTED_LEFT);
		String rightValue = intent.getStringExtra(SELECTED_RIGHT);
		
		vpTest.setLeftValue(leftValue);
		vpTest.setRightValue(rightValue);
		
		vpTest.initialize();
	}

	@Override
	public void onClick(View arg0) {		
		String rightValue = vpTest.getRightValue();
		if(rightValue == null){
			Toast.makeText(this, "请选择右边的值", Toast.LENGTH_SHORT).show();
			return;
		}
		Intent data = new Intent();
		data.putExtra(SELECTED_LEFT, vpTest.getLeftVaue());
		data.putExtra(SELECTED_RIGHT, vpTest.getRightValue());
		setResult(RESULT_OK, data);
		finish();		
	}

}
