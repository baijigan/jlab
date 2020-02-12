package cn.org.rsun;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ObjLoader {
    public static Set<Class<?>> scanPackage( ){
        Hashtable<Class<?>, String> cls= getClasses("cn.org.rsun");
        Set<Class<?>> result= new HashSet<Class<?>>( );

        Enumeration enm = cls.keys();
        while(enm.hasMoreElements())
        {
            Class<?> key = (Class<?>)enm.nextElement();
            String value = (String)cls.get(key);
            if(value.equals("AutoWired")){
                result.add( key );
            }
        }

        return result;
    }

    /**
     * 从包package中获取所有的Class
     *
     * @param pack 指定扫描的包
     * @return 包里面的所有文件
     */
    public static Hashtable<Class<?>, String> getClasses(String pack) {
        Hashtable<Class<?>, String> classes = new Hashtable<Class<?>, String>();
        boolean recursive = true;

        String packageName = pack;
        String packageDirName = packageName.replace('.', '/');

        Enumeration<URL> dirs;
        try {
        dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
        while (dirs.hasMoreElements()) {
            URL url = dirs.nextElement();

            String protocol = url.getProtocol();
            if ("file".equals(protocol)) {
                String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                System.out.println(filePath);
                findAndAddClassesInPackageByFile(packageName, filePath, recursive, classes);

            } else if ("jar".equals(protocol)) {
                JarFile jar;
                jar = ((JarURLConnection) url.openConnection()).getJarFile();
                Enumeration<JarEntry> entries = jar.entries();

                while (entries.hasMoreElements()) {
                    // 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
                    JarEntry entry = entries.nextElement();
                    String name = entry.getName();
                    if (name.charAt(0) == '/') {
                        name = name.substring(1);
                    }

                    if (name.startsWith(packageDirName)) {
                        int idx = name.lastIndexOf('/');
                        if (idx != -1) {
                            packageName = name.substring(0, idx)
                                    .replace('/', '.');
                        }

                        if ((idx != -1) || recursive) {
                            if (name.endsWith(".class") && !entry.isDirectory()) {
                                String className = name.substring(
                                        packageName.length() + 1, name
                                                .length() - 6);
                                try {
                                    Class cls= Class
                                            .forName(packageName + '.'
                                                    + className);
                                    if(cls.isAnnotationPresent(AutoWired.class))classes.put(cls, "AutoWired");
                                    else classes.put(cls, "");
                                } catch (ClassNotFoundException e) { e.printStackTrace(); }
                            }
                        }
                    }
                }
            }
        }

        } catch (IOException e) { e.printStackTrace(); }
        return classes;
    }

    /**
     * 以文件的形式来获取包下的所有Class
     *
     * @param packageName 包的名字
     * @param packagePath 包的路径
     * @param recursive
     * @param classes 文件列表
     */
    public static void findAndAddClassesInPackageByFile(String packageName,
                                                        String packagePath,
                                                        final boolean recursive,
                                                        Hashtable<Class<?>, String> classes) {
        File dir = new File(packagePath);
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }

        File[] dirfiles = dir.listFiles(new FileFilter() {
            // 自定义过滤规则
            public boolean accept(File file) {
                return (recursive && file.isDirectory())
                        || (file.getName().endsWith(".class"));
            }
        });

        for (File file : dirfiles) {
            if (file.isDirectory()) {
                findAndAddClassesInPackageByFile(packageName + "."
                                + file.getName(), file.getAbsolutePath(), recursive,
                        classes);
            } else {
                String className = file.getName().substring(0,
                        file.getName().length() - 6);
                try {
                    // 添加到集合中去
                    Class cls= Thread.currentThread().getContextClassLoader().loadClass(
                            packageName + '.' +
                                    className);
                    if(cls.isAnnotationPresent(AutoWired.class))classes.put(cls, "AutoWired");
                    else classes.put(cls, "");

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
