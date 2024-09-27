package com.aurora.core.helper;

import lombok.Getter;
import lombok.NonNull;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;

public class BaseDialog extends BindingFragment {

  @Getter
  private BindingActivity fragmentBindingActivity;

  @Override
  public int getBindingVariable() {
    return 0;
  }

  @Override
  public int getLayoutId() {
    return 0;
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof BindingActivity) {
      BindingActivity bindingActivity = (BindingActivity) context;
      this.fragmentBindingActivity = bindingActivity;
      bindingActivity.onFragmentAttached();
    }
  }

  @Override
  protected void setTranslatedTexts() {

  }

  @NonNull
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    // the content
    final RelativeLayout root = new RelativeLayout(getActivity());
    root.setLayoutParams(new ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT));

    // creating the fullscreen dialog
    final Dialog dialog = new Dialog(getContext());
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    dialog.setContentView(root);
    if (dialog.getWindow() != null) {
      dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
      dialog.getWindow().setLayout(
          ViewGroup.LayoutParams.MATCH_PARENT,
          ViewGroup.LayoutParams.WRAP_CONTENT);
    }
    dialog.setCanceledOnTouchOutside(false);

    return dialog;
  }

  @Override
  public void onDetach() {
    fragmentBindingActivity = null;
    super.onDetach();
  }

  public void show(FragmentManager fragmentManager, String tag) {
    FragmentTransaction transaction = fragmentManager.beginTransaction();
    Fragment prevFragment = fragmentManager.findFragmentByTag(tag);
    if (prevFragment != null) {
      transaction.remove(prevFragment);
    }
    transaction.addToBackStack(null);
    show(fragmentManager, tag); //was transaction instead of fragmentManager
  }

  public void hideKeyboard() {
    if (fragmentBindingActivity != null) {
      fragmentBindingActivity.hideKeyboard();
    }
  }

  public void hideLoading() {
    if (fragmentBindingActivity != null) {
      fragmentBindingActivity.hideLoading();
    }
  }

  public boolean isNetworkConnected() {
    return fragmentBindingActivity != null && fragmentBindingActivity.isNetworkConnected();
  }


  public void showLoading() {
    if (fragmentBindingActivity != null) {
      fragmentBindingActivity.showLoading();
    }
  }

}
