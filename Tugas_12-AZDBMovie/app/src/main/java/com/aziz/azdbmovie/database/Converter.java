package com.aziz.azdbmovie.database;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Base64;

import androidx.room.TypeConverter;

import java.io.ByteArrayOutputStream;

public class Converter {
    @TypeConverter
    public static Bitmap fromBase64ToBitmap(String base64Value) {
        if (!TextUtils.isEmpty(base64Value)) {
            byte[] decodedBytes = Base64.decode(base64Value, 0);
            return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
        } else {
            return null;
        }
    }

    @TypeConverter
    public static String fromBitmapToBase64(Bitmap bitmap) {
        if (bitmap != null) {
            ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 25, byteArrayOS);
            return Base64.encodeToString(byteArrayOS.toByteArray(), Base64.DEFAULT);
        } else {
            return null;
        }
    }
}
