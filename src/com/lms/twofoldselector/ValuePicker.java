package com.lms.twofoldselector;

import java.util.Map;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

public class ValuePicker extends LinearLayout{
	
	private Context mContext;
	private LayoutInflater mInflater;
	private ListView lvLeft, lvRight;
			
	private String[] summaries = DataProvider.summaries;
	private Map<String, String[]> details = DataProvider.details;
	
	private int mPosLeft = -1;	
	private String mCurLeft;
	private int mPosRight = -1;
	private String mCurRight;
	
	private Button btnConfirm;
	
	/**
	 * Listener to handle the logic when current city card number is selected
	 */
	public OnClickListener mListener;
	
	/**
	 * @param listener the listener to set
	 */
	public void setButtonOnClickListener(OnClickListener listener) {
		this.mListener = listener;
	}

	public ValuePicker(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	
	public ValuePicker(Context context) {
		super(context);
		init(context);
	}
	
	private void init(Context context){
		mContext = context;		
	}
	
	public void initialize(){
		initData();		
		initView();
	}
	
	private void initData(){		
		
		int len = summaries.length;
		
		for(int i = 0; i < len; i++){
			
			String summary = summaries[i];
			
			if(summary.equals(mCurLeft)){
				mPosLeft = i;
				break;
			}		
						
		}
		
		if(mPosLeft >= 0){
			String summary = summaries[mPosLeft];
			String[] right = details.get(summary);
			int lenOfRight = right.length;
			
			for(int j = 0; j < lenOfRight; j++){
				
				String detail = right[j];
				
				if(mCurRight != null && detail.equals(mCurRight)){
					mPosRight = j;
					break;
				}
			}
		}
		
		
		
		Log.v(this.getClass().getName(), "mCurLeft = " + mCurLeft + " | mPosLeft = " + mPosLeft);
		Log.v(this.getClass().getName(), "mCurRight= " + mCurRight + " | mPosRight "+ mPosRight);
	}
	
	private void initView(){
		mInflater = LayoutInflater.from(mContext);
		View parent = mInflater.inflate(R.layout.custom_twofold_listview, this);
		
		lvLeft = (ListView) parent.findViewById(R.id.lvLeft);
		lvRight = (ListView) parent.findViewById(R.id.lvRight);
		btnConfirm = (Button) parent.findViewById(R.id.btnConfirm);
		
		final SingleCheckedListAdapter lAdapter = new SingleCheckedListAdapter(mContext, summaries);
		lAdapter.setCheckedPosition(mPosLeft);
		lvLeft.setAdapter(lAdapter);
		
		String[] rights = new String[]{};
		if(mPosLeft >= 0 && mPosRight >= 0){
			rights = details.get(summaries[mPosLeft]);
		}
		
		final SingleCheckedListAdapter rAdapter = new SingleCheckedListAdapter(mContext, rights);		
		rAdapter.setCheckedPosition(mPosRight);		
		lvRight.setAdapter(rAdapter);		
		
		lvLeft.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
				
				mCurRight = null;
				lAdapter.setCheckedPosition(position);
				lAdapter.setCheckedView(view);
				mPosLeft = position;
				rAdapter.setData(details.get(summaries[mPosLeft]));
			}
		});
		
		lvRight.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
				rAdapter.setCheckedPosition(position);
				rAdapter.setCheckedView(view);
				mCurRight = details.get(summaries[mPosLeft])[position];
			}
		});		
		
		btnConfirm.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View arg0) {
				if(mListener != null){
					mListener.onClick(arg0);
				}
			}
		});
	}
	
	public String getLeftVaue(){
		if(mPosLeft >= 0){
			return summaries[mPosLeft];
		}else{
			return "";
		}
	}
	
	public void setLeftValue(String leftValue){
		mCurLeft = leftValue;
	}
	
	public String getRightValue(){
		return mCurRight;
	}
	
	public void setRightValue(String rightValue){
		mCurRight  = rightValue;
	}
}
