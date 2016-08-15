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
import airtel.comviva.mahindra.radar.db.tables.TableSMSTasks;
import airtel.comviva.mahindra.radar.models.SMSTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowSMSTaskFragment extends BaseRadarFragment {

    public static final String TAG = "SMSTasks";

    RecyclerView smsTaskRecyclerView;
    ArrayList<SMSTask> smsTasks;
    SQLiteDatabase db;
    SmsTaskAdapter stAdapter;



    public ShowSMSTaskFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_show_smstask, container, false);
        db = (new DbManager(getActivity())).getReadableDatabase();

        smsTaskRecyclerView = (RecyclerView) rootView.findViewById(R.id.sms_task_list);
        smsTasks = TableSMSTasks.getAllTasks(db);

        stAdapter = new SmsTaskAdapter();
        smsTaskRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        smsTaskRecyclerView.setAdapter(stAdapter);


        return rootView;
    }

    @Override
    public void onDestroyView() {

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
        smsTasks = TableSMSTasks.getAllTasks(db);
        stAdapter.notifyDataSetChanged();
    }

    public String getTitle () {
        return "SMS";
    }

    public class SmsTaskHolder extends RecyclerView.ViewHolder {

        public TextView tvRecipient, tvInterval, tvMessage;
        public View itemView;

        public SmsTaskHolder(View itemView) {
            super(itemView);

            tvRecipient = (TextView) itemView.findViewById(R.id.tv_recipient);
            tvInterval = (TextView) itemView.findViewById(R.id.tv_interval);
            tvMessage = (TextView) itemView.findViewById(R.id.tv_msg_content);
            this.itemView = itemView;
        }


    }
    public class SmsTaskAdapter extends RecyclerView.Adapter<SmsTaskHolder> {



        @Override
        public SmsTaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater li = LayoutInflater.from(getActivity());
            View convertView = li.inflate(R.layout.list_item_sms_task, parent, false);
            return new SmsTaskHolder(convertView);
        }

        @Override
        public void onBindViewHolder(final SmsTaskHolder holder, int position) {

            holder.tvInterval.setText(String.valueOf(smsTasks.get(position).getInterval()));
            holder.tvRecipient.setText(smsTasks.get(position).getRecipient());
            holder.tvMessage.setText(smsTasks.get(position).getMessage());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Delete task")
                            .setTitle("Are you sure you want to delete this task ?")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    SmsSender.cancelSmsSendingTask(
                                            smsTasks.get(holder.getAdapterPosition()).getRecipient(),
                                            smsTasks.get(holder.getAdapterPosition()).getMessage(),
                                            smsTasks.get(holder.getAdapterPosition()).getInterval(),
                                            getActivity()
                                    );
                                    TableSMSTasks.deleteTask(db, smsTasks.get(holder.getAdapterPosition()).getId());
                                    refreshList();
                                }
                            })
                            .setCancelable(true)
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .show();
                }
            });

        }

        @Override
        public int getItemCount() {
            return smsTasks.size();
        }
    }

}
