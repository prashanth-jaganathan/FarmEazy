package com.example.farmeazy2;

import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> author = new ArrayList<>();
    ArrayList<String> title = new ArrayList<>();
    ArrayList<String> content = new ArrayList<>();
    LayoutInflater inflater;

    public CustomAdapter(Context context, ArrayList<String> title, ArrayList<String> content, ArrayList<String> author){
        this.context = context;
        this.title = title;
        this.content = content;
        this.inflater = (LayoutInflater.from(context));
        this.author = author;
    }

    @Override
    public int getCount() {
        return title.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int pos, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.opinion_card_list, null);
        TextView titleTv = view.findViewById(R.id.opinion_title);
        TextView contentTv = view.findViewById(R.id.opinion_content);
        titleTv.setText(title.get(pos));
        TextView authorTv = view.findViewById(R.id.opinion_author);
        contentTv.setText(content.get(pos));
        authorTv.setText("- "+author.get(pos));
        contentTv.setMovementMethod(new ScrollingMovementMethod());
        return view;
    }
}

