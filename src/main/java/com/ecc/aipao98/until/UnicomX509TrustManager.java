package com.ecc.aipao98.until;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class UnicomX509TrustManager implements X509TrustManager {

    private X509TrustManager sunJSSEX509TrustManager;

    public UnicomX509TrustManager() throws Exception {
        // Create a "default" JSSE X509TrustManager.
        KeyStore keyStore = KeyStore.getInstance("JKS");
        // 
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("SunX509",
            "SunJSSE");
        trustManagerFactory.init(keyStore);
        TrustManager trustManagers[] = trustManagerFactory.getTrustManagers();
        /*
         * Iterate over the returned trustmanagers, look for an instance of
         * X509TrustManager. If found, use that as our "default" trust manager.
         */
        for (int i = 0; i < trustManagers.length; i++) {
            if (trustManagers[i] instanceof X509TrustManager) {
                this.sunJSSEX509TrustManager = (X509TrustManager) trustManagers[i];
                return;
            }
        }
        /*
         * Find some other way to initialize, or else we have to fail the
         * constructor.
         */
        throw new Exception("Couldn't initialize");
    }

    /*
     * Delegate to the default trust manager.
     */
    public void checkClientTrusted(X509Certificate[] chain, String authType)
                                                                            throws CertificateException {
        try {
            this.sunJSSEX509TrustManager.checkClientTrusted(chain, authType);
        } catch (CertificateException e) {
            // do any special handling here, or rethrow exception.
        }
    }

    /*
     * Delegate to the default trust manager.
     */
    public void checkServerTrusted(X509Certificate[] chain, String authType)
                                                                            throws CertificateException {
        try {
            this.sunJSSEX509TrustManager.checkServerTrusted(chain, authType);
        } catch (CertificateException e) {
            /*
             * Possibly pop up a dialog box asking whether to trust the cert
             * chain.
             */
        }
    }

    /*
     * Merely pass this through.
     */
    public X509Certificate[] getAcceptedIssuers() {
        return this.sunJSSEX509TrustManager.getAcceptedIssuers();
    }
}