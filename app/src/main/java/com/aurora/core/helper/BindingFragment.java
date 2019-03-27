package com.aurora.core.helper;

import lombok.Getter;
import lombok.NonNull;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BindingFragment<T extends ViewDataBinding, V extends ActivityViewModel> extends Fragment {

  @Getter
  private BindingActivity bindingActivity;
  @Getter
  private View rootView;
  @Getter
  private T viewDataBinding;
  @Getter
  private V activityViewModel;

  /**
   * Override for set binding variable.
   *
   * @return variable id
   */
  public abstract int getBindingVariable();

  /**
   * Abstract method for getting LayoutId.
   *
   * @return layout resource id
   */
  @LayoutRes
  public abstract int getLayoutId();


  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof BindingActivity) {
      BindingActivity activity = (BindingActivity) context;
      this.bindingActivity = activity;
      activityViewModel = (V) getBindingActivity().getExActivityViewModel();
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
    viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
    rootView = viewDataBinding.getRoot();
    setTranslatedTexts();
    return rootView;
  }

  protected abstract void setTranslatedTexts();

  @Override
  public void onDetach() {
    bindingActivity = null;
    super.onDetach();
  }

  @Override
  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewDataBinding.setVariable(getBindingVariable(), activityViewModel);
    viewDataBinding.executePendingBindings();
  }

  public void hideKeyboard() {
    if (bindingActivity != null) {
      bindingActivity.hideKeyboard();
    }
  }

  public boolean isNetworkConnected() {
    return bindingActivity != null && bindingActivity.isNetworkConnected();
  }

  private void performDependencyInjection() {
    //AndroidSupportInjection.inject(this); todo learn and fix
  }

  public interface Callback {

    void onFragmentAttached();

    void onFragmentDetached(String tag);
  }

}
