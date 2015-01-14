package com.zengye.qq_demo.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zengye.qq_demo.R;

/**
 * Created by colour on 2015/1/13.
 */
public class LeftFragment extends BaseFragment {
    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_left,container,false);
    }
}
