package com.setuga.summary.zip;

import java.io.*;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class Zip
{

    public static void compressFiles(String zipPath, List<String> files)
    {
        File zipFile = new File(zipPath);
        try (ZipOutputStream  zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFile)))
        {
            for (String targetFile : files)
            {
                File file = new File(targetFile);
                archive(zipOutputStream, file);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void unZip(File zip)
    {
        String fileName = zip.getName();
        int end = fileName.lastIndexOf(".");
        File directory = new File(zip.getParent(), fileName.substring(0, end));
        if (!directory.mkdir())
        {
            System.out.println("Couldn't make a directory because same name exists : " + directory.getName());
        }
        try(ZipFile zipFile = new ZipFile(zip))
        {
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements())
            {
                ZipEntry zipEntry = entries.nextElement();
                File outFile = new File(directory, zipEntry.getName());
                if (zipEntry.isDirectory())
                {
                    outFile.mkdir();
                }
                else
                {
                    try (BufferedInputStream bufferedInputStream = new BufferedInputStream(zipFile.getInputStream(zipEntry));
                         BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(outFile)))
                    {
                        if (!outFile.getParentFile().exists())
                        {
                            outFile.getParentFile().mkdir();
                        }

                        int read;
                        byte[] buffer = new byte[1024];
                        while ((read = bufferedInputStream.read(buffer)) != -1)
                        {
                            bufferedOutputStream.write(buffer, 0, read);
                        }
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static void archive(ZipOutputStream zipOutputStream, File targetFile)
    {
        if (targetFile.isDirectory())
        {
            File[] files = targetFile.listFiles();
            if (files != null)
            {
                for (File file : files)
                {
                    archive(zipOutputStream, file);
                }
            }
        }
        else
        {
            try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(targetFile)))
            {
                zipOutputStream.setLevel(4);
                zipOutputStream.putNextEntry(new ZipEntry(targetFile.getName()));
                int read;
                byte[] buffer = new byte[1024];
                while ((read = bufferedInputStream.read(buffer)) != -1)
                {
                    zipOutputStream.write(buffer, 0, read);
                }
                zipOutputStream.closeEntry();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

}
