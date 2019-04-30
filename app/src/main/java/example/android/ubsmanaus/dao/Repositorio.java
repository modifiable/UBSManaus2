package example.android.ubsmanaus.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import example.android.ubsmanaus.Model.Cidade;

public class Repositorio {

    private SQLHelper helper;
    private SQLiteDatabase db;

    public Repositorio(Context ctx){
        helper = new SQLHelper(ctx);
    }

    public void inserir(Cidade cidade){
        db = helper.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(SQLHelper.COLUNA_NAME, cidade.name);
        cv.put(SQLHelper.COLUNA_CAPITAL, cidade.capital);
        cv.put(SQLHelper.COLUNA_REGION, cidade.region);


        db.insert(SQLHelper.TABELA_CIDADE, null, cv);
        db.close();
    }

    public void excluirAll(){
        db = helper.getWritableDatabase();
        db.delete(SQLHelper.TABELA_CIDADE, null, null);
        db.close();
    }

    public List<Cidade> listarCidade() {
        String sql = "SELECT * FROM " + SQLHelper.TABELA_CIDADE;
        db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        List<Cidade> list = new ArrayList();

        while (cursor.moveToNext()) {

            String name = cursor.getString(
                    cursor.getColumnIndex(SQLHelper.COLUNA_NAME)
            );
            String capital = cursor.getString(
                    cursor.getColumnIndex(SQLHelper.COLUNA_CAPITAL)
            );
            String region = cursor.getString(
                    cursor.getColumnIndex(SQLHelper.COLUNA_REGION)
            );


            Cidade cidade = new Cidade(name,capital, region);
            list.add(cidade);
        }
        cursor.close();
        return list;
    }

}
