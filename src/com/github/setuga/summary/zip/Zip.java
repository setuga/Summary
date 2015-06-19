package com.github.setuga.summary.zip;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;

public class Zip
{

    public static void compressFiles(String zipPath, ArrayList<String> files)
    {
        File zipFile = new File(zipPath);
        ZipOutputStream zipOutputStream = null;
        try
        {
            zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFile));
            for (String targetFile : files)
            {
                File file = new File(targetFile);
                archive(zipOutputStream, file);
            }
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (zipOutputStream != null)
                {
                    zipOutputStream.close();
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void unZip(File zip) throws IOException
    {
        String fileName = zip.getName();
        int end = fileName.lastIndexOf(".");
        File directory = new File(zip.getParent(), fileName.substring(0, end));
        if (!directory.mkdir())
        {
            System.out.println("Couldn't make a directory because directory with same name exists : " + directory.getName());
        }
        ZipFile zipFile = new ZipFile(zip);
        Enumeration<? extends ZipEntry> entries = zipFile.getEntries();
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
                BufferedInputStream bufferedInputStream = null;
                BufferedOutputStream bufferedOutputStream = null;
                try
                {
                    bufferedInputStream = new BufferedInputStream(zipFile.getInputStream(zipEntry));
                    if (!outFile.getParentFile().exists())
                    {
                        outFile.getParentFile().mkdir();
                    }
                    bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(outFile));
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
                finally
                {
                    if (bufferedInputStream != null)
                    {
                        bufferedInputStream.close();
                    }
                    if (bufferedOutputStream != null)
                    {
                        bufferedOutputStream.close();
                    }
                }
            }
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
            BufferedInputStream bufferedInputStream = null;
            try
            {
                bufferedInputStream = new BufferedInputStream(new FileInputStream(targetFile));
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
            finally
            {
                try
                {
                    if (bufferedInputStream != null)
                    {
                        bufferedInputStream.close();
                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

}
