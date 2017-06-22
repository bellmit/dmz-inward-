//package com.dmz.service;
//
//import org.apache.http.ProtocolVersion;
//import org.apache.http.client.entity.DecompressingEntity;
//import org.apache.http.client.entity.InputStreamFactory;
//import org.apache.http.message.BasicHttpResponse;
//import org.apache.http.message.BasicStatusLine;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.nio.charset.Charset;
//import java.util.zip.GZIPInputStream;
//
///**
// * @author dmz
// * @date 2017/3/24
// */
//public class CompressDe {
//
//    private  static InputStreamFactory GZIP = new InputStreamFactory() {
//
//        @Override
//        public InputStream create(final InputStream instream) throws IOException {
//            return new GZIPInputStream(instream);
//        }
//    };
//    public static void main(String[] args) throws IOException {
//        BasicHttpResponse response = new BasicHttpResponse(new BasicStatusLine(new ProtocolVersion("1", 2, 3), 200, "Wrong"));
//        DecompressingEntity entry = new DecompressingEntity(response.getEntity(), GZIP);
//        InputStream respStream = entry.getContent();
//
//        input = new BufferedReader(new InputStreamReader(respStream, Charset.forName("UTF-8")));
//        StringBuilder strBuilder = new StringBuilder();
//        char[] buffer = new char[1024];
//        int count;
//        while ((count = input.read(buffer)) > 0) {
//            strBuilder.append(buffer, 0, count);
//        }
//    }
//}
