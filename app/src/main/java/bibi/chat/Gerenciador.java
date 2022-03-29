package bibi.chat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Gerenciador {
    private Banco db;
    private SQLiteDatabase database;

    public Gerenciador(Context context){
        db = new Banco(context);
        database = db.getWritableDatabase();
    }

    public long addUsuario(String codigo, String nome){
        ContentValues values = new ContentValues();
        values.put("cod", codigo);
        values.put("name", nome);
        return database.insert("usuario", null, values);
    }

    public Cursor listarUsuarios() {
        String[] cols = new String[] {"cod", "name"};
        Cursor mCursor = database.query(true, "usuario",cols,null
                , null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
}
