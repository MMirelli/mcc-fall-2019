package mcc.group14.apiclientapp.views.projects.dashboard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.content.Context;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import java.util.ArrayList;


import android.content.Intent;

import android.util.Log;
import android.widget.Toast;

import mcc.group14.apiclientapp.R;
import mcc.group14.apiclientapp.api.APIInterfaceJava;
import mcc.group14.apiclientapp.api.ProjectAPIJava;

import mcc.group14.apiclientapp.data.projects.ProjectsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import mcc.group14.apiclientapp.views.projects.tasks.TaskDashboard;

public class ProjectsHomeFragment extends Fragment {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ProjectsResponse data;
    static View.OnClickListener myOnClickListener;
    private static ArrayList<ProjectCard> passToAdapter;
    private String userEmail,userAuth;

    private Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
    }




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project_home, container, false);

        myOnClickListener = new MyOnClickListener(mContext);
        ProgressBar spinner = (ProgressBar) view.findViewById(R.id.progressBar);
        recyclerView = view.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //======================Read Data================================

        Intent intent = getActivity().getIntent();

        // get the info from login/sign-up
        userEmail = intent.getStringExtra("USER_EMAIL");
        userAuth = intent.getStringExtra("USER_AUTH");



        APIInterfaceJava apiInterface = ProjectAPIJava.getClient().create(APIInterfaceJava.class);
        try{
            Call<ProjectsResponse> call = apiInterface.doGetListProjects(userEmail);

            call.enqueue(new Callback<ProjectsResponse>() {
                @Override
                public void onResponse(Call<ProjectsResponse> call, Response<ProjectsResponse> response) {


                    Log.d("TAG",response.code()+"");


                data = response.body();
                passToAdapter = new ArrayList<>();
                for(ProjectsResponse.Payload d:data.payload ){
                    ProjectCard pCard = new ProjectCard();
                    pCard.projectName = d.name;
                    pCard.lastModified = d.last_modified;
                    pCard.projectType = d.project_type;
                    pCard.badge = d.badge;
                    pCard.project_id = d.project_id;
                    pCard.team_member = d.team_members;
                    pCard.requester_email = d.requester_email;
                    pCard.creation_time = d.creation_time;
                    passToAdapter.add(pCard);
                }
                    spinner.setVisibility(View.INVISIBLE);

                adapter = new CustomAdapter(passToAdapter,mContext,userEmail,userAuth);
                recyclerView.setAdapter(adapter);

                }

                @Override
                public void onFailure(Call<ProjectsResponse> call, Throwable t) {
                    call.cancel();
                    spinner.setVisibility(View.INVISIBLE);
                    Toast.makeText(mContext, "Oops! Something went wrong. Please try again", Toast.LENGTH_LONG).show();
                }
            });
        }
        catch (Exception e){
            spinner.setVisibility(View.INVISIBLE);
            Toast.makeText(mContext, "Oops! Something went wrong. Please try again", Toast.LENGTH_LONG).show();
        }



        //================================================================

        return view;


    }


    private static class MyOnClickListener implements View.OnClickListener {

        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            //removeItem(v);

            Intent taskActivity = new Intent(context, TaskDashboard.class);

            context.startActivity(taskActivity);

        }


    }



}
