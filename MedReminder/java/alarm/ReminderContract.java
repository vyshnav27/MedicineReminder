package com.gautam.medreminder.alarm;

import com.gautam.medreminder.BasePresenter;
import com.gautam.medreminder.BaseView;
import com.gautam.medreminder.data.source.History;
import com.gautam.medreminder.data.source.MedicineAlarm;


public interface ReminderContract {

    interface View extends BaseView<Presenter> {

        void showMedicine(MedicineAlarm medicineAlarm);

        void showNoData();

        boolean isActive();

        void onFinish();

    }

    interface Presenter extends BasePresenter {

        void finishActivity();

        void onStart(long id);

        void loadMedicineById(long id);

        void addPillsToHistory(History history);

    }
}
