package example.android.ubsmanaus.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLHelper extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "dbCidade";
    private static final int VERSAO_BANCO = 1;
    public static final String TABELA_CIDADE = "cidade_tabela";
    public static final String COLUNA_NAME = "name";
    public static final String COLUNA_CAPITAL = "capital";
    public static final String COLUNA_REGION = "region";
    public static final String COLUNA_POPULATION = "population";
    public static final String COLUNA_AREA = "area";
    public static final String COLUNA_LANGUAGES = "languages";
    public static final String COLUNA_RELEVANCE = "relevance";
    public static final String COLUNA_LATLNG = "latlng";


    public SQLHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE " + TABELA_CIDADE + " ( " +
                        COLUNA_NAME + " TEXT, " +
                        COLUNA_CAPITAL+ " TEXT, " +
                        COLUNA_REGION+ " TEXT, " +
                        COLUNA_POPULATION + " TEXT, " +
                        COLUNA_AREA + " TEXT, " +
                        COLUNA_LANGUAGES + " TEXT, " +
                        COLUNA_RELEVANCE + " TEXT, " +
                        COLUNA_LATLNG + "TEXT)"
        );

    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // para as próximas versões
    }

}