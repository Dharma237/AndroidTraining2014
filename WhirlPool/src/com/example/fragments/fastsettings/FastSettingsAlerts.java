package com.example.fragments.fastsettings;

import com.example.whirlpool.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.Toast;

public class FastSettingsAlerts extends Fragment implements OnCheckedChangeListener{

	private Switch alerts_ON_OFF_Switch;
	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.fast_settings_alerts, container);

		alerts_ON_OFF_Switch = (Switch)view.findViewById(R.id.onOff_switch);

		alerts_ON_OFF_Switch.setChecked(true);

		alerts_ON_OFF_Switch.setOnCheckedChangeListener(this);

		return view;
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

		switch (buttonView.getId()) {

		case R.id.onOff_switch:

			if(isChecked)
			{
				Toast.makeText(getActivity(),getResources().getString(R.string.switch_on), Toast.LENGTH_SHORT).show();
			}
			else
				Toast.makeText(getActivity(),getResources().getString(R.string.switch_off), Toast.LENGTH_SHORT).show();

			break;
		}

	}
}
