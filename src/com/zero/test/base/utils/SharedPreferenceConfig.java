package com.zero.test.base.utils;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by zhe on 15/10/23.
 */
public class SharedPreferenceConfig {

    protected SharedPreferences mPrefer;
    protected SharedPreferences.Editor mEditor;
    protected Context mContext;

    public final int TYPE_INT = 0x0090001;
    public final int TYPE_BOOLEAN = 0x0090002;
    public final int TYPE_STRING = 0x0090003;
    public final int TYPE_FLOAT = 0x0090004;
    public final int TYPE_LONG = 0x0090005;

    private SharedPreferenceConfig() {
    }

    private static class ConfigHolder {
        private static final SharedPreferenceConfig INSTANCE = new SharedPreferenceConfig();
    }

    public static SharedPreferenceConfig getConfig() {
        return ConfigHolder.INSTANCE;
    }

    public void init(Context context) {
        this.mContext = context;
        mPrefer = this.mContext.getSharedPreferences(Constants.BASE_PREFER, Context.MODE_PRIVATE);
        mEditor = mPrefer.edit();
    }


    private boolean isBroken() {
        if (mPrefer == null || mEditor == null) {
            if (mContext != null) {
                init(mContext);
                return isBroken();
            }
            return true;
        }
        return false;
    }

    public void setValue(String tag, Object text) {
        if (!isBroken()) {
            if (text instanceof String) {
                mEditor.putString(tag, String.valueOf(text));
            } else if (text instanceof Integer) {
                mEditor.putInt(tag, Integer.parseInt(String.valueOf(text)));
            } else if (text instanceof Boolean) {
                mEditor.putBoolean(tag, Boolean.parseBoolean(String.valueOf(text)));
            } else if (text instanceof Float) {
                mEditor.putFloat(tag, Float.parseFloat(String.valueOf(text)));
            } else if (text instanceof Long) {
                mEditor.putLong(tag, Long.parseLong(String.valueOf(text)));
            }
            mEditor.commit();
        }
    }

    public Object getValue(String tag, int type) {
        if (!isBroken()) {
            switch (type) {
                case TYPE_STRING:
                    return mPrefer.getString(tag, "");
                case TYPE_INT:
                    return mPrefer.getInt(tag, 0);
                case TYPE_BOOLEAN:
                    return mPrefer.getBoolean(tag, false);
                case TYPE_FLOAT:
                    return mPrefer.getFloat(tag, 0f);
                case TYPE_LONG:
                    return mPrefer.getLong(tag, 0);
            }
        }
        return null;
    }
}
