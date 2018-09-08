package co.id.telkomsigma.palapaone.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import co.id.telkomsigma.palapaone.R;
import co.id.telkomsigma.palapaone.model.RundownModel;
import co.id.telkomsigma.palapaone.util.DataSession;

public class RundownAdapter extends BaseAdapter {

    private Context mContext;
    private RundownModel model;
    private List<RundownModel> listModel;
    private TextView txt_expo, txt_cate;
    private Typeface font, fontbold;
    private DataSession dataSess;
    private String idAgenda;
    private String idEvent;


    public RundownAdapter(Context mContext, List<RundownModel> listModel, DataSession dataSess, String idEvent, String idAgenda) {
        this.mContext = mContext;
        this.listModel = listModel;
        this.dataSess = dataSess;
        this.idEvent = idEvent;
        this.idAgenda = idAgenda;

        fontbold = Typeface.createFromAsset(mContext.getAssets(), "fonts/AvenirLTStd-Medium.otf");
        font = Typeface.createFromAsset(mContext.getAssets(), "fonts/AvenirLTStd-Book.otf");
    }

    @Override
    public int getCount() {
        return listModel.size();
    }

    @Override
    public Object getItem(int position) {
        return listModel.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public View getView(final int position, View view, ViewGroup parent) {
        model = listModel.get(position);
        final ViewHolder holder;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            view = inflater.inflate(R.layout.item_list_rundown, null);
            holder = new ViewHolder();
            holder.txtTitle_tiga = view.findViewById(R.id.txt_jam);
            holder.txtTitle_empat = view.findViewById(R.id.txt_acara);
            holder.txtTitle_enam = view.findViewById(R.id.txt_speaker);
            holder.txtTitle_lima = view.findViewById(R.id.txt_room);
            holder.lay_reminder = view.findViewById(R.id.lay_reminder);
            holder.imageView = view.findViewById(R.id.iv_reminder);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.txtTitle_tiga.setText(model.getRundown_start() + " - " + model.getRundown_end());
        holder.txtTitle_tiga.setTypeface(fontbold);
        holder.txtTitle_empat.setText(model.getRundown_name());
        holder.txtTitle_empat.setTypeface(fontbold);
        holder.txtTitle_enam.setText(model.getRundown_name());
        holder.txtTitle_enam.setVisibility(View.GONE);
        holder.txtTitle_enam.setTypeface(fontbold);
        holder.txtTitle_lima.setText(model.getRundown_place());
        holder.txtTitle_lima.setTypeface(fontbold);

        String lonceng = dataSess.getData("lonceng" + (position) + idEvent + idAgenda);
        if (lonceng.equals("")) {
            holder.imageView.setImageResource(R.drawable.icon_bell_off);
            dataSess.setData("lonceng" + (position) + idEvent + idAgenda, "off");

            holder.lay_reminder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(v.getRootView().getContext())
                            .setMessage("Are you sure you want to turn on reminder?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Toast.makeText(mContext, "Alarm has been set, see you soon!", Toast.LENGTH_SHORT).show();
                                    holder.imageView.setImageResource(R.drawable.icon_bell_on);
                                    holder.imageView.setTag(position);
                                    dataSess.setData("lonceng" + (position) + idEvent + idAgenda, "on");
                                }
                            })
                            .setNegativeButton("No", null)
                            .show();
                }
            });
        } else {
            if (lonceng.equals("off")) {
                holder.imageView.setImageResource(R.drawable.icon_bell_off);

                holder.lay_reminder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new AlertDialog.Builder(v.getRootView().getContext())
                                .setMessage("Are you sure you want to turn on reminder?")
                                .setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Toast.makeText(mContext, "Alarm has been set, see you soon!", Toast.LENGTH_SHORT).show();
                                        holder.imageView.setImageResource(R.drawable.icon_bell_on);
                                        holder.imageView.setTag(position);
                                        dataSess.setData("lonceng" + (position) + idEvent + idAgenda, "on");
                                    }
                                })
                                .setNegativeButton("No", null)
                                .show();
                    }
                });
            } else {
                holder.imageView.setImageResource(R.drawable.icon_bell_on);

                holder.lay_reminder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new AlertDialog.Builder(v.getRootView().getContext())
                                .setMessage("Are you sure you want to turn off reminder?")
                                .setCancelable(false)
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        holder.imageView.setImageResource(R.drawable.icon_bell_off);
                                        holder.imageView.setTag(position);
                                        dataSess.setData("lonceng" + (position) + idEvent + idAgenda, "off");
                                    }
                                })
                                .setNegativeButton("No", null)
                                .show();
                    }
                });
            }
        }

        return view;
    }

    class ViewHolder {
        TextView txtTitle_tiga;
        TextView txtTitle_empat;
        TextView txtTitle_enam;
        TextView txtTitle_lima;
        LinearLayout lay_reminder;
        ImageView imageView;
    }

