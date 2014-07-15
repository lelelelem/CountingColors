package com.lemuelcastro.android.countingcolorsgl;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public class DialogFrag extends DialogFragment {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		return new AlertDialog.Builder(getActivity()).setTitle("sure?")
				.setPositiveButton("yes", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent i = new Intent(getActivity(), Menu.class);
						i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(i);
					}
				}).setNegativeButton("continue", new OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_CANCELED, null);
					}
				}).create();
	}

}
