package com.example.cube;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.ParcelUuid;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String TABLE_CUBES_NAME = "Cubes";
    private static final String COLUMN_CUBES_ID = "id";
    private static final String COLUMN_CUBE_NAME = "name";
    private static final String COLUMN_IS_OPEN = "is_open";



    private static final String TABLE_IMAGES_NAME = "Images";
    private static final String COLUMN_IMAGE_NAME = "name";
    private static final String COLUMN_IMAGE_ID = "id";
    private static final String COLUMN_CUBE_ID = "cubes_id";
    private static final String COLUMN_LEDS_STATUS = "layers";


    private static final String TABLE_USERS_NAME = "Users";
    private static final String COLUMN_LOGIN = "login";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_USER_ID = "id";
    private static final String COLUMN_IS_ADMIN = "is_admin";
    public DataBaseHelper(@Nullable Context context) {
        super(context, "cubes.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableCubes = "CREATE TABLE IF NOT EXISTS "+
                TABLE_CUBES_NAME + " ( " +
                COLUMN_CUBES_ID + "	INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE," +
                COLUMN_CUBE_NAME + " TEXT NOT NULL,"+
                COLUMN_IS_OPEN+"	INTEGER NOT NULL)";

        String createTableImages = "CREATE TABLE IF NOT EXISTS " +
                TABLE_IMAGES_NAME + " ( "+
                COLUMN_IMAGE_ID+"	INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE," +
                COLUMN_IMAGE_NAME + "	TEXT NOT NULL, "+
                COLUMN_CUBE_ID+"	INTEGER, "+
                COLUMN_LEDS_STATUS+"	TEXT NOT NULL, " +
                "FOREIGN KEY("+ COLUMN_CUBE_ID+") REFERENCES "+ TABLE_CUBES_NAME+"("+COLUMN_CUBES_ID+"))";

        String createTableUsers = "CREATE TABLE IF NOT EXISTS " +
                TABLE_USERS_NAME + " ( "+
                COLUMN_USER_ID+"	INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE," +
                COLUMN_LOGIN + "	TEXT NOT NULL UNIQUE, "+
                COLUMN_PASSWORD+"	TEXT NOT NULL UNIQUE, "+
                COLUMN_IS_ADMIN+"	INTEGER NOT NULL)";
        db.execSQL(createTableCubes);
        db.execSQL(createTableImages);
        db.execSQL(createTableUsers);
        db.execSQL("INSERT INTO "+TABLE_USERS_NAME+" ("+COLUMN_USER_ID+","+COLUMN_LOGIN+","+COLUMN_PASSWORD+","+COLUMN_IS_ADMIN+") " + "VALUES (0, 'admin','admin', 1)");
        db.execSQL("INSERT INTO "+TABLE_CUBES_NAME+" ("+COLUMN_CUBES_ID+","+COLUMN_CUBE_NAME+","+COLUMN_IS_OPEN+") " +
                "VALUES (0, 'А',0), " +
                "(1, 'Б',0), " + "(2, 'В',0), " + "(3, 'Г',0), " + "(4, 'Д',0), " + "(5, 'Е',0), " + "(6, 'Ё',0), " + "(7, 'Ж',0), " + "(8, 'З',0), " + "(9, 'И',0), " + "(10, 'Й',0), " + "(11, 'К',0), " +
                "(12, 'Л',0), " + "(13, 'М',0), " + "(14, 'Н',0), " + "(15, 'О',0), " + "(16, 'П',0), " + "(17, 'Р',0), " + "(18, 'С',0), " + "(19, 'Т',0), " + "(20, 'У',0), " + "(21, 'Ф',0), " + "(22, 'Х',0), " +
                "(23, 'Ц',0), " + "(24, 'Ч',0), " + "(25, 'Ш',0), " + "(26, 'Щ',0), " + "(27, 'Ъ',0), " + "(28, 'Ы',0), " + "(29, 'Ь',0), " + "(30, 'Э',0), " + "(31, 'Ю',0), " + "(32, 'Я',0), " + "(33, '!',0), (34, '?',0), (35, ' ',0) "

        );
        insertLetterAnimation(db, "4200000000000000420000000000000042000000000000007e000000000000004200000000000000420000000000000024000000000000001800000000000000", 0);
        insertLetterAnimation(db, "fc00000000000000840000000000000084000000000000008400000000000000f80000000000000080000000000000008000000000000000fe00000000000000", 1);
        insertLetterAnimation(db, "f800000000000000840000000000000084000000000000008800000000000000f00000000000000088000000000000008800000000000000f000000000000000", 2);
        insertLetterAnimation(db, "40000000000000004000000000000000400000000000000040000000000000004000000000000000400000000000000040000000000000007c00000000000000", 3);
        insertLetterAnimation(db, "000000000000000042000000000000007e0000000000000024000000000000002400000000000000240000000000000038000000000000000000000000000000", 4);
        insertLetterAnimation(db, "7c00000000000000400000000000000040000000000000007800000000000000400000000000000040000000000000007c000000000000000000000000000000", 5);
        insertLetterAnimation(db, "7c00000000000000400000000000000040000000000000007800000000000000400000000000000040000000000000007c000000000000002800000000000000", 6);
        insertLetterAnimation(db, "92000000000000009200000000000000540000000000000038000000000000003800000000000000540000000000000092000000000000009200000000000000", 7);
        insertLetterAnimation(db, "f000000000000000080000000000000008000000000000000800000000000000700000000000000008000000000000000800000000000000f000000000000000", 8);
        insertLetterAnimation(db, "84000000000000008400000000000000c400000000000000a40000000000000094000000000000008c0000000000000084000000000000008400000000000000", 9);
        insertLetterAnimation(db, "8400000000000000c400000000000000a40000000000000094000000000000008c000000000000008400000000000000b4000000000000004800000000000000", 10);
        insertLetterAnimation(db, "88000000000000009000000000000000a000000000000000c000000000000000c000000000000000a00000000000000090000000000000008800000000000000", 11);
        insertLetterAnimation(db, "42000000000000004200000000000000420000000000000042000000000000004200000000000000420000000000000024000000000000001800000000000000", 12);
        insertLetterAnimation(db, "42000000000000004200000000000000420000000000000042000000000000005a00000000000000660000000000000042000000000000004200000000000000", 13);
        insertLetterAnimation(db, "42000000000000004200000000000000420000000000000042000000000000007e00000000000000420000000000000042000000000000004200000000000000", 14);
        insertLetterAnimation(db, "18000000000000002400000000000000420000000000000042000000000000004200000000000000420000000000000024000000000000001800000000000000", 15);
        insertLetterAnimation(db, "42000000000000004200000000000000420000000000000042000000000000004200000000000000420000000000000042000000000000007e00000000000000", 16);
        insertLetterAnimation(db, "40000000000000004000000000000000400000000000000078000000000000004400000000000000440000000000000044000000000000007800000000000000", 17);
        insertLetterAnimation(db, "1c000000000000002000000000000000400000000000000040000000000000004000000000000000400000000000000020000000000000001c00000000000000", 18);
        insertLetterAnimation(db, "1000000000000000100000000000000010000000000000001000000000000000100000000000000010000000000000001000000000000000fe00000000000000", 19);
        insertLetterAnimation(db, "80000000000000004000000000000000200000000000000010000000000000001800000000000000240000000000000042000000000000008100000000000000", 20);
        insertLetterAnimation(db, "10000000000000001000000000000000100000000000000038000000000000005400000000000000540000000000000054000000000000003800000000000000", 21);
        insertLetterAnimation(db, "81000000000000004200000000000000240000000000000018000000000000001800000000000000240000000000000042000000000000008100000000000000", 22);
        insertLetterAnimation(db, "02000000000000003e00000000000000240000000000000024000000000000002400000000000000240000000000000024000000000000002400000000000000", 23);
        insertLetterAnimation(db, "0200000000000000020000000000000002000000000000003e000000000000004200000000000000420000000000000042000000000000004200000000000000", 24);
        insertLetterAnimation(db, "0000000000000000fe00000000000000920000000000000092000000000000009200000000000000920000000000000092000000000000009200000000000000", 25);
        insertLetterAnimation(db, "0100000000000000ff00000000000000920000000000000092000000000000009200000000000000920000000000000092000000000000009200000000000000", 26);
        insertLetterAnimation(db, "3800000000000000240000000000000024000000000000003800000000000000200000000000000020000000000000002000000000000000e000000000000000", 27);
        insertLetterAnimation(db, "72000000000000004a000000000000004a0000000000000072000000000000004200000000000000420000000000000042000000000000004200000000000000", 28);
        insertLetterAnimation(db, "38000000000000002400000000000000240000000000000038000000000000002000000000000000200000000000000020000000000000002000000000000000", 29);
        insertLetterAnimation(db, "70000000000000000800000000000000040000000000000004000000000000007c00000000000000040000000000000008000000000000007000000000000000", 30);
        insertLetterAnimation(db, "8c00000000000000920000000000000092000000000000009200000000000000f200000000000000920000000000000092000000000000008c00000000000000", 31);
        insertLetterAnimation(db, "88000000000000004800000000000000280000000000000018000000000000003800000000000000480000000000000048000000000000003800000000000000", 32);
        insertLetterAnimation(db, "10000000000000000000000000000000100000000000000010000000000000001000000000000000100000000000000010000000000000001000000000000000", 33);
        insertLetterAnimation(db, "1000000000000000000000000000000010000000000000000c000000000000000200000000000000020000000000000022000000000000001c00000000000000", 34);
        insertLetterAnimation(db, "00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", 35);

    }
    public void insertLetterAnimation(SQLiteDatabase db, String letter, int cubeId){
        for(int i = 0; i < 8; i++){
            ContentValues cv = new ContentValues();
            cv.put(COLUMN_IMAGE_NAME, "image_letter_"+cubeId+"."+i);
            cv.put(COLUMN_CUBE_ID, cubeId);
            String letterWithOffset = "";
            for(int j = 0; j < i; j++){
                letterWithOffset += "00";
            }
            cv.put(COLUMN_LEDS_STATUS, (letterWithOffset + letter).substring(0, letter.length()));
            //Log.i("RESULT LETTER", (letterWithOffset + letter).substring(0, letter.length()));
            long result = db.insert(TABLE_IMAGES_NAME, null, cv);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUBES_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_IMAGES_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS_NAME);
        onCreate(db);
    }
    public boolean addCube(Animation animation) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_CUBE_NAME, animation.getName());
        cv.put(COLUMN_IS_OPEN, 1);
        long result = db.insert(TABLE_CUBES_NAME, null, cv);
        long result2[] = new long[animation.getImages().size()];
        Cursor c;
        for(int i = 0; i < animation.getImages().size(); i++){
            cv = new ContentValues();
            cv.put(COLUMN_IMAGE_NAME, animation.getImages().get(i).getName());
            cv.put(COLUMN_LEDS_STATUS, animation.getImages().get(i).getLeds_status());
            c = null;
            int seq = 0;
            try {
                String sql = "select seq from sqlite_sequence where name=?";
                c = db.rawQuery(sql, new String[] {TABLE_CUBES_NAME});
                if (c.moveToFirst()) {
                    seq = c.getInt(0);
                }
            } finally {
                if (c != null) {
                    c.close();
                }
            }
            cv.put(COLUMN_CUBE_ID, seq);
            result2[i] = db.insert(TABLE_IMAGES_NAME, null, cv);
        }
        db.close();
        long output = 1;
        for(int i = 0; i < result2.length; i++){
            output *= result2[i];
        }
        return result*output != -1;
    }
    public boolean deleteCube(Animation anim) {
        SQLiteDatabase db = this.getWritableDatabase();
        int[] resultImgs = new int[anim.getImages().size()];
        for(int i = 0; i < anim.getImages().size(); i++){
            resultImgs[i] = db.delete(TABLE_IMAGES_NAME, COLUMN_IMAGE_ID + " = ?", new String[]{anim.getImages().get(i).getId() + ""});
        }
        int result = db.delete(TABLE_CUBES_NAME, COLUMN_CUBES_ID + " = ?", new String[]{anim.getId() + ""});
        db.close();
        //Log.d("DELETE ANIM", result+"");
        for(int i = 0; i < resultImgs.length; i++){
            result *= resultImgs[i];
            //Log.d("DELETE ANIM", resultImgs[i]+"");

        }
        return result != -1;
    }
    public Animation findAnimation(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CUBES_NAME, new String[]{COLUMN_CUBES_ID, COLUMN_CUBE_NAME},
                COLUMN_CUBE_ID + " = ?", new String[]{id + ""},
                null,
                null,
                null);
        if (cursor != null && cursor.moveToFirst()) {
            Animation anim = new Animation(cursor.getInt(0), cursor.getString(1), new ArrayList<>());
            ArrayList<Image> images = new ArrayList<>();
            Cursor cursorImg = db.rawQuery("SELECT * FROM " + TABLE_IMAGES_NAME, null);
            if (cursorImg.moveToFirst()) {
                do {
                    if(cursor.getInt(0) == cursorImg.getInt(2)) {
                        Image img = new Image(cursorImg.getInt(0), cursorImg.getString(1), cursorImg.getString(3));
                        images.add(img);
                    }
                } while (cursorImg.moveToNext());
            }
            anim.setImages(new ArrayList<>(images));
            cursor.close();
            cursorImg.close();
            db.close();
            return anim;
        }
        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return null;
    }
    public List<Animation> getAllCubes() {
        List<Animation> cubes = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CUBES_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                Animation anim = new Animation(cursor.getInt(0), cursor.getString(1), new ArrayList<>());
                if(cursor.getInt(2) == 1) {
                    ArrayList<Image> images = new ArrayList<>();
                    Cursor cursorImg = db.rawQuery("SELECT * FROM " + TABLE_IMAGES_NAME, null);
                    if (cursorImg.moveToFirst()) {
                        do {
                            if (cursor.getInt(0) == cursorImg.getInt(2)) {
                                //Log.i("IMGS ID", cursor.getInt(0) + "");
                                Image img = new Image(cursorImg.getInt(0), cursorImg.getString(1), cursorImg.getString(3));
                                images.add(img);
                            }
                        } while (cursorImg.moveToNext());
                    }
                    anim.setImages(new ArrayList<>(images));
                    cursorImg.close();
                    cubes.add(anim);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return cubes;
    }
    public List<Animation> getClosedCubes() {
        List<Animation> cubes = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CUBES_NAME, null);
        if (cursor.moveToFirst()) {
            do {
                Animation anim = new Animation(cursor.getInt(0), cursor.getString(1), new ArrayList<>());
                if(cursor.getInt(2) == 0) {
                    ArrayList<Image> images = new ArrayList<>();
                    Cursor cursorImg = db.rawQuery("SELECT * FROM " + TABLE_IMAGES_NAME, null);
                    if (cursorImg.moveToFirst()) {
                        do {
                            if (cursor.getInt(0) == cursorImg.getInt(2)) {
                                //Log.i("IMGS ID", cursorImg.getInt(0) + " "+cursor.getString(1));
                                Image img = new Image(cursorImg.getInt(0), cursorImg.getString(1), cursorImg.getString(3));
                                images.add(img);
                            }
                        } while (cursorImg.moveToNext());
                    }
                    anim.setImages(new ArrayList<>(images));
                    cursorImg.close();
                    cubes.add(anim);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return cubes;
    }


    public User findUser(String login, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS_NAME, new String[]{COLUMN_USER_ID, COLUMN_LOGIN, COLUMN_PASSWORD, COLUMN_IS_ADMIN},
                COLUMN_LOGIN + " = ? AND "+COLUMN_PASSWORD+" = ?", new String[]{login, password},
                null,
                null,
                null);
        if (cursor != null && cursor.moveToFirst()) {
            User user = new User(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getInt(3));
            cursor.close();
            db.close();
            return user;
        }
        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return null;
    }
    public boolean addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_LOGIN, user.getLogin());
        cv.put(COLUMN_PASSWORD, user.getPassword());
        cv.put(COLUMN_IS_ADMIN, user.getIs_admin());
        long result = db.insert(TABLE_USERS_NAME, null, cv);
        db.close();
        return result != -1;
    }
}
