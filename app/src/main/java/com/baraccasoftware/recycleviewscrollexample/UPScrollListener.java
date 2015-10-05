package com.baraccasoftware.recycleviewscrollexample;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;

/**
 * Created by angelomoroni on 05/10/15.
 */
public abstract class UPScrollListener extends RecyclerView.OnScrollListener {

    private int toolbarOffset = 0;
    private int toolbarHeight;

    public UPScrollListener(Context context) {
        int[] actionBarAttr = new int[] { android.R.attr.actionBarSize };
        TypedArray a = context.obtainStyledAttributes(actionBarAttr);

        Resources r = context.getResources();
        int dd = (int)context.getResources().getDimension(R.dimen.dimen_bar);
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dd, r.getDisplayMetrics());

        toolbarHeight = (int) px + 10;
        //a.recycle();


    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        clipToolbarOffset();
        onMoved(toolbarOffset);

        if((toolbarOffset < toolbarHeight && dy>0) || (toolbarOffset > 0 && dy<0)) {
            toolbarOffset += dy;
        }
    }

    private void clipToolbarOffset() {
        if(toolbarOffset > toolbarHeight) {
            toolbarOffset = toolbarHeight;
        } else if(toolbarOffset < 0) {
            toolbarOffset = 0;
        }
    }

    public abstract void onMoved(int distance);
}
