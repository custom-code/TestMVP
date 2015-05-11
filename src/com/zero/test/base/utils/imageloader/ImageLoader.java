package com.zero.test.base.utils.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import com.zero.test.base.utils.BitmapUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.*;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ImageLoader {

    public MemoryCache memoryCache = new MemoryCache();
    FileCache fileCache;
    private Map<ImageView, String> imageViews = Collections.synchronizedMap(new WeakHashMap<ImageView, String>());
    ExecutorService executorService;
    Handler handler = new Handler();// handler to display images in UI thread

    private float roundPx = 10;

    public ImageLoader(Context context) {
        fileCache = new FileCache(context);
        executorService = Executors.newFixedThreadPool(5);
    }

    // final int stub_id=R.drawable.color_white_drawable;
    public void DisplayImage(String url, ImageView imageView) {
        imageViews.put(imageView, url);
        Bitmap bitmap = memoryCache.get(url);
        if (bitmap != null)
            imageView.setImageBitmap(bitmap);
        else {
            queuePhoto(url, imageView);
            // imageView.setImageResource(stub_id);
        }
    }

    public void DisplayImageRound(String url, ImageView imageView) {
        imageViews.put(imageView, url);
        Bitmap bitmap = memoryCache.get(url);
        if (bitmap != null)
            imageView.setImageBitmap(bitmap);
        else {
            queuePhoto(url, imageView);
            // imageView.setImageResource(stub_id);
        }
    }

    public void DisplayImage(String url, ImageView imageView, int defaultResourceId) {
        imageViews.put(imageView, url);
        Bitmap bitmap = memoryCache.get(url);
        if (bitmap != null)
            imageView.setImageBitmap(bitmap);
        else {
            queuePhotoWEH(url, imageView);
            imageView.setImageResource(defaultResourceId);
        }
    }

    public FileCache getFileCache() {
        return fileCache;
    }

    private void queuePhoto(String url, ImageView imageView) {
        PhotoToLoad p = new PhotoToLoad(url, imageView);
        executorService.submit(new PhotosLoader(p));
    }

    private void queuePhotoWEH(String url, ImageView imageView) {
        PhotoToLoad p = new PhotoToLoad(url, imageView, true);
        executorService.submit(new PhotosLoader(p));
    }

    public Bitmap getBitmap(String url) throws Exception {
        final DefaultHttpClient client = new DefaultHttpClient();
        final HttpGet getRequest = new HttpGet(url);

        HttpResponse response = client.execute(getRequest);
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != HttpStatus.SC_OK) {
            Log.e("PicShow", "Request URL failed, error code =" + statusCode);
        }

        HttpEntity entity = response.getEntity();
        if (entity == null) {
            Log.e("PicShow", "HttpEntity is null");
        }
        InputStream is = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            is = entity.getContent();
            byte[] buf = new byte[1024];
            int readBytes = -1;
            while ((readBytes = is.read(buf)) != -1) {
                baos.write(buf, 0, readBytes);
            }
        } finally {
            if (baos != null) {
                baos.close();
            }
            if (is != null) {
                is.close();
            }
        }
        byte[] imageArray = baos.toByteArray();
//		BitmapFactory.Options options = new BitmapFactory.Options();
//		options.inDither = false; /* 不进行图片抖动处理 */
//		options.inPreferredConfig = null; /* 设置让解码器以最佳方式解码 */
//		options.inSampleSize = 2; /* 图片长宽方向缩小倍数 */

        Bitmap bitmap = null;
        try {
//			bitmap = BitmapFactory.decodeByteArray(imageArray, 0, imageArray.length, options);
            bitmap = BitmapFactory.decodeByteArray(imageArray, 0, imageArray.length);
            memoryCache.put(url, bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    // decodes image and scales it to reduce memory consumption
    private Bitmap decodeFile(File f) {
        try {
            // decode image size
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            FileInputStream stream1 = new FileInputStream(f);
            BitmapFactory.decodeStream(stream1, null, o);
            stream1.close();

            // Find the correct scale value. It should be the power of 2.
            final int REQUIRED_SIZE = 280;
            int width_tmp = o.outWidth, height_tmp = o.outHeight;
            int scale = 1;
            while (true) {
                if (width_tmp / 2 < REQUIRED_SIZE || height_tmp / 2 < REQUIRED_SIZE)
                    break;
                width_tmp /= 2;
                height_tmp /= 2;
                scale *= 2;
            }

            // decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            FileInputStream stream2 = new FileInputStream(f);
            Bitmap bitmap = BitmapFactory.decodeStream(stream2, null, o2);
            stream2.close();
            return bitmap;
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Task for the queue
    private class PhotoToLoad {
        public String url;
        public ImageView imageView;
        public boolean weh;

        public PhotoToLoad(String u, ImageView i, boolean weh) {
            url = u;
            imageView = i;
            this.weh = weh;
        }

        public PhotoToLoad(String u, ImageView i) {
            this(u, i, false);
        }

        public void setBitmap(Bitmap bmp) {

            if (weh) {
                imageView.setImageBitmap(BitmapUtil.getRoundedCornerBitmap(bmp, roundPx));
            } else {
                imageView.setImageBitmap(bmp);
            }
        }

        public void setBitmapRes(int id) {
            imageView.setImageResource(id);
        }
    }

    class PhotosLoader implements Runnable {
        PhotoToLoad photoToLoad;

        PhotosLoader(PhotoToLoad photoToLoad) {
            this.photoToLoad = photoToLoad;
        }

        @Override
        public void run() {
            try {

                if (imageViewReused(photoToLoad))
                    return;
                Bitmap bmp = getBitmap(photoToLoad.url);
                if (bmp == null) {
                    memoryCache.put(photoToLoad.url, bmp);
                }
                if (imageViewReused(photoToLoad))
                    return;
                BitmapDisplayer bd = new BitmapDisplayer(bmp, photoToLoad);
                handler.post(bd);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    boolean imageViewReused(PhotoToLoad photoToLoad) {
        String tag = imageViews.get(photoToLoad.imageView);
        if (tag == null || !tag.equals(photoToLoad.url))
            return true;
        return false;
    }

    // Used to display bitmap in the UI thread
    class BitmapDisplayer implements Runnable {
        Bitmap bitmap;
        PhotoToLoad photoToLoad;

        public BitmapDisplayer(Bitmap b, PhotoToLoad p) {
            bitmap = b;
            photoToLoad = p;
        }

        public void run() {
            if (imageViewReused(photoToLoad))
                return;
            if (bitmap != null)
                photoToLoad.setBitmap(bitmap);
            // else
            // photoToLoad.setBitmapRes(stub_id);
        }
    }

    public void clearCache() {
        memoryCache.clear();
        fileCache.clear();
    }

}
