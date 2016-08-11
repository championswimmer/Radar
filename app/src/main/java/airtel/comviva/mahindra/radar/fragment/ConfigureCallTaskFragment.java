package airtel.comviva.mahindra.radar.fragment;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import airtel.comviva.mahindra.phonytale.Caller;
import airtel.comviva.mahindra.phonytale.SmsSender;
import airtel.comviva.mahindra.radar.R;
import airtel.comviva.mahindra.radar.db.DbManager;
import airtel.comviva.mahindra.radar.db.tables.TableCallTasks;
import airtel.comviva.mahindra.radar.db.tables.TableSMSTasks;
import airtel.comviva.mahindra.radar.services.RadarSmsSendService;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConfigureCallTaskFragment extends BaseRadarFragment {

    public static final String TAG = "ConfCall";

    EditText etRecipient, etInterval;
    Button btnCreate;


    public ConfigureCallTaskFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_configure_call_task, container, false);

        etRecipient = (EditText) rootView.findViewById(R.id.et_recipient);
        etInterval = (EditText) rootView.findViewById(R.id.et_interval);
        btnCreate = (Button) rootView.findViewById(R.id.btn_create_sms);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ");
                String recipient = etRecipient.getText().toString();
                int interval = Integer.valueOf(etInterval.getText().toString());

                Caller.createCallingTask(
                        recipient,
                        interval,
                        getActivity()
                );

                SQLiteDatabase db = new DbManager(getActivity()).getWritableDatabase();
                TableCallTasks.addTask(db, interval, recipient);
            }
        });


        return rootView;
    }

    public String getTitle () {
        return "Call";
    }

}
