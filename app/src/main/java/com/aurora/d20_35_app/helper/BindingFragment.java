package com.aurora.d20_35_app.helper;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import lombok.Getter;
import lombok.NonNull;

public abstract class BindingFragment<T extends ViewDataBinding, V extends ActivityViewModel> extends Fragment {

    @Getter
    private BindingActivity mActivity;
    @Getter
    private View mRootView;
    @Getter
    private T mViewDataBinding;
    @Getter
    private V mActivityViewModel;

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    public abstract int getBindingVariable();

    /**
     * @return layout resource id
     */
    public abstract
    @LayoutRes
    int getLayoutId();


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BindingActivity) {
            BindingActivity activity = (BindingActivity) context;
            this.mActivity = activity;
            mActivityViewModel = (V) getMActivity().getMActivityViewModel();
            activity.onFragmentAttached();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        performDependencyInjection();
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        mRootView = mViewDataBinding.getRoot();
        setTranslatedTexts();
        return mRootView;
    }

    protected abstract void setTranslatedTexts();

    @Override
    public void onDetach() {
        mActivity = null;
        super.onDetach();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewDataBinding.setVariable(getBindingVariable(), mActivityViewModel);
        mViewDataBinding.executePendingBindings();
    }

    public void hideKeyboard() {
        if (mActivity != null) {
            mActivity.hideKeyboard();
        }
    }

    public boolean isNetworkConnected() {
        return mActivity != null && mActivity.isNetworkConnected();
    }

    private void performDependencyInjection() {
        //AndroidSupportInjection.inject(this); todo learn and fix
    }

    public interface Callback {

        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }

}
