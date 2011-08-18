package org.ancestris.trancestris.editors;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Properties;

import org.openide.util.Utilities;

/**
 *
 * @author claudio, daniel
 * This code is borrowed from http://nbmodules.javaforge.com/
 */
public class PropertiesLike extends Properties {

    public static final String LF = System.getProperty("line.separator");
    public static final String PROP_ESCAPE = "nb-settings.escape";

    public PropertiesLike() {
        super();
    }

    public PropertiesLike(Properties def) {
        super(def);
    }

    public PropertiesLike(String pathToLoad) throws IOException {
        super();
        FileInputStream fileIn = new FileInputStream(pathToLoad);
        load(fileIn);
        Util.close(fileIn);
    }

    @Override
    public synchronized void load(InputStream inStream) throws IOException {
        if (Utilities.isWindows()) {
            // quick hack, sorry
            // windows backslashes sucks
            BufferedReader bf = null;
            ByteArrayOutputStream byteOut = null;
            ByteArrayInputStream byteIn = null;
            try {
                bf = new BufferedReader(new InputStreamReader(inStream));
                String line = null;
                byteOut = new ByteArrayOutputStream();
                while ((line = bf.readLine()) != null) {
                    if (line.indexOf("jdkhome") > -1
                            || line.indexOf("default_userdir") > -1
                            || line.indexOf("default_mac_userdir") > -1) {
                        line = line.replace('\\', '/');

                    }
                    if (line.indexOf("default_options") > -1) {
                        line = line.replace("\\\"", "");
                        line = line.replace("\\", "/");
                        line = line.replace("\\t", "/t");
                    }
                    byteOut.write(line.getBytes());
                    byteOut.write(LF.getBytes());
                }
                byteOut.flush();
                byteIn = new ByteArrayInputStream(byteOut.toByteArray());
                // let the Properties class load the sanitized inputstream
                super.load(byteIn);

            } finally {
                Util.close(byteIn);
                Util.close(byteOut);
                Util.close(bf);
            }
        } else {
            super.load(inStream);
        }
    }

    public void store(OutputStream out, String comments) throws IOException {
        boolean escape = Boolean.getBoolean(PROP_ESCAPE);
        if (escape) {
            super.store(out, comments);
        } else {
            StringBuffer buff = new StringBuffer("###");
            buff.append(comments);
            buff.append(LF);
            Enumeration en = this.keys();
            while (en.hasMoreElements()) {
                String key = (String) en.nextElement();
                String value = getProperty(key);
                buff.append(key).append("=").append(value).append(LF);
            }
            // TODO: load charset encoding from netbeans advanced settings
            // and write it accordingly
            out.write(buff.toString().getBytes());
        }
    }

    public static void main(String[] args) throws Exception {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        Properties props = new PropertiesLike("/home/claudio/downloads/netbeans.conf");
        //props.setProperty("nb-settings.escape", "true");
        props.store(byteOut, "netbeans configuration file generated by nb-settings module");


//        PropertiesConfiguration config = new PropertiesConfiguration("C:\\Java\\NetBeans6.0.1\\etc\\netbeans.conf");
//        config.save(byteOut);
        System.out.println(byteOut.toString());

        System.out.println(System.getProperty("user.home"));
    }
}
