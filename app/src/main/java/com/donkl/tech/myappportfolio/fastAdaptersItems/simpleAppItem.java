package com.donkl.tech.myappportfolio.fastAdaptersItems;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.donkl.tech.myappportfolio.R;
import com.mikepenz.fastadapter.items.AbstractItem;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ِAhmed Donkl on 7-08-2015.
 * this item is used by fast adapter
 * find more about implementation
 * https://github.com/mikepenz/FastAdapter
 */
public class simpleAppItem extends AbstractItem<simpleAppItem, simpleAppItem.ViewHolder> {

    private String appName;

    public String getAppName() {
        return appName;
    }

    public simpleAppItem(String appName) {
        this.appName = appName;
    }

    // Fast Adapter AbstractItem methods
    @Override
    public int getType() {
        return R.id.app_item_id;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.simple_app_item;
    }

    @Override
    public void bindView(ViewHolder holder) {
        super.bindView(holder);
        //logic in views here when binding

        //set the app name
        holder.mAppNameText.setText(appName);
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        //bind item views here
        @Bind(R.id.app_name)
        TextView mAppNameText;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}