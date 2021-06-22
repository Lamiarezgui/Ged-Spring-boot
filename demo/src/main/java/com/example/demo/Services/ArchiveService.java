package com.example.demo.Services;

import com.example.demo.Model.Archive;
import com.example.demo.Model.FileEntity;
import com.example.demo.Repository.ArchiveRepository;
import com.example.demo.Repository.FichierRepository;
import com.example.demo.Repository.VersionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
@AllArgsConstructor
public class ArchiveService {

    private final ArchiveRepository archiveRepository;
    private final VersionRepository versionRepository;
    private final FichierRepository fichierRepository;

    public void save(Archive a) {
        Archive archive = new Archive();
        archive.setCin(a.getCin());
        archive.setDate(LocalDateTime.now());
        archive.setMatricule(a.getMatricule());
        archive.setMotdeCle(a.getMotdeCle());
        archive.setNbrePieces(a.getNbrePieces());
        archive.setNomPersonnel(a.getNomPersonnel());
        archive.setNumDoss(a.getNumDoss());
        archive.setSerie(a.getSerie());
        archive.setTheme_titre(a.getTheme_titre());
        archive.setPrivilege(a.getPrivilege());
        archiveRepository.save(archive);
    }


    public List<Object> getAllDossiers() {
        return archiveRepository.getAll();

    }

    public List<Object> getContenus(String numDoss) {
        return archiveRepository.getContenu(numDoss);
    }


    public void addToArchive(String id, String archive_id) {
        versionRepository.ajouterVersionsArchive(id, archive_id);
        fichierRepository.ajouterFilesArchive(id, archive_id);
    }
    public void addVar(String numDoss) {
        Archive f=  archiveRepository.findByDoss(numDoss);
        int v=f.getVar();
        int res=v+1;
        System.out.println(res);
        archiveRepository.addVar(numDoss,res);
    }


    public List<FileEntity> getFiles(String numDoss) {
        return archiveRepository.getfiles(numDoss);
    }

/* public void download(HttpServletResponse response,
                      String numDoss) throws IOException {

        ZipEntry zipEntry = null;

        // Get the list of attachments with the specified ID

     List<String> liste= getFiles(numDoss);


        String path = "";
        String name = "";
        if (liste.size() == 1) {
             String attachment = liste.get(0);

        } else {
            long nowTime = new Date().getTime();
            name = "attachment_" + nowTime + ".zip";
            path = fileToZip(liste);
        }

     OutputStream out = null;
        BufferedInputStream br = null;

        try {

            String fileName = URLEncoder.encode(name, "UTF-8");
            br = new BufferedInputStream(new FileInputStream(path));
            byte[] buf = new byte[1024];
            int len = 0;
            response.reset(); // very important
            // pure download mode
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            out = response.getOutputStream();
            while ((len = br.read(buf)) > 0)
                out.write(buf, 0, len);
            out.flush();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            br.close();
            out.close();
        }
    }

    public static String fileToZip(List<String> list) {
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        ZipOutputStream zos = null;


        String path = System.getProperty("java.io.tmpdir")+"Desktop" ;
        try {
            File zipFile = new File(path);
            // delete if the zip file exists
            zipFile.deleteOnExit();

            // Create a new file
            zipFile.createNewFile();

            fos = new FileOutputStream(zipFile);
            zos = new ZipOutputStream(fos);
            byte[] bufs = new byte[1024 * 10];
            for (String fileEntity : list) {

                File subFile = new File(fileEntity);
                //If the file does not exist, it is not added to the zip package
                if (!subFile.exists()) {
                    continue;
                }
                //File name increase time stamp to avoid duplication
                String subFileName = fileEntity + "-" + new Date().getTime();
                //Create a ZIP entity and add it to the tarball
                ZipEntry zipEntry = new ZipEntry(subFileName);
                zos.putNextEntry(zipEntry);
                //Read the file to be compressed and write it into the compressed package
                fis = new FileInputStream(subFile);
                bis = new BufferedInputStream(fis, 1024 * 10);
                int read = 0;
                while ((read = bis.read(bufs, 0, 1024 * 10)) != -1) {
                    zos.write(bufs, 0, read);
                }
            }
            System.out.println("compression success");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            //Close the stream
            try {
                if (null != bis) bis.close();
                if (null != zos) zos.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        return path;
    }

*/

}
