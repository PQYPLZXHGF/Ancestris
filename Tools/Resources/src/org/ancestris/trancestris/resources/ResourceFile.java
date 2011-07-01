package org.ancestris.trancestris.resources;

// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ResourceFile.java
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

public class ResourceFile {

    private static final String PREFIX = "Bundle";
    private static final String SUFFIX = ".properties";
    private String fromBundleName = "";
    private String toBundleName = "";
    private File DefaultLangageFile = null;
    private Properties defaultLangage = new Properties();
    private Properties translatedLangage = new Properties();
    private ArrayList<ResourceLine> content = null;
    private int not_translated;
    private List<PropertyChangeListener> listeners = Collections.synchronizedList(new LinkedList());
    private TreeMap<String, Properties> resourceFiles = new TreeMap();

    ResourceFile() {
        not_translated = 0;
    }

    public void put(InputStream inputStream, String bundleName) throws IOException {
        Properties properties = new Properties();

        properties.load(inputStream);
        resourceFiles.put(bundleName, properties);
    }

    public Set<String> getFiles() {
        return resourceFiles.keySet();
    }

    public void writeTo(OutputStream outputStream, String bundleName) throws IOException {
        Properties properties = null;
        if (bundleName.equals(toBundleName)) {
            properties = translatedLangage;
        } else {
            properties = resourceFiles.get(bundleName);
        }
        properties.store(outputStream, "Translated with Trancetris");
    }

    void setTranslation(Locale fromLocale, Locale toLocale) {
        Iterator it = null;

        if (fromLocale.getLanguage().equals("en")) {
            fromBundleName = PREFIX + SUFFIX;
        } else {
            fromBundleName = PREFIX + "_" + fromLocale.getLanguage() + SUFFIX;
        }

        defaultLangage = resourceFiles.get(fromBundleName);
        if (defaultLangage != null) {
            content = new ArrayList<ResourceLine>(defaultLangage.size());
            it = defaultLangage.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pairs = (Map.Entry) it.next();
                content.add(new ResourceLine((String) pairs.getKey(), (String) pairs.getValue()));
            }

            if (toLocale.getLanguage().equals("en")) {
                toBundleName = PREFIX + SUFFIX;
            } else {
                toBundleName = PREFIX + "_" + toLocale.getLanguage() + SUFFIX;
            }

            translatedLangage = resourceFiles.get(toBundleName);
            if (translatedLangage == null) {
                translatedLangage = new Properties();
                resourceFiles.put(toBundleName, translatedLangage);
            }

            it = defaultLangage.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pairs = (Map.Entry) it.next();
                if (translatedLangage.getProperty((String) pairs.getKey()) != null) {
                    not_translated = Math.max(0, not_translated - 1);
                }
            }
        }
    }

    public boolean isTranslated() {
        return not_translated == 0;
    }

    public int getLineCount() {
        return defaultLangage.size();
    }

    public String getLine(int i) {
        return content.get(i).propertyValue;
    }

    public String getLineTranslation(int i) {
        String key = content.get(i).getKey();
        String s2 = (String) translatedLangage.get(key);
        if (s2 != null) {
            return s2;
        } else {
            return "";
        }
    }

    public void setLineTranslation(int i, String s) {
        String key = content.get(i).getKey();
        String old = content.get(i).getValue();
        if (translatedLangage.get(key) == null) {
            not_translated--;
        }
        translatedLangage.put(key, s);

        fire(key, old, s);
    }

    public int getLineState(int i) {

        if (translatedLangage.containsKey(content.get(i).getKey()) == true) {
            String Origin = defaultLangage.getProperty(content.get(i).getKey());

            if (Origin.equalsIgnoreCase(translatedLangage.getProperty(content.get(i).getKey()))) {
                return -1;
            } else {
                return 1;
            }
        } else {
            return 0;
        }
    }

    public File getDefaultBundleFile() {
        return DefaultLangageFile;
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        listeners.add(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        listeners.remove(pcl);
    }

    private void fire(String propertyName, Object old, Object nue) {
        //Passing 0 below on purpose, so you only synchronize for one atomic call
        @SuppressWarnings(value = "unchecked")
        PropertyChangeListener[] pcls = (PropertyChangeListener[]) listeners.toArray(new PropertyChangeListener[0]);
        for (int i = 0; i < pcls.length; i++) {
            pcls[i].propertyChange(new PropertyChangeEvent(this, propertyName, old, nue));
        }
    }
}
