package com.e3.web.test;

import com.e3.util.common.FastDFSClient;

public class TestFastDFS {
    public static void main(String[] args) throws Exception {
       /* // 1、加载配置文件，配置文件中的内容就是tracker服务的地址。
        ClientGlobal.init("E:\\mikey\\e3\\e3-web-manager\\src\\main\\resources\\client.conf");
        // 2、创建一个TrackerClient对象。直接new一个。
        TrackerClient trackerClient = new TrackerClient();
        // 3、使用TrackerClient对象创建连接，获得一个TrackerServer对象。
        TrackerServer trackerServer = trackerClient.getConnection();
        // 4、创建一个StorageServer的引用，值为null
        StorageServer storageServer = null;
        // 5、创建一个StorageClient对象，需要两个参数TrackerServer对象、StorageServer的引用
        StorageClient storageClient = new StorageClient(trackerServer, storageServer);
        // 6、使用StorageClient对象上传图片。
        //扩展名不带“.”
        String[] strings = storageClient.upload_file("E:\\图片\\1.jpg", "jpg", null);
        // 7、返回数组。包含组名和图片的路径。
        for (String string : strings) {
            System.out.println(string);
        }*/
        FastDFSClient fastDFSClient = new FastDFSClient("E:\\mikey\\e3\\e3-web-manager\\src\\main\\resources\\client.conf");
        String file = fastDFSClient.uploadFile("E:\\images\\4.jpg");
        System.out.println(file);
    }
}
