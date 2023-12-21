package com.example.devapp_1.controllers;

import com.example.devapp_1.models.History;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import com.example.devapp_1.helpers.SqLiteHelper;

public class HistoryController {
    private static HistoryController instance;
    private static final int VERSION = 1;
    private static final String NOM_BDD = "mydatabase.db";
    private static final String TABLE_NAME = "table_histories";
    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_NAME = "USERNAME";
    private static final int NUM_COL_NAME = 1;
    private static final String COL_CONSULT = "CONSULTATION";
    private static final int NUM_COL_CONULT = 2;
    private SQLiteDatabase bd;
    private SqLiteHelper histories;
    private History history;

    // Private constructor to enforce Singleton pattern
    private HistoryController(Context context) {
        histories = new SqLiteHelper(context, NOM_BDD, null, VERSION);
    }

    // Method to get the singleton instance
    public static HistoryController getInstance(Context context) {
        if (instance == null) {
            instance = new HistoryController(context);
        }
        return instance;
    } 

    public void openForWrite() {
        bd = histories.getWritableDatabase();
    }

    public void openForRead() {
        bd = histories.getReadableDatabase();
    }

    public void close() {
        bd.close();
    }

    public SQLiteDatabase getBd() {
        return bd;
    }

    public long addHistory(String username,String consult) {
        openForWrite();
        history = new History(username,consult);
      
        ContentValues content = new ContentValues();
        content.put(COL_NAME, history.getUserame());
        content.put(COL_CONSULT, history.getConsultation());
        long result = bd.insert(TABLE_NAME, null, content);
        close();
        return result;
    }

    // public int updateHistory(int id, History history) {
//        ContentValues content = new ContentValues();
//        content.put(COL_NAME, history.getNae());
//        content.put(COL_CONSULT, history.getConsultation());
//        return bd.update(TABLE_NAME, content, COL_ID + " = " + id, null);
//     }
// 
//     public int removeHistory(String username) {
//         return bd.delete(TABLE_NAME, COL_NAME + " = ?", new String[]{username});
//     }
// 
//     public History getHistory(String username) {
//         Cursor c = bd.query(TABLE_NAME, new String[]{COL_ID, COL_NAME, COL_CONSULT},
//                 COL_NAME + " LIKE ?", new String[]{"%" + username + "%"}, null, null, COL_NAME);
//         return cursorToHistory(c);
//     }

    public ArrayList<History> getAllHistories() {
        openForRead();
        Cursor c = bd.query(TABLE_NAME, new String[]{COL_ID, COL_NAME, COL_CONSULT},
                null, null, null, null, COL_NAME);
        close();
        return cursorToHistoryList(c);
    }

    // private History cursorToHistory(Cursor c) {
//         if (c.getCount() == 0) {
//             c.close();
//             return null;
//         }
//         c.moveToFirst();
//         History history = new History();
//         history.setId(c.getInt(NUM_COL_ID));
//         history.setUserame(c.getString(NUM_COL_NAME));
//         history.setConsultation(c.getString(NUM_COL_CONSULT));
//         
//         c.close();
//         return history;
//     }

    private ArrayList<History> cursorToHistoryList(Cursor c) {
        if (c.getCount() == 0) {
            c.close();
            return null;
        }
        ArrayList<History> historyList = new ArrayList<>();
        while (c.moveToNext()) {
            history history = new History();
            history.setId(c.getInt(NUM_COL_ID));
            history.setUsername(c.getString(NUM_COL_NAME));
            history.setConsultation(c.getString(NUM_COL_CONSULT));
            historyList.add(history);
        }
        c.close();
        return historyList;
    }
} 