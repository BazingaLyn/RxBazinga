package org.lyncc.bazinga.rx.bazinga.bundle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

public class StandardUrlClassLoader extends URLClassLoader {

    private final static String baseDir = System.getProperty("user.dir") + File.separator + "ext" + File.separator;


    public StandardUrlClassLoader(String version){
        super(new URL[]{},null);
        loadResource(version);
    }

    private void loadResource(String version) {

        String jarPath = baseDir + version;

        tryLoadJarInDir(jarPath);
        tryLoadJarInDir(jarPath + File.separator + "lib");

    }

    private void tryLoadJarInDir(String jarPath) {

        File dir = new File(jarPath);

        if(dir.exists() && dir.isDirectory()){
            for (File file : dir.listFiles()) {
                if (file.isFile() && file.getName().endsWith(".jar")) {
                    this.addURL(file);
                    continue;
                }
            }
        }
    }


    private void addURL(File file){
        try {
            super.addURL(new URL("file", null, file.getCanonicalPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {

        System.out.println("Class loader:" + name);
        return super.loadClass(name);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }
}
