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

    public long inserir(Cidade cidade){
        db = helper.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(SQLHelper.COLUNA_NAME, cidade.name);
        cv.put(SQLHelper.COLUNA_CAPITAL, cidade.capital);
        cv.put(SQLHelper.COLUNA_REGION, cidade.region);
        cv.put(SQLHelper.COLUNA_POPULATION, cidade.population);
        cv.put(SQLHelper.COLUNA_AREA, cidade.area);
        cv.put(SQLHelper.COLUNA_LANGUAGES, cidade.languages);
        cv.put(SQLHelper.COLUNA_RELEVANCE, cidade.relevance);

        long id = db.insert(SQLHelper.TABELA_CIDADE, null, cv);

        if(id != -1){
            cidade.id = id;
        }
        db.close();
        return id;
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
            long id = cursor.getLong(
                    cursor.getColumnIndex(SQLHelper.COLUNA_ID)
            );
            String name = cursor.getString(
                    cursor.getColumnIndex(SQLHelper.COLUNA_NAME)
            );
            String capital = cursor.getString(
                    cursor.getColumnIndex(SQLHelper.COLUNA_CAPITAL)
            );
            String population = cursor.getString(
                    cursor.getColumnIndex(SQLHelper.COLUNA_POPULATION)
            );
            String area = cursor.getString(
                    cursor.getColumnIndex(SQLHelper.COLUNA_AREA)
            );
            String languages = cursor.getString(
                    cursor.getColumnIndex(SQLHelper.COLUNA_LANGUAGES)
            );
            String relevance = cursor.getString(
                    cursor.getColumnIndex(SQLHelper.COLUNA_RELEVANCE)
            );
            String region = cursor.getString(
                    cursor.getColumnIndex(SQLHelper.COLUNA_REGION)
            );

            Cidade cidade = new Cidade(name,capital, region, population,area,languages,relevance,
                    "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
            list.add(cidade);
        }
        cursor.close();
        return list;
    }

}
