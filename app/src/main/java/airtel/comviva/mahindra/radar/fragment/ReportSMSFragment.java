package airtel.comviva.mahindra.radar.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import airtel.comviva.mahindra.radar.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReportSMSFragment extends BaseRadarFragment {


    public ReportSMSFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_report_sm, container, false);
    }

    public String getTitle () {
        return "SMS";
    }

}
