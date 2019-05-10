package com.teaching.evaluation;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.teaching.evaluation.bean.User;
import com.teaching.evaluation.manager.LoginManager;
import com.teaching.evaluation.view.CaluDialog;
import com.teaching.evaluation.view.DaliyDialog;
import com.teaching.evaluation.view.EditCourseDialog;
import com.teaching.evaluation.view.HelpDialog;
import com.teaching.evaluation.view.OpreateCourseDialog;
import com.teaching.evaluation.view.MyInfoDialog;
import com.teaching.evaluation.view.StudyDialog;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private TextView mUserName;
    private LinearLayout mEditCourseBtn;
    private LinearLayout mInsertCourseBtn;

    private Button mMyInfo;
    private Button mMyStudy;
    private Button mDaily;
    private Button mCalculator;
    private Button mHelp;
    private Button mInserCourse;
    private Button mEditCourse;

    private Button mExApp;

    public MyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyFragment newInstance(String param1, String param2) {
        MyFragment fragment = new MyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my, container, false);

        mUserName = (TextView) view.findViewById(R.id.user_name);
        mEditCourseBtn = (LinearLayout)view.findViewById(R.id.edit_cousr_layout);
        mInsertCourseBtn = (LinearLayout)view.findViewById(R.id.insert_cousr_layout);
        mExApp = (Button)view.findViewById(R.id.exit_app_btn);
        mMyInfo = (Button)view.findViewById(R.id.my_info);
        mMyStudy = (Button)view.findViewById(R.id.my_study);
        mDaily = (Button)view.findViewById(R.id.my_daily);
        mCalculator = (Button)view.findViewById(R.id.my_calculator);
        mHelp = (Button)view.findViewById(R.id.my_help);
        mInserCourse = (Button)view.findViewById(R.id.insert_course);
        mEditCourse = (Button)view.findViewById(R.id.edit_course);

        User user = LoginManager.getInstance(getContext()).getUser();

        mUserName.setText(user.getName());

        if (user.getRole().equals("老师"))
        {
            mEditCourseBtn.setVisibility(View.VISIBLE);
            mInsertCourseBtn.setVisibility(View.VISIBLE);
        }

        mMyInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyInfoDialog dialog = new MyInfoDialog(getContext(),R.layout.my_info,true,true);
                dialog.show();
            }
        });

        mMyStudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StudyDialog dialog = new StudyDialog(getContext(),R.layout.activity_guide,true,true);
                dialog.show();
            }
        });

        mDaily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DaliyDialog dialog = new DaliyDialog(getContext(),R.layout.activity_guide,true,true);
                dialog.show();
            }
        });

        mCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CaluDialog dialog = new CaluDialog(getContext(),R.layout.activity_guide,true,true);
                dialog.show();
            }
        });
        mHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HelpDialog dialog = new HelpDialog(getContext(),R.layout.activity_guide,true,true);
                dialog.show();
            }
        });

        mInserCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpreateCourseDialog dialog = new OpreateCourseDialog(getContext(),"insert",R.layout.insert_course,true,true);
                dialog.show();
            }
        });

        mEditCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpreateCourseDialog dialog = new OpreateCourseDialog(getContext(),"edit",R.layout.insert_course,true,true);
                dialog.show();
            }
        });
        mExApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance(getContext()).logout();
                getActivity().finish();
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
