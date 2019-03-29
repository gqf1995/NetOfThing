package com.netofthing.mvp.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.fivefivelike.mybaselibrary.base.BasePullActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.netofthing.adapter.ComponentesAdapter;
import com.netofthing.entity.bean.ThingPmBean;
import com.netofthing.mvp.databinder.BaseActivityPullBinder;
import com.netofthing.mvp.delegate.BaseActivityPullDelegate;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ComponentesListActivity extends BasePullActivity<BaseActivityPullDelegate, BaseActivityPullBinder> {
    ComponentesAdapter adapter;

    @Override
    protected Class<BaseActivityPullDelegate> getDelegateClass() {
        return BaseActivityPullDelegate.class;
    }

    @Override
    public BaseActivityPullBinder getDataBinder(BaseActivityPullDelegate viewDelegate) {
        return new BaseActivityPullBinder(viewDelegate);
    }


    @Override
    protected void bindEvenListener() {
        super.bindEvenListener();
        getIntentData();
        initToolbar(new ToolbarBuilder().setTitle(type));
        initList(new ArrayList<ThingPmBean>());
        viewDelegate.viewHolder.swipeRefreshLayout.setRefreshing(true);
        onRefresh();
        Disposable subscribe = Observable.interval(60, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onTerminateDetach()
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        if (ActivityUtils.getTopActivity() != null&&
                                ActivityUtils.getTopActivity() instanceof ComponentesListActivity) {
                            onRefresh();
                        }
                    }
                });
        addRequest(subscribe);
    }

    public static void startAct(Activity activity,
                                String id,
                                String type) {
        Intent intent = new Intent(activity, ComponentesListActivity.class);
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

    private void initList(List<ThingPmBean> data) {
        if (adapter == null) {
            adapter = new ComponentesAdapter(this, data);
            adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

                }

                @Override
                public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                    return false;
                }
            });
            initRecycleViewPull(adapter, new LinearLayoutManager(this));
        } else {
            Collections.sort(data, new Comparator<ThingPmBean>() {
                public int compare(ThingPmBean o1, ThingPmBean o2) {
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
                initList(GsonUtil.getInstance().toList(data,"warningList", ThingPmBean.class));
                break;
        }
    }

    @Override
    protected void refreshData() {
        addRequest(binder.equipment_real_time(id, this));
    }
}
