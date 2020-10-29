package com.zipe.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);
    public static final double kilobyte = Math.pow(2.0D, 10.0D);
    public static final double megabyte = Math.pow(2.0D, 20.0D);
    public static final double gigabyte = Math.pow(2.0D, 30.0D);

    public FileUtil() {
    }

    public static File generate(long size) {
        return generate("", size);
    }

    public static File generate(String path, long size) {
        return generate(path, size, Long.toString(size));
    }

    public static File generate(String path, long size, FileUtil.sizeType sizeType) {
        size = getSize(size, sizeType);
        return generate("", size);
    }

    public static File generate(final String path, long size, final String fileName) {
        try {
            PrintWriter file = new PrintWriter(new BufferedWriter(new FileWriter(path + fileName + ".out")));

            for (long i = 0L; i < size; ++i) {
                file.append('a');
            }

            file.close();
            Runnable th = new Runnable() {
                public void run() {
                    try {
                        FileInputStream srcFile = new FileInputStream(path + fileName + ".out");
                        String md5 = DigestUtils.md5Hex(srcFile);
                        srcFile.close();
                        PrintWriter md5File = new PrintWriter(new BufferedWriter(new FileWriter(path + fileName + ".md5")));
                        md5File.append(md5);
                        md5File.close();
                    } catch (IOException var4) {
                        var4.printStackTrace();
                    } catch (Exception var5) {
                        var5.printStackTrace();
                    }

                }
            };
            Thread task = new Thread(th);
            task.start();
            return new File(path + fileName + ".out");
        } catch (IOException var7) {
            var7.printStackTrace();
        } catch (Exception var8) {
            var8.printStackTrace();
        }

        return null;
    }

    public static long getSize(long fileSize, FileUtil.sizeType sizeType) {
        long size = 0L;
        switch (sizeType) {
            case KB:
                size = fileSize * (long) kilobyte;
                break;
            case MB:
                size = fileSize * (long) megabyte;
                break;
            case GB:
                size = fileSize * (long) gigabyte;
                break;
            default:
                size = fileSize * (long) kilobyte;
        }

        return size;
    }

    public static void copyFile(String from, String to) throws IOException {
        int BUFF_SIZE = 100000;
        byte[] buffer = new byte[BUFF_SIZE];
        InputStream src = null;
        FileOutputStream dst = null;

        try {
            src = new FileInputStream(from);
            dst = new FileOutputStream(to);

            while (true) {
                int amountRead = src.read(buffer);
                if (amountRead == -1) {
                    return;
                }

                dst.write(buffer, 0, amountRead);
            }
        } finally {
            if (src != null) {
                src.close();
            }

            if (dst != null) {
                dst.close();
            }

        }
    }

    public static File[] listFiles(File path) {
        List<File> fileList = new ArrayList();
        if (path.isDirectory()) {
            File[] files = path.listFiles();

            for (int i = 0; i < files.length; ++i) {
                if (files[i].isDirectory()) {
                    File[] tmp = listFiles(files[i]);

                    for (int j = 0; j < tmp.length; ++j) {
                        fileList.add(tmp[j]);
                    }
                } else {
                    fileList.add(files[i]);
                }
            }
        } else {
            fileList.add(path);
        }

        return (File[]) fileList.toArray(new File[0]);
    }

    public static File[] listDirs(File path) {
        List<File> fileList = new ArrayList();
        if (path.isDirectory()) {
            File[] files = path.listFiles();

            for (int i = 0; i < files.length; ++i) {
                if (files[i].isDirectory()) {
                    fileList.add(files[i]);
                }
            }
        }

        return (File[]) fileList.toArray(new File[0]);
    }

    public static String readFile(String path) {
        StringBuilder sb = new StringBuilder();
        InputStream is = FileUtil.class.getResourceAsStream(path);
        BufferedReader buf = new BufferedReader(new InputStreamReader(is));

        try {
            for (String line = buf.readLine(); line != null; line = buf.readLine()) {
                sb.append(line).append("\n");
            }
        } catch (IOException var13) {
            logger.error("Read file error", var13);
        } finally {
            try {
                buf.close();
                is.close();
            } catch (Exception var12) {
                logger.error("Stream close error", var12);
            }

        }

        return sb.toString();
    }

    public static byte[] getByteFromFile(String path) throws IOException {
        File file = new File(path);
        byte[] buffer = new byte[(int) file.length()];
        FileInputStream is = null;

        try {
            is = new FileInputStream(file);
            if (is.read(buffer) == -1) {
                throw new IOException("EOF reached while trying to read the whole file");
            }
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException var10) {
            }

        }

        return buffer;
    }

    public static File getFileFromByte(byte[] data, String filePath) throws IOException {
        FileOutputStream stream = new FileOutputStream(filePath);
        Throwable var3 = null;

        try {
            stream.write(data);
        } catch (Throwable var12) {
            var3 = var12;
            throw var12;
        } finally {
            if (stream != null) {
                if (var3 != null) {
                    try {
                        stream.close();
                    } catch (Throwable var11) {
                        var3.addSuppressed(var11);
                    }
                } else {
                    stream.close();
                }
            }

        }

        return new File(filePath);
    }

    public static enum sizeType {
        KB,
        MB,
        GB;

        private sizeType() {
        }
    }
}
