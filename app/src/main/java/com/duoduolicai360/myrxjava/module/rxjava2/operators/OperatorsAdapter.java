package com.duoduolicai360.myrxjava.module.rxjava2.operators;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.duoduolicai360.myrxjava.R;
import com.duoduolicai360.myrxjava.model.OperatorModel;

import java.util.List;

/**
 * Created by Administrator on 2017/8/22.
 */

public abstract class OperatorsAdapter extends BaseQuickAdapter<OperatorModel,BaseViewHolder> {

    public OperatorsAdapter(@Nullable List<OperatorModel> data) {
        super(R.layout.layout_item_operator,data);
    }

    @Override
    protected void convert(final BaseViewHolder holder, OperatorModel item) {
        if (item != null){
            holder.setText(R.id.item_title,item.title)
                    .setText(R.id.item_des,item.des)
                    .getConvertView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClick(holder.getAdapterPosition());
                }
            });
        }
    }

    protected abstract void onItemClick(int adapterPosition);
}
