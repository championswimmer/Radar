package airtel.comviva.mahindra.radar.fragment;


import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import airtel.comviva.mahindra.phonytale.Caller;
import airtel.comviva.mahindra.radar.R;
import airtel.comviva.mahindra.radar.db.DbManager;
import airtel.comviva.mahindra.radar.db.tables.TableCallTasks;
import airtel.comviva.mahindra.radar.models.CallTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowCallTaskFragment extends BaseRadarFragment {

    RecyclerView callTaskRecyclerView;
    ArrayList<CallTask> callTasks;
    SQLiteDatabase db;
    CallTaskAdapter ctAdapter;

    public ShowCallTaskFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_show_call_task, container, false);

        db = (new DbManager(getActivity())).getReadableDatabase();

        callTaskRecyclerView = (RecyclerView) rootView.findViewById(R.id.call_task_list);
        callTasks = TableCallTasks.getAllTasks(db);

        ctAdapter = new CallTaskAdapter();
        callTaskRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        callTaskRecyclerView.setAdapter(ctAdapter);

        return rootView;
    }

    public String getTitle () {
        return "Call";
    }

    public class CallTaskHolder extends RecyclerView.ViewHolder {

        public TextView tvRecipient, tvInterval;
        public View itemView;

        public CallTaskHolder(View itemView) {
            super(itemView);

            tvRecipient = (TextView) itemView.findViewById(R.id.tv_recipient);
            tvInterval = (TextView) itemView.findViewById(R.id.tv_interval);
            this.itemView = itemView;
        }


    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && (db != null)) {
            refreshList();
        }
    }

    @Override
    public void onDestroyView() {
        db.close();
        super.onDestroyView();
    }


    private void refreshList () {
        callTasks = TableCallTasks.getAllTasks(db);
        ctAdapter.notifyDataSetChanged();
    }
    public class CallTaskAdapter extends RecyclerView.Adapter<CallTaskHolder> {



        @Override
        public CallTaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater li = LayoutInflater.from(getActivity());
            View convertView = li.inflate(R.layout.list_item_call_task, parent, false);
            return new CallTaskHolder(convertView);
        }

        @Override
        public void onBindViewHolder(final CallTaskHolder holder, int position) {

            holder.tvInterval.setText(String.valueOf(callTasks.get(position).getInterval()));
            holder.tvRecipient.setText(callTasks.get(position).getRecipient());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Delete task")
                            .setTitle("Are you sure you want to delete this task ?")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Caller.cancelCallingTask(
                                            callTasks.get(holder.getAdapterPosition()).getRecipient(),
                                            callTasks.get(holder.getAdapterPosition()).getInterval(),
                                            getActivity()
                                    );
                                    TableCallTasks.deleteTask(db, callTasks.get(holder.getAdapterPosition()).getId());
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
            return callTasks.size();
        }
    }

}
