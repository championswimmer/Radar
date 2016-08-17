package airtel.comviva.mahindra.radar.fragment;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import airtel.comviva.mahindra.radar.R;
import airtel.comviva.mahindra.radar.db.DbManager;
import airtel.comviva.mahindra.radar.db.tables.TableSMSReport;
import airtel.comviva.mahindra.radar.db.tables.TableUSSDReport;
import airtel.comviva.mahindra.radar.models.USSDReportItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReportUSSDFragment extends BaseRadarFragment {


    public static final String TAG = "ReportUSSD";


    RecyclerView ussdReportRecyclerView;
    ArrayList<USSDReportItem> ussdReports;
    SQLiteDatabase db;
    USSDReportAdapter urAdapter;
    public ReportUSSDFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_report_ussd, container, false);
        db = (new DbManager(getActivity())).getReadableDatabase();

        ussdReportRecyclerView = (RecyclerView) rootView.findViewById(R.id.ussd_report_list);
        ussdReports = TableUSSDReport.getAllReports(db);

        urAdapter = new USSDReportAdapter();
        ussdReportRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ussdReportRecyclerView.setAdapter(urAdapter);

        return rootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && (db != null)) {
            refreshList();
        }
    }

    private void refreshList () {
        ussdReports = TableUSSDReport.getAllReports(db);
        urAdapter.notifyDataSetChanged();
    }


    @Override
    public String getTitle() {
        return "USSD";
    }


    public class USSDReportHolder extends RecyclerView.ViewHolder {

        public TextView tvUSSDCode, tvTimestamp;
        public View itemView;

        public USSDReportHolder(View itemView) {
            super(itemView);

            tvUSSDCode = (TextView) itemView.findViewById(R.id.tv_ussd_code);
            tvTimestamp = (TextView) itemView.findViewById(R.id.tv_timestamp);
            this.itemView = itemView;
        }


    }
    public class USSDReportAdapter extends RecyclerView.Adapter<USSDReportHolder> {



        @Override
        public USSDReportHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater li = LayoutInflater.from(getActivity());
            View convertView = li.inflate(R.layout.list_item_ussd_report, parent, false);
            return new USSDReportHolder(convertView);
        }

        @Override
        public void onBindViewHolder(USSDReportHolder holder, int position) {

            holder.tvUSSDCode.setText(ussdReports.get(position).getUssdCode());
            Log.d(TAG, "onBindViewHolder: " + ussdReports.get(position).getTimeStamp());
            holder.tvTimestamp.setText(String.valueOf(ussdReports.get(position).getTimeStamp()));

        }

        @Override
        public int getItemCount() {
            return ussdReports.size();
        }
    }
}
