package com.example.fragments.fridge;

import com.example.whirlpool.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FridgeDataWarningStatus extends Fragment {
	
	private View view;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		view =  inflater.inflate(R.layout.fridge_data_warning_status, container,false);
		
		
		
		return view;
	}
}
