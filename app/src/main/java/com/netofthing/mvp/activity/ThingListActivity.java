package com.netofthing.mvp.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fivefivelike.mybaselibrary.base.BasePullActivity;
import com.fivefivelike.mybaselibrary.entity.ToolbarBuilder;
import com.fivefivelike.mybaselibrary.utils.GsonUtil;
import com.netofthing.adapter.ProductionLineAdapter;
import com.netofthing.entity.bean.ThingBean;
import com.netofthing.entity.bean.ThingKlineBean;
import com.netofthing.entity.bean.ThingLineBean;
import com.netofthing.mvp.databinder.BaseActivityPullBinder;
import com.netofthing.mvp.delegate.BaseActivityPullDelegate;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

public class ThingListActivity extends BasePullActivity<BaseActivityPullDelegate, BaseActivityPullBinder> {
    ProductionLineAdapter adapter;

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
        initToolbar(new ToolbarBuilder().setTitle("中国水泥").setShowBack(false));
        initList(new ArrayList<ThingBean>());
        viewDelegate.viewHolder.swipeRefreshLayout.setRefreshing(true);
        onRefresh();
    }

    private void initList(List<ThingBean> data) {
        if (adapter == null) {
            adapter = new ProductionLineAdapter(this, data);
            adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                    MachineListActivity.startAct(viewDelegate.getActivity(),
                            adapter.getDatas().get(position).getId(),
                            adapter.getDatas().get(position).getProLineName());
                }

                @Override
                public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                    return false;
                }
            });
            viewDelegate.setIsLoadMore(false);
            initRecycleViewPull(adapter, new LinearLayoutManager(this));
        } else {
            adapter.setHight(viewDelegate.viewHolder.pull_recycleview.getMeasuredHeight() / 2);
            for (int i = 0; i < data.size(); i++) {
                addRequest(binder.k_data(data.get(i).getId(), 900 + i, this));
                addRequest(binder.real_time(data.get(i).getId(), i, this));
            }
            getDataBack(adapter.getDatas(), data, adapter);
        }
    }

    @Override
    protected void onServiceSuccess(String data, String info, int status, int requestCode) {
        switch (requestCode) {
            case 0x999:
                initList(GsonUtil.getInstance().toList(data, ThingBean.class));
                break;
            default:
                if (adapter != null) {
                    if (requestCode >= 900) {
                        adapter.getDatas().get(requestCode - 900).setkData(GsonUtil.getInstance().toList(data, ThingKlineBean.class));
                        if (adapter.getDatas().get(requestCode - 900).getData() != null
                                && adapter.getDatas().get(requestCode - 900).getkData() != null) {
                            adapter.notifyItemChanged(requestCode - 900);
                        }
                    } else {
                        adapter.getDatas().get(requestCode).setData(GsonUtil.getInstance().toObj(data, ThingLineBean.class));
                        if (adapter.getDatas().get(requestCode).getData() != null
                                && adapter.getDatas().get(requestCode).getkData() != null) {
                            adapter.notifyItemChanged(requestCode);
                        }
                    }
                }
                break;
        }
    }

    @Override
    protected void refreshData() {
        addRequest(binder.pro_line_list(this));
    }
}