//    public void saveReminder(){
////        ContentValues values = new ContentValues();
////
////        values.put(AlarmReminderContract.AlarmReminderEntry.KEY_TITLE, mTitle);
////        values.put(AlarmReminderContract.AlarmReminderEntry.KEY_DATE, mDate);
////        values.put(AlarmReminderContract.AlarmReminderEntry.KEY_TIME, mTime);
////        values.put(AlarmReminderContract.AlarmReminderEntry.KEY_REPEAT, mRepeat);
////        values.put(AlarmReminderContract.AlarmReminderEntry.KEY_REPEAT_NO, mRepeatNo);
////        values.put(AlarmReminderContract.AlarmReminderEntry.KEY_REPEAT_TYPE, mRepeatType);
////        values.put(AlarmReminderContract.AlarmReminderEntry.KEY_ACTIVE, mActive);
//
//        // Set up calender for creating the notification
//        mCalendar.set(Calendar.MONTH, --mMonth);
//        mCalendar.set(Calendar.YEAR, mYear);
//        mCalendar.set(Calendar.DAY_OF_MONTH, mDay);
//        mCalendar.set(Calendar.HOUR_OF_DAY, mHour);
//        mCalendar.set(Calendar.MINUTE, mMinute);
//        mCalendar.set(Calendar.SECOND, 0);
//
//        long selectedTimestamp =  mCalendar.getTimeInMillis();
//
//        // Check repeat type
//        if (mRepeatType.equals("Minute")) {
//            mRepeatTime = Integer.parseInt(mRepeatNo) * milMinute;
//        } else if (mRepeatType.equals("Hour")) {
//            mRepeatTime = Integer.parseInt(mRepeatNo) * milHour;
//        } else if (mRepeatType.equals("Day")) {
//            mRepeatTime = Integer.parseInt(mRepeatNo) * milDay;
//        } else if (mRepeatType.equals("Week")) {
//            mRepeatTime = Integer.parseInt(mRepeatNo) * milWeek;
//        } else if (mRepeatType.equals("Month")) {
//            mRepeatTime = Integer.parseInt(mRepeatNo) * milMonth;
//        }
//
//        if (mCurrentReminderUri == null) {
//            // This is a NEW reminder, so insert a new reminder into the provider,
//            // returning the content URI for the new reminder.
//            Uri newUri = getContentResolver().insert(AlarmReminderContract.AlarmReminderEntry.CONTENT_URI, values);
//
//            // Show a toast message depending on whether or not the insertion was successful.
//            if (newUri == null) {
//                // If the new content URI is null, then there was an error with insertion.
//                Toast.makeText(this, getString(R.string.editor_insert_reminder_failed),
//                        Toast.LENGTH_SHORT).show();
//            } else {
//                // Otherwise, the insertion was successful and we can display a toast.
//                Toast.makeText(this, getString(R.string.editor_insert_reminder_successful),
//                        Toast.LENGTH_SHORT).show();
//            }
//        } else {
//
//            int rowsAffected = getContentResolver().update(mCurrentReminderUri, values, null, null);
//
//            // Show a toast message depending on whether or not the update was successful.
//            if (rowsAffected == 0) {
//                // If no rows were affected, then there was an error with the update.
//                Toast.makeText(this, getString(R.string.editor_update_reminder_failed),
//                        Toast.LENGTH_SHORT).show();
//            } else {
//                // Otherwise, the update was successful and we can display a toast.
//                Toast.makeText(this, getString(R.string.editor_update_reminder_successful),
//                        Toast.LENGTH_SHORT).show();
//            }
//        }
//
//        // Create a new notification
//        if (mActive.equals("true")) {
//            if (mRepeat.equals("true")) {
//                new AlarmScheduler().setRepeatAlarm(getApplicationContext(), selectedTimestamp, mCurrentReminderUri, mRepeatTime);
//            } else if (mRepeat.equals("false")) {
//                new AlarmScheduler().setAlarm(getApplicationContext(), selectedTimestamp, mCurrentReminderUri);
//            }
//
//            Toast.makeText(this, "Alarm time is " + selectedTimestamp,
//                    Toast.LENGTH_LONG).show();
//        }
//
//        // Create toast to confirm new reminder
//        Toast.makeText(getApplicationContext(), "Saved",
//                Toast.LENGTH_SHORT).show();
//
//    }
}