package com.game.Utils;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.game.Activity.MainFragment;
import com.game.Data.MySqlHelper;
import com.game.Model.Gamer;
import com.game.R;

/**
 * Created by longlong on 2015/4/28.
 */
public class DialogUtils {
    public static void getAddChartDialog(final Context context, final int score) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflate = LayoutInflater.from(context);
        View v = inflate.inflate(R.layout.dialog_charts, null);
        final EditText editText = (EditText) v.findViewById(R.id.et_name);

        builder.setTitle(R.string.dialog_title).setView(v).setPositiveButton(R.string.dialog_positive, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //插入数据
                MySqlHelper mySqlHelper = new MySqlHelper(context, "myapp.db", null, 1);
                SQLiteDatabase db = mySqlHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("user_name", editText.getText().toString());
                values.put("user_score", score);
                db.insert("charts", "id", values);
                MainFragment.getMainFragment().startGame();
            }
        }).setNegativeButton(R.string.dialog_negative, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainFragment.getMainFragment().startGame();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();
        final Button mBtnPositive = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        mBtnPositive.setEnabled(false);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() != 0) {
                    mBtnPositive.setEnabled(true);
                } else {
                    mBtnPositive.setEnabled(false);
                }
            }
        });

    }

    public static void getOpenDialog(Context context, Gamer gamer) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.dialog_open_title);
        LayoutInflater inflate = LayoutInflater.from(context);
        View v = inflate.inflate(R.layout.dialog_open, null);
        //设置布局
        TextView tv_name = (TextView) v.findViewById(R.id.tv_name);
        TextView tv_score = (TextView) v.findViewById(R.id.tv_score);

        tv_name.setText("姓名：" + gamer.getName());
        tv_score.setText("分数：" + gamer.getScore());

        builder.setView(v);
        builder.setPositiveButton("确定", null);
        //dialog属性操作
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

}
