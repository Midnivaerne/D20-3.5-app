package com.aurora.d20_35_app.viewModels;

import android.os.AsyncTask;

import com.aurora.d20_35_app.helper.ActivityViewModel;
import com.aurora.d20_35_app.interfaces.CustomCallback;
import com.aurora.d20_35_app.utilsDatabase.DatabaseManager;
import com.aurora.d20_35_app.views.D2035appActivity;

import java.util.concurrent.ExecutionException;

public class D2035appVM extends ActivityViewModel<D2035appActivity> {

    public D2035appVM(D2035appActivity activity) {
        super(activity);
    }

    public void initializeDB(CustomCallback callback) {
        InitializeDbAsync initializeDbAsync = new InitializeDbAsync(this, callback);
        try {
            initializeDbAsync.execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class InitializeDbAsync extends AsyncTask<Void, Void, Void> {
        private final D2035appVM d2035appVM;
        private CustomCallback customCallback;

        private InitializeDbAsync(D2035appVM d2035appVM, CustomCallback customCallback) {
            this.d2035appVM = d2035appVM;
            this.customCallback = customCallback;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            DatabaseManager.initialDatabasesResolver(d2035appVM.getActivity());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void par) {
            customCallback.onDatabaseInitialised();
        }
    }
}
