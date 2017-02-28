/*
 * www.fagnersouza.com.br
 */
package br.com.fagnersouza.academy.varchive;

import br.com.fagnersouza.academy.IVArchive;
import br.com.fagnersouza.log.Tracer;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.FileList;
import com.google.api.services.drive.model.ParentReference;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author fagner.souza
 */
public class VArchive implements IVArchive{
    private static VArchive instance;
    private static final String APPLICATION_NAME = "SAcademy";    
    private static final String SERVICE_ACCOUNT_EMAIL = "831303896161-50ig3o697dqrhmnhbtmehj2f6752gkhv@developer.gserviceaccount.com" ;    
    
    private static HttpTransport HTTP_TRANSPORT;
    private static JacksonFactory JSON_FACTORY;
    
    static{
        try{
            HTTP_TRANSPORT = new NetHttpTransport();
            JSON_FACTORY = new JacksonFactory();
        }catch(Exception ex){
            Tracer.logger.error("", ex);
        }
    }
    
    private VArchive(){
        
    }
    
    public static VArchive getInstance(){
        if(instance == null)
            instance = new VArchive();
        
        return instance;
    }
    
    private void diary(com.google.api.services.drive.model.File file){
        Tracer.logger.debug("Title:\t" + file.getTitle());
        Tracer.logger.debug("AlternateLink:\t" + file.getAlternateLink());       
    }
    
    public boolean save(File ... files){
        try{
            Drive service = service();
            com.google.api.services.drive.model.File body;
            
            for(File file:files){
                 body = new com.google.api.services.drive.model.File();
                 body.setTitle(file.getName());
                 //body.setDescription("A test document");
                 //body.setMimeType("text/plain");
                 FileContent mediaContent = new FileContent("text/plain", file);
                 diary(service.files().insert(body, mediaContent).execute());
            }                                                           
            
            return true;            
        }catch(Exception ex){
            Tracer.logger.error("", ex);
        }
        
        return false;
    }
        
    public List<File> dir(){
        try{        
            List<com.google.api.services.drive.model.File> result = new ArrayList<>();
            Drive service = service();
            com.google.api.services.drive.Drive.Files.List request = service.files().list();            
            
            do {
                try {
                    FileList files = request.execute();
                    
                    result.addAll(files.getItems());
                    request.setPageToken(files.getNextPageToken());
                } catch (IOException e) {
                    Tracer.logger.error("", e);
                    request.setPageToken(null);
                }
            } while (request.getPageToken() != null &&
                         request.getPageToken().length() > 0);
                
                Tracer.logger.debug("Dir size: " + result.size());
                return convert(result);                
        }catch(Exception ex){
            Tracer.logger.error("", ex);
        }
        
        return null;
    }
    
    private List<File> convert(List<com.google.api.services.drive.model.File> remoteFiles){
        List<File> files = new ArrayList<>();
        for(com.google.api.services.drive.model.File f:remoteFiles){
            File file;
            
            if(f.getParents().size() == 1){            
                file = new File("\\."+f.getTitle());
            }else{
                file = new File("\\"+f.getParents().get(1).getId()+"\\"+f.getTitle());
            }
            
            file.setReadOnly();
            file.setLastModified(f.getModifiedDate().getValue());
            file.setExecutable(false);
            files.add(file);
        }
        
        return files;
    }
    
    private Drive service(){
        try{
            Credential credential = authorize();
            
            Drive service = new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, null)
                                        .setHttpRequestInitializer(credential)
                                        .setApplicationName(APPLICATION_NAME)
                                        .build();
            
            return service;            
        }catch(Exception ex){
            Tracer.logger.error("", ex);
        }
        
        return null;
    }
    
    private Credential authorize(){
      try{
        java.io.File key = new java.io.File("C:\\Temp\\Google\\SAcademy.p12");
        
        Tracer.logger.debug("Key: " + key.exists());
        
        // service account credential (uncomment setServiceAccountUser for domain-wide delegation)
        GoogleCredential credential = new GoogleCredential.Builder()
            .setTransport(HTTP_TRANSPORT)
            .setJsonFactory(JSON_FACTORY)
            .setServiceAccountId(SERVICE_ACCOUNT_EMAIL)
            .setServiceAccountScopes(Collections.singleton(DriveScopes.DRIVE))
            .setServiceAccountPrivateKeyFromP12File(key)                    
            .build();
        
        return credential;
      } catch (GeneralSecurityException | IOException ex) {
            Tracer.logger.error("", ex);
      }
      
      return null;
    }
}
