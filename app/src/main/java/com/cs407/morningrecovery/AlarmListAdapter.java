package com.cs407.morningrecovery;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;


public class AlarmListAdapter extends BaseAdapter {
    private Context context;
    private List<String> alarmStrings;

    public AlarmListAdapter(Context context, List<String> alarmStrings) {
        this.context = context;
        this.alarmStrings = alarmStrings;
    }

    @Override
    public int getCount() {
        return alarmStrings.size();
    }

    @Override
    public Object getItem(int position) {
        return alarmStrings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(alarmStrings.get(position));

        return convertView;
    }

    // 데이터 업데이트 메서드 추가
    public void updateData(List<String> newAlarmStrings) {
        alarmStrings = newAlarmStrings;
        notifyDataSetChanged();
    }
}
