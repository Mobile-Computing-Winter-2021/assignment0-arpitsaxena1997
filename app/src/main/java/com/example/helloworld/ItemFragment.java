package com.example.helloworld;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class ItemFragment extends Fragment {

    List<Stud> studList;
    View globalView ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        globalView = view;
        studList = StudentData.get().getStudList();

        // Set the adapter

        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new MyItemRecyclerViewAdapter(studList));

        return view;
    }

    @Override
    public void onResume() {

        /*//getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        Fragment frg = getFragmentManager().findFragmentByTag("Fragment");
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(frg);
        ft.attach(frg);
        ft.commit();*/
        super.onResume();/*
        MyItemRecyclerViewAdapter adapter = null;
        adapter = new MyItemRecyclerViewAdapter(studList);
        adapter.notifyDataSetChanged();*/


        Context context = globalView.getContext();
        RecyclerView recyclerView = (RecyclerView) globalView.findViewById(R.id.list);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new MyItemRecyclerViewAdapter(studList));


    }

    private class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

        List<Stud> studList;

        public MyItemRecyclerViewAdapter(List<Stud> studList ) {
            this.studList = studList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mRollNo.setText(studList.get(position).dRollNo);
            holder.mName.setText(studList.get(position).dName);

            holder.rowLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), SecondActivity.class);
                    intent.putExtra("rollNo", studList.get(position).dRollNo);
                    intent.putExtra("name", studList.get(position).dName);
                    intent.putExtra("email", studList.get(position).dEid);
                    intent.putExtra("dept", studList.get(position).dDept);
                    intent.putExtra("pos", position);


                    startActivityForResult(intent, 0);

                    MyItemRecyclerViewAdapter adapter = null;
                    adapter = new MyItemRecyclerViewAdapter(studList);
                    adapter.notifyDataSetChanged();
                }
            });
        }

        @Override
        public int getItemCount() {
            return studList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView mRollNo;
            public TextView mName;
            LinearLayout rowLayout;


            public ViewHolder(View view) {
                super(view);
                mRollNo = (TextView) view.findViewById(R.id.roll_number);
                mName = (TextView) view.findViewById(R.id.name);

                rowLayout = view.findViewById(R.id.rowLayout);

            }
        }
    }

}