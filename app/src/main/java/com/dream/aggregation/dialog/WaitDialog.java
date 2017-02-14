package com.dream.aggregation.dialog;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import com.dream.aggregation.R;

public class WaitDialog extends Dialog {
    private static WaitDialog waitDialog = null;

    public WaitDialog(Context context, int theme) {
        super(context, theme);
    }

    public static WaitDialog createDialog(Context context) {
    	waitDialog = new WaitDialog(context,R.style.progress_dialog);
		waitDialog.setContentView(R.layout.wait_dialog);
		waitDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent); 
		waitDialog.setCanceledOnTouchOutside(false);
		TextView msg = (TextView) waitDialog.findViewById(R.id.id_tv_loadingmsg);  
		msg.setText(context.getResources().getString(R.string.wait_msg));
        return waitDialog;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    	return;
    }
}
