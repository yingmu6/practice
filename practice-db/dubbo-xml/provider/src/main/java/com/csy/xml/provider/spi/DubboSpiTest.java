package com.csy.xml.provider.spi;

import org.apache.dubbo.common.extension.ExtensionLoader;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

public class DubboSpiTest {
    public static void main(String[] args) throws IOException {
        // 自适应获取
//        Animal animal = ExtensionLoader.getExtensionLoader(Animal.class).getDefaultExtension();

        Animal animal = ExtensionLoader.getExtensionLoader(Animal.class).getExtension("dog");
        animal.cry();
        testLoadFile();
    }

    private static void testLoadFile() throws IOException {
        String fileName = "META-INF/dubbo/com.basic.com.basic.use.spi.Animal";
        File file = new File("META-INF/internal/com.basic.com.basic.use.spi.Animal");
        System.out.println(file.exists());
        ClassLoader classLoader = ExtensionLoader.class.getClassLoader();
        Enumeration<URL> urls = classLoader.getResources(fileName);
        if (urls != null) {
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                System.out.println(url.getPath());
            }
        }
    }
}
