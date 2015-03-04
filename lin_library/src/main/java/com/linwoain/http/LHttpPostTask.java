/**
 *知我者为我心忧，不知我者谓我何求！
 *linwoain@outlook.com
 *作者 linwoain
 *日期 2014/10/19 20:34
 */
package com.linwoain.http;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.os.AsyncTask;

/**
 * @author linwoain
 * @version 2014/10/19 20:34
 */
public class LHttpPostTask extends AsyncTask<Void, Void, String> {


    private static final String ERROR = "error_code";
    private LHttpParams params;
    private String url;
    private LHttpHandler handler;

    public LHttpPostTask(String url, LHttpParams params, LHttpHandler handler) {
        this.url = url;
        this.params = params;
        this.handler = handler;
    }


    @Override
    protected String doInBackground(Void... param) {

        try {
            URL u = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) u.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            bw.write(params.toString());
            //此次必须调用flush方法
            bw.flush();
            int code = conn.getResponseCode();
            if (200 == code) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line = null;
                StringBuffer buffer = new StringBuffer();
                while ((line = br.readLine()) != null) {
                    buffer.append(line);
                }
                br.close();
                bw.close();
                return buffer.toString();
            } else {
                bw.close();
                return ERROR + code;
            }
       

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        if (handler != null) {
            if (null != s) {
                if (s.startsWith(ERROR)) {
                    handler.onFailure("返回码出错，" + s.substring(ERROR.length()));
                } else {
                    handler.onSuccess(s);
                }
            } else {
                handler.onFailure("网络连接超时，无返回码");
            }
        }
        super.onPostExecute(s);
    }


}
