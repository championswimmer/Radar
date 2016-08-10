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

import airtel.comviva.mahindra.phonytale.SmsSender;
import airtel.comviva.mahindra.phonytale.services.SmsSendService;
import airtel.comviva.mahindra.radar.R;
import airtel.comviva.mahindra.radar.db.DbManager;
import airtel.comviva.mahindra.radar.db.tables.TableSMSTasks;
import airtel.comviva.mahindra.radar.services.RadarSmsSendService;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConfigureSMSTaskFragment extends BaseRadarFragment {

    public static final String TAG = "ConfSMS";

    EditText etRecipient, etInterval, etMsgContents;
    Button btnCreate;


    public ConfigureSMSTaskFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView: ");
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_configure_smstask, container, false);
        etRecipient = (EditText) rootView.findViewById(R.id.et_recipient);
        etInterval = (EditText) rootView.findViewById(R.id.et_interval);
        etMsgContents = (EditText) rootView.findViewById(R.id.et_msg_content);
        btnCreate = (Button) rootView.findViewById(R.id.btn_create_sms);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ");
                String recipient = etRecipient.getText().toString();
                String msgContent = etMsgContents.getText().toString();
                int interval = Integer.valueOf(etInterval.getText().toString());

                SmsSender.createSmsSendingTask(
                        recipient,
                        msgContent,
                        interval,
                        getActivity(),
                        RadarSmsSendService.class
                );

                SQLiteDatabase db = new DbManager(getActivity()).getWritableDatabase();
                TableSMSTasks.addTask(db, interval, recipient);
            }
        });


        return rootView;
    }

    public String getTitle () {
        return "SMS";
    }

}
