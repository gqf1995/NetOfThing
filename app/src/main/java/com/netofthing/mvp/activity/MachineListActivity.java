package com.netofthing.mvp.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fivefivelike.mybaselibrary.base.BasePullActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.netofthing.adapter.MachinesAdapter;
import com.netofthing.entity.bean.WarningListBean;
import com.netofthing.mvp.databinder.BaseActivityPullBinder;
import com.netofthing.mvp.delegate.BaseActivityPullDelegate;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MachineListActivity extends BasePullActivity<BaseActivityPullDelegate, BaseActivityPullBinder> {
    MachinesAdapter adapter;

    @Override
    protected Class<BaseActivityPullDelegate> getDelegateClass() {
        return BaseActivityPullDelegate.class;
    }

    @Override
    public BaseActivityPullBinder getDataBinder(BaseActivityPullDelegate viewDelegate) {
        return new BaseActivityPullBinder(viewDelegate);
    }

    public static void startAct(Activity activity,
                                String id,
                                String type) {
        Intent intent = new Intent(activity, MachineListActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("type", type);
        activity.startActivity(intent);
    }

    private String id;
    private String type;

    private void getIntentData() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        type = intent.getStringExtra("type");
    }

    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        getIntentData();
        initToolbar(new ToolbarBuilder().setTitle(type));
        initList(new ArrayList<WarningListBean>());
        viewDelegate.viewHolder.swipeRefreshLayout.setRefreshing(true);
        onRefresh();
    }

    private void initList(List<WarningListBean> data) {
        if (adapter == null) {
            adapter = new MachinesAdapter(this, data);
            adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                    ShowImgsActivity.startAct(viewDelegate.getActivity(),
                            adapter.getDatas().get(position).getId(),
                            adapter.getDatas().get(position).getDeviceName(),
                            adapter.getDatas().get(position).getEquipmentName()
                    );
                }

                @Override
                public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                    return false;
                }
            });
            initRecycleViewPull(adapter, new LinearLayoutManager(this));
        } else {
            Collections.sort(data, new Comparator<WarningListBean>() {
                public int compare(WarningListBean o1, WarningListBean o2) {
                    return o1.getSort() - o2.getSort();
                }
            });
            adapter.setHight(viewDelegate.viewHolder.pull_recycleview.getMeasuredHeight() / 3);
            getDataBack(adapter.getDatas(), data, adapter);
        }
    }

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
            case 0x123:
                initList(GsonUtil.getInstance().toList(data, "warningList", WarningListBean.class));
                break;
        }
    }

    @Override
    protected void refreshData() {
        addRequest(binder.real_time(id, this));
    }
}
