package quangndph27050.fpoly.damthvinpn.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import quangndph27050.fpoly.damthvinpn.database.DbHelper;
import quangndph27050.fpoly.damthvinpn.model.Sach;

public class SachDAO {
    DbHelper dbHelper;
    public SachDAO(Context context){
        dbHelper = new DbHelper(context);
    }

    //lấy toàn bộ đầu sách trong tv
    public ArrayList<Sach> getDSDauSach(){
        ArrayList<Sach> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT sc.masach, sc.tensach, sc.giathue, sc.maloai, lo.tenloai FROM SACH sc, LOAISACH lo WHERE sc.maloai = lo.maloai",null);
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                list.add(new Sach(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3), cursor.getString(4)));
            }while (cursor.moveToNext());
        }

        return list;
    }

    public boolean themSachMoi(String tensach, int giatien, int maloai){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tensach",tensach);
        contentValues.put("giathue",giatien);
        contentValues.put("maloai",maloai);
        long check = sqLiteDatabase.insert("SACH",null,contentValues);
        if (check == -1)
            return false;
        return true;
    }
}
