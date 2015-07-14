package com.ban.incl.instantclass.util;

import android.os.AsyncTask;
import android.util.Log;

import com.ban.incl.instantclass.vo.ClassVO;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class phpDown extends AsyncTask<String, Integer, String>{

    private String sIpAddr = "http://incladmin.cafe24.com/";

    private String sMode;

    private ClassVO insertItem = new ClassVO();

    public void setInsertItem(ClassVO insertItem) {
        this.insertItem = insertItem;
    }

    public phpDown() {

    }

    public String getMode() {
        return sMode;
    }

    public void setMode(String sMode) {
        this.sMode = sMode;
    }

    @Override
    protected String doInBackground(String... urls) {
        String sReturn = null;

        if(sMode == "SELECT") {
            sReturn = getSelect(sIpAddr+urls[0]);
        } else if(sMode == "INSERT") {
            sReturn = insertData(sIpAddr+urls[0]);
        } else if(sMode == "DETAIL") {
            sReturn = getSelectDetail(sIpAddr+urls[0]);
        }

        return sReturn;

    }

    private String getSelect(String url1) {
        StringBuilder jsonHtml = new StringBuilder();
        try{
            URL url = new URL(url1);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();

            if(conn != null){
                conn.setConnectTimeout(10000);
                conn.setUseCaches(false);

                if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                    for(;;){

                        String line = br.readLine();
                        if(line == null) break;

                        jsonHtml.append(line + "\n");
                    }
                    br.close();
                } else {
                }
                conn.disconnect();
            } else {
            }
        } catch(Exception ex){
            ex.printStackTrace();
        }

        Log.d("Incl_Debug", jsonHtml.toString());
        return jsonHtml.toString();
    }

    private String insertData(String url1) {
        if(insertItem == null) { return null; }

        ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();

        param.add(new BasicNameValuePair("title", insertItem.getTitle()));
        param.add(new BasicNameValuePair("content", insertItem.getContent()));
        param.add(new BasicNameValuePair("date", insertItem.getDate()));
        param.add(new BasicNameValuePair("start_time", insertItem.getStartTime()));
        param.add(new BasicNameValuePair("end_time", insertItem.getEndTime()));
        param.add(new BasicNameValuePair("addr", insertItem.getAddr()));
        param.add(new BasicNameValuePair("price", insertItem.getPrice()));

        HttpClient client = new DefaultHttpClient();

        HttpParams params = client.getParams();
        HttpConnectionParams.setConnectionTimeout(params, 5000);
        HttpConnectionParams.setSoTimeout(params, 5000);

        HttpPost httpPost = new HttpPost(url1);

        try {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(param, "UTF-8");
            httpPost.setEntity(entity);
            client.execute(httpPost);
            return EntityUtils.getContentCharSet(entity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String getSelectDetail(String url1) {
        StringBuilder jsonHtml = new StringBuilder();
        try{
            URL url = new URL(url1);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();

            conn.setDefaultUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");

            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");

            StringBuffer buffer = new StringBuffer();
            buffer.append("class_id").append("=").append(insertItem.getClassId());

            Log.d("inclTest", "detail >> " + buffer);

            OutputStreamWriter outStream = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            PrintWriter writer = new PrintWriter(outStream);
            writer.write(buffer.toString());
            writer.flush();

            if(conn != null){
                conn.setConnectTimeout(10000);
//                conn.setUseCaches(false);

                if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                    for(;;){

                        String line = br.readLine();
                        if(line == null) break;

                        jsonHtml.append(line + "\n");
                    }
                    br.close();
                } else {
                }
                conn.disconnect();
            } else {
            }
        } catch(Exception ex){
            ex.printStackTrace();
        }
        Log.d("inclTest", jsonHtml.toString());
        return jsonHtml.toString();
    }

    // nothing to do
    protected void onPostExecute(String str){
//        Log.d("phpTest", "phpDown >> onPostExecute >> Json : " + str);
//        Log.d("phpTest", "phpDown >> onPostExecute >> start");
//        ClassVO vo;
//        ArrayList<ClassVO> list = new ArrayList<ClassVO>();
//        try{
//
//            JSONObject root = new JSONObject(str);
//            JSONArray ja = root.getJSONArray("results");
//
//            Log.d("phpTest", "phpDown >> onPostExecute >> ja.length() : " + ja.length());
//            for(int i=0; i<ja.length(); i++){
//                JSONObject jo = ja.getJSONObject(i);
//
//                vo = new ClassVO();
//                vo.setClassId(jo.getInt("class_id"));
//                vo.setTitle(jo.getString("title"));
//                vo.setContent(jo.getString("content"));
//                vo.setStartTime(jo.getString("start_time"));
//                vo.setEndTime(jo.getString("end_time"));
//                vo.setAddr(jo.getString("addr"));
//                vo.setPrice(jo.getString("price"));
//
//                list.add(vo);
//            }
//        }catch(JSONException e){
//            e.printStackTrace();
//        }
//        Log.d("phpTest", "phpDown >> onPostExecute >> list.size() : " + list.size());
//        Log.d("phpTest", "phpDown >> onPostExecute >> call processFinish");
//        delegate.processFinish(list);
    }


}