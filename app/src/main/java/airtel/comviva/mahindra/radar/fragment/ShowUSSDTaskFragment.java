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

import airtel.comviva.mahindra.phonytale.USSDSender;
import airtel.comviva.mahindra.radar.R;
import airtel.comviva.mahindra.radar.db.DbManager;
import airtel.comviva.mahindra.radar.db.tables.TableUSSDTasks;
import airtel.comviva.mahindra.radar.models.USSDTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowUSSDTaskFragment extends BaseRadarFragment {


    RecyclerView ussdTaskRecyclerView;
    ArrayList<USSDTask> ussdTasks;
    SQLiteDatabase db;
    USSDTaskAdapter ctAdapter;


    public ShowUSSDTaskFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_show_ussdtask, container, false);

        db = (new DbManager(getActivity())).getReadableDatabase();

        ussdTaskRecyclerView = (RecyclerView) rootView.findViewById(R.id.ussd_task_list);
        ussdTasks = TableUSSDTasks.getAllTasks(db);

        ctAdapter = new USSDTaskAdapter();
        ussdTaskRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        ussdTaskRecyclerView.setAdapter(ctAdapter);

        return rootView;
    }
    public class USSDTaskHolder extends RecyclerView.ViewHolder {

        public TextView tvRecipient, tvInterval;
        public View itemView;

        public USSDTaskHolder(View itemView) {
            super(itemView);

            tvRecipient = (TextView) itemView.findViewById(R.id.tv_recipient);
            tvInterval = (TextView) itemView.findViewById(R.id.tv_interval);
            this.itemView = itemView;
        }


    }


    @Override
    public void onDestroyView() {

        super.onDestroyView();
    }


    private void refreshList () {
        ussdTasks = TableUSSDTasks.getAllTasks(db);
        ctAdapter.notifyDataSetChanged();
    }

    @Override
    public String getTitle() {
        return "USSD";
    }

    public class USSDTaskAdapter extends RecyclerView.Adapter<USSDTaskHolder> {



        @Override
        public USSDTaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater li = LayoutInflater.from(getActivity());
            View convertView = li.inflate(R.layout.list_item_ussd_task, parent, false);
            return new USSDTaskHolder(convertView);
        }

        @Override
        public void onBindViewHolder(final USSDTaskHolder holder, int position) {

            holder.tvInterval.setText(String.valueOf(ussdTasks.get(position).getInterval()));
            holder.tvRecipient.setText(ussdTasks.get(position).getRecipient());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Delete task")
                            .setTitle("Are you sure you want to delete this task ?")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    USSDSender.cancelUSSDSendTask(
                                            ussdTasks.get(holder.getAdapterPosition()).getRecipient(),
                                            ussdTasks.get(holder.getAdapterPosition()).getInterval(),
                                            getActivity()
                                    );
                                    TableUSSDTasks.deleteTask(db, ussdTasks.get(holder.getAdapterPosition()).getId());
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
            return ussdTasks.size();
        }
    }


}
