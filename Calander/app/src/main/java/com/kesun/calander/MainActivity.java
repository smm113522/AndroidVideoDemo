package com.kesun.calander;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Calendars;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private android.widget.Button btnaddzhanghao;
    private android.widget.Button btnlookzhanghao;
    private android.widget.Button btnlookshijian;
    private android.widget.Button btnaddshijian;
    private android.widget.Button btndelshijian;
    private android.widget.ListView list;

    //Android2.2版本以后的URL，之前的就不写了
    private static String calanderURL = "content://com.android.calendar/calendars";
    private static String calanderEventURL = "content://com.android.calendar/events";
    private static String calanderRemiderURL = "content://com.android.calendar/reminders";

    private ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        listAdapter = new ListAdapter(this);
        list.setAdapter(listAdapter);
    }

    private void initView() {
        this.list = (ListView) findViewById(R.id.list);
        this.btndelshijian = (Button) findViewById(R.id.btn_del_shijian);
        this.btnaddshijian = (Button) findViewById(R.id.btn_add_shijian);
        this.btnlookshijian = (Button) findViewById(R.id.btn_look_shijian);
        this.btnlookzhanghao = (Button) findViewById(R.id.btn_look_zhanghao);
        this.btnaddzhanghao = (Button) findViewById(R.id.btn_add_zhanghao);

        this.btndelshijian.setOnClickListener(this);
        this.btnaddshijian.setOnClickListener(this);
        this.btnlookshijian.setOnClickListener(this);
        this.btnlookzhanghao.setOnClickListener(this);
        this.btnaddzhanghao.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_del_shijian:
                int rownum = getContentResolver().delete(Uri.parse(calanderURL), "_id!=-1", null);  //注意：会全部删除所有账户，新添加的账户一般从id=1开始，
                //可以令_id=你添加账户的id，以此删除你添加的账户
                Toast.makeText(MainActivity.this, "删除了: " + rownum, Toast.LENGTH_LONG).show();

                break;
            case R.id.btn_add_shijian:
                addCalendars_Shijian();
                break;
            case R.id.btn_look_shijian:
                Cursor eventCursor = getContentResolver().query(Uri.parse(calanderEventURL), null, null, null, null);
                ArrayList<String> list = new ArrayList<>();
                if (eventCursor.getCount() > 0) {
                    eventCursor.moveToLast();             //注意：这里与添加事件时的账户相对应，都是向最后一个账户添加
                    String eventTitle = eventCursor.getString(eventCursor.getColumnIndex("title"));
//                    Toast.makeText(MainActivity.this, eventTitle, Toast.LENGTH_LONG).show();
                    list.add(eventTitle);
                }
                listAdapter.setMlist(list);

                break;
            case R.id.btn_look_zhanghao:
                /* 查看账户 */
                Cursor userCursor = getContentResolver().query(Uri.parse(calanderURL), null, null, null, null);

                System.out.println("Count: " + userCursor.getCount());
//                Toast.makeText(this, "Count: " + userCursor.getCount(), Toast.LENGTH_LONG).show();
                ArrayList<String> list1 = new ArrayList<>();
                for (userCursor.moveToFirst(); !userCursor.isAfterLast(); userCursor.moveToNext()) {
                    System.out.println("name: " + userCursor.getString(userCursor.getColumnIndex("ACCOUNT_NAME")));


                    String userName1 = userCursor.getString(userCursor.getColumnIndex("name"));
                    String userName0 = userCursor.getString(userCursor.getColumnIndex("ACCOUNT_NAME"));
                    String userName2 = userCursor.getString(userCursor.getColumnIndex("ACCOUNT_TYPE"));
                    String userName3 = userCursor.getString(userCursor.getColumnIndex("CALENDAR_DISPLAYNAME"));
//                    Toast.makeText(this, "NAME: " + userName1 + " -- ACCOUNT_NAME: " + userName0, Toast.LENGTH_LONG).show();
                    list1.add("NAME: " + userName1 + " -- ACCOUNT_NAME: " + userName0 + "--ACCOUNT_TYPE:" + userName2
                     + "--CALENDAR_DISPLAY_NAME: " + userName3);
                }

                listAdapter.setMlist(list1);
                break;
            case R.id.btn_add_zhanghao:
                /* 添加账户*/
                addCalendars();
                break;
        }
    }

    private void addCalendars_Shijian() {
        // 获取要出入的gmail账户的id
        String calId = "";
        Cursor userCursor = getContentResolver().query(Uri.parse(calanderURL), null, null, null, null);
        if (userCursor.getCount() > 0) {
            userCursor.moveToLast();  //注意：是向最后一个账户添加，开发者可以根据需要改变添加事件 的账户
            calId = userCursor.getString(userCursor.getColumnIndex("_id"));
        } else {
            Toast.makeText(this, "没有账户，请先添加账户", Toast.LENGTH_SHORT).show();
            return;
        }

        ContentValues event = new ContentValues();
        event.put("title", "测试说明");
        event.put("description", "Frankie受 邀请,今天晚上10点以后将在Sheraton 交流.lol~");
        // 插入账户
        event.put("calendar_id", calId);
        System.out.println("calId: " + calId);
        event.put("eventLocation", "地球-华夏");

        Calendar mCalendar = Calendar.getInstance();
        mCalendar.set(Calendar.HOUR_OF_DAY, 11);
        mCalendar.set(Calendar.MINUTE, 45);
        long start = mCalendar.getTime().getTime();
        mCalendar.set(Calendar.HOUR_OF_DAY, 12);
        long end = mCalendar.getTime().getTime();

        event.put("dtstart", start);
        event.put("dtend", end);
        event.put("hasAlarm", 1);

        event.put(CalendarContract.Events.EVENT_TIMEZONE, "Asia/Shanghai");  //这个是时区，必须有，
        //添加事件
        Uri newEvent = getContentResolver().insert(Uri.parse(calanderEventURL), event);
        //事件提醒的设定
        long id = Long.parseLong(newEvent.getLastPathSegment());
        ContentValues values = new ContentValues();
        values.put("event_id", id);
        // 提前10分钟有提醒
        values.put("minutes", 10);
        getContentResolver().insert(Uri.parse(calanderRemiderURL), values);

        Toast.makeText(MainActivity.this, "插入事件成功!!!", Toast.LENGTH_LONG).show();
    }

    /*/添加账户*/
    private void addCalendars() {
        String email = "mygmailaddress@gmail.com";
        String baopk = "com.android.exchange";
        String name = "yy";
        String displayName = "mytt";

        TimeZone timeZone = TimeZone.getDefault();
        ContentValues value = new ContentValues();
        value.put(CalendarContract.Calendars.NAME, name);

        value.put(Calendars.ACCOUNT_NAME, email);
        value.put(Calendars.ACCOUNT_TYPE, baopk);
        value.put(Calendars.CALENDAR_DISPLAY_NAME, displayName);
        value.put(Calendars.VISIBLE, 1);
        value.put(Calendars.CALENDAR_COLOR, -9206951);
        value.put(Calendars.CALENDAR_ACCESS_LEVEL, Calendars.CAL_ACCESS_OWNER);
        value.put(Calendars.SYNC_EVENTS, 1);
        value.put(Calendars.CALENDAR_TIME_ZONE, timeZone.getID());
        value.put(Calendars.OWNER_ACCOUNT, email);
        value.put(Calendars.CAN_ORGANIZER_RESPOND, 0);

        Uri calendarUri = Calendars.CONTENT_URI;
        calendarUri = calendarUri.buildUpon()
                .appendQueryParameter(CalendarContract.CALLER_IS_SYNCADAPTER, "true")
                .appendQueryParameter(Calendars.ACCOUNT_NAME, email)
                .appendQueryParameter(Calendars.ACCOUNT_TYPE, baopk)
                .build();

        getContentResolver().insert(calendarUri, value);
    }

    public class ListAdapter extends BaseAdapter {
        private Context mContext;
        private ArrayList<String> mlist;

        private ListAdapter(Context context) {
            this.mContext = context;
        }

        public void setMlist(ArrayList<String> mlist) {
            this.mlist = mlist;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mlist == null ? 0 : mlist.size();
        }

        @Override
        public Object getItem(int i) {
            return mlist.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(mContext).inflate(R.layout.list_item, null);
            }
            ViewHolder holder = (ViewHolder) view.getTag();
            if (holder == null) {
                holder = new ViewHolder();
                holder.item = (TextView) view.findViewById(R.id.tv_item);
                view.setTag(holder);
            }
            String txt = mlist.get(i);
            holder.item.setText(txt);

            return view;
        }

        public class ViewHolder {
            TextView item;
        }
    }
}
