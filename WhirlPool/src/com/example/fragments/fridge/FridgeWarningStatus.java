package com.example.fragments.fridge;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.whirlpool.R;

public class FridgeWarningStatus extends Fragment {

	private View view;
	private FridgeStatus fridgeStatus;
	private FridgeTimerStatus fridgeTimerStatus;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view =  inflater.inflate(R.layout.fridge_warning_status, container);

		view.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Fragment homeDataFragment = new FridgeDataWarningStatus();
				FragmentManager homeDataFragmentManager = getChildFragmentManager();

				homeDataFragmentManager
				.beginTransaction()
				.replace(R.id.home_data_area_container,
						homeDataFragment).commit();
				
				Fragment fridgeStatus = new FridgeStatus();
				getFragmentManager().beginTransaction().hide(fridgeStatus);
				
				Fragment fridgeTimerStatus = new FridgeTimerStatus();
				getFragmentManager().beginTransaction().hide(fridgeTimerStatus);

			}
		});

		return view;
	}

}
