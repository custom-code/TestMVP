package com.zero.test.base.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * ͼƬ��С
 * CompressImage compress = new CompressImage(context, filePath);// ���ͼƬѹ������
 * Bitmap bitmap = compress.getBitmap();// ���ѹ��֮���ͼƬ
 */
public class CompressImage {
    private Bitmap bm;
    private String filePath;

    public CompressImage(Activity context, String filePath) {
        this.filePath = filePath;
        getimage();
    }

    private Bitmap getimage() {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // ��ʼ����ͼƬ����ʱ��options.inJustDecodeBounds ���true��
        newOpts.inJustDecodeBounds = true;
        bm = BitmapFactory.decodeFile(filePath, newOpts);// ��ʱ����bmΪ��

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        // ���������ֻ��Ƚ϶���800*480�ֱ��ʣ����ԸߺͿ���������Ϊ
        float hh = 800f;// �������ø߶�Ϊ800f
        float ww = 480f;// �������ÿ���Ϊ480f
        // ���űȡ������ǹ̶��������ţ�ֻ�ø߻��߿�����һ�����ݽ��м��㼴��
        int be = 1;// be=1��ʾ������
        if (w > h && w > ww) {// ������ȴ�Ļ����ݿ��ȹ̶���С����
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {// ����߶ȸߵĻ����ݿ��ȹ̶���С����
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;// �������ű���
        // ���¶���ͼƬ��ע���ʱ�Ѿ���options.inJustDecodeBounds ���false��
        bm = BitmapFactory.decodeFile(filePath, newOpts);
        return compressImage(bm);// ѹ���ñ�����С���ٽ�������ѹ��
    }

    public Bitmap getBitmap() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        if (baos.toByteArray().length / 1024 > 1024) {// �ж����ͼƬ����1M,����ѹ������������ͼƬ��BitmapFactory.decodeStream��ʱ���
            baos.reset();// ����baos�����baos
            bm.compress(Bitmap.CompressFormat.JPEG, 50, baos);// ����ѹ��50%����ѹ��������ݴ�ŵ�baos��
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // ��ʼ����ͼƬ����ʱ��options.inJustDecodeBounds ���true��
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        // ���������ֻ��Ƚ϶���800*480�ֱ��ʣ����ԸߺͿ���������Ϊ
        float hh = 800f;// �������ø߶�Ϊ800f
        float ww = 480f;// �������ÿ���Ϊ480f
        // ���űȡ������ǹ̶��������ţ�ֻ�ø߻��߿�����һ�����ݽ��м��㼴��
        int be = 1;// be=1��ʾ������
        if (w > h && w > ww) {// ������ȴ�Ļ����ݿ��ȹ̶���С����
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {// ����߶ȸߵĻ����ݿ��ȹ̶���С����
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;// �������ű���
        // ���¶���ͼƬ��ע���ʱ�Ѿ���options.inJustDecodeBounds ���false��
        isBm = new ByteArrayInputStream(baos.toByteArray());
        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        return compressImage(bitmap);// ѹ���ñ�����С���ٽ�������ѹ��

    }

    private Bitmap compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// ����ѹ������������100��ʾ��ѹ������ѹ��������ݴ�ŵ�baos��
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) { // ѭ���ж����ѹ����ͼƬ�Ƿ����100kb,���ڼ���ѹ��
            baos.reset();// ����baos�����baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// ����ѹ��options%����ѹ��������ݴ�ŵ�baos��
            options -= 10;// ÿ�ζ�����10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// ��ѹ���������baos��ŵ�ByteArrayInputStream��
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// ��ByteArrayInputStream��������ͼƬ
        return bitmap;
    }

}