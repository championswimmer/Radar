package airtel.comviva.mahindra.radar.fragment;


import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import airtel.comviva.mahindra.phonytale.SmsSender;
import airtel.comviva.mahindra.radar.R;
import airtel.comviva.mahindra.radar.db.DbManager;
import airtel.comviva.mahindra.radar.db.tables.TableSMSReport;
import airtel.comviva.mahindra.radar.db.tables.TableSMSTasks;
import airtel.comviva.mahindra.radar.models.SMSReportItem;
import airtel.comviva.mahindra.radar.models.SMSTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReportSMSFragment extends BaseRadarFragment {

    public static final String TAG = "ReportSMS";


    RecyclerView smsReportRecyclerView;
    ArrayList<SMSReportItem> smsReports;
    SQLiteDatabase db;
    SMSReportAdapter srAdapter;


    public ReportSMSFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_report_sms, container, false);
        db = (new DbManager(getActivity())).getReadableDatabase();

        smsReportRecyclerView = (RecyclerView) rootView.findViewById(R.id.sms_report_list);
        smsReports = TableSMSReport.getAllReports(db);

        srAdapter = new SMSReportAdapter();
        smsReportRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        smsReportRecyclerView.setAdapter(srAdapter);


        return rootView;
    }

    @Override
    public void onDestroyView() {
        db.close();
        super.onDestroyView();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && (db != null)) {
            refreshList();
        }
    }

    private void refreshList () {
        smsReports = TableSMSReport.getAllReports(db);
        srAdapter.notifyDataSetChanged();
    }

    public String getTitle () {
        return "SMS";
    }

    public class SMSReportHolder extends RecyclerView.ViewHolder {

        public TextView tvRecipient, tvStatus, tvMessage, tvMsgid;
        public View itemView;

        public SMSReportHolder(View itemView) {
            super(itemView);

            tvRecipient = (TextView) itemView.findViewById(R.id.tv_recipient);
            tvStatus = (TextView) itemView.findViewById(R.id.tv_status);
            tvMessage = (TextView) itemView.findViewById(R.id.tv_msg_content);
            tvMsgid = (TextView) itemView.findViewById(R.id.tv_msg_id);
            this.itemView = itemView;
        }


    }
    public class SMSReportAdapter extends RecyclerView.Adapter<SMSReportHolder> {



        @Override
        public SMSReportHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater li = LayoutInflater.from(getActivity());
            View convertView = li.inflate(R.layout.list_item_sms_report, parent, false);
            return new SMSReportHolder(convertView);
        }

        @Override
        public void onBindViewHolder(SMSReportHolder holder, int position) {

            holder.tvStatus.setText(smsReports.get(position).getStatusString());
            holder.tvRecipient.setText(smsReports.get(position).getRecipient());
            holder.tvMessage.setText(smsReports.get(position).getMessage());
            holder.tvMsgid.setText(String.valueOf(smsReports.get(position).getSmsId()));

        }

        @Override
        public int getItemCount() {
            return smsReports.size();
        }
    }

}
