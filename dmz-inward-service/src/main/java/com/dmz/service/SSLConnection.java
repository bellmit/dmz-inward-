package com.dmz.service;

import com.dmz.service.utils.ByteCharFileUtil;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.ssl.SSLContexts;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

/**
 * @author dmz
 * @tag
 * @memo 2016/6/2
 */
public abstract class SSLConnection {

    private String keyStorePasswd = "123456";
    private String trustStorePasswd = "123456";
    private String version = "TLSv1.2";
    protected Registry<ConnectionSocketFactory> socketFactoryRegistry;

    protected void initSSLConnection() {
        InputStream keyFileStream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("keystore.jks");
        if (keyFileStream == null) {
            keyFileStream=this.getClass().getResourceAsStream("keystore.jks");
        }
        InputStream trustFileStream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("truststore.jks");
        if (trustFileStream == null) {
            trustFileStream=this.getClass().getResourceAsStream("truststore.jks");
        }
        File keyFile = null;
        File trustFile = null;
        try {

            keyFile = File.createTempFile("keystore", "jks");
            trustFile = File.createTempFile("truststore", "jks");

            ByteCharFileUtil.Byte2File(keyFile, keyFileStream);
            ByteCharFileUtil.Byte2File(trustFile, trustFileStream);

            SSLContext context = SSLContexts.custom()
                    .loadKeyMaterial(keyFile,
                            keyStorePasswd.toCharArray(),
                            trustStorePasswd.toCharArray())
                    .loadTrustMaterial(trustFile,
                            trustStorePasswd.toCharArray())
                    .build();

            SSLConnectionSocketFactory sf = new SSLConnectionSocketFactory(
                    context,
                    new String[]{version},
                    null,
                    SSLConnectionSocketFactory.getDefaultHostnameVerifier());

            socketFactoryRegistry = RegistryBuilder
                    .<ConnectionSocketFactory>create().register("https", sf)
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } finally {
            if (keyFile != null) {
                keyFile.delete();
            }
            if (trustFile != null) {
                trustFile.delete();
            }
        }
    }

    public String getKeyStorePasswd() {
        return keyStorePasswd;
    }

    public void setKeyStorePasswd(String keyStorePasswd) {
        this.keyStorePasswd = keyStorePasswd;
    }

    public String getTrustStorePasswd() {
        return trustStorePasswd;
    }

    public void setTrustStorePasswd(String trustStorePasswd) {
        this.trustStorePasswd = trustStorePasswd;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
