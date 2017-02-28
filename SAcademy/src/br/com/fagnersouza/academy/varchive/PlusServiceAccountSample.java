/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.fagnersouza.academy.varchive;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.Drive.Children;
import com.google.api.services.drive.Drive.Files;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.ChildList;
import com.google.api.services.drive.model.ChildReference;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.api.services.drive.model.Permission;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 *
 * @author fagner.souza
 */
public class PlusServiceAccountSample {


          private static final String APPLICATION_NAME = "SAcademy";

          /** E-mail address of the service account. */
          private static final String SERVICE_ACCOUNT_EMAIL = "831303896161-50ig3o697dqrhmnhbtmehj2f6752gkhv@developer.gserviceaccount.com" ;

          /** Global instance of the HTTP transport. */
          private static HttpTransport httpTransport;

          /** Global instance of the JSON factory. */
          private static final JsonFactory JSON_FACTORY = new JacksonFactory();


          public static void main(String[] args) {
            try {
              try {
                httpTransport = new NetHttpTransport();
                java.io.File key = new java.io.File("C:\\Temp\\Google\\SAcademy.p12");
                
                System.out.println("Key: " + key.exists());

                // service account credential (uncomment setServiceAccountUser for domain-wide delegation)
                GoogleCredential credential = new GoogleCredential.Builder()
                    .setTransport(httpTransport)
                    .setJsonFactory(JSON_FACTORY)
                    .setServiceAccountId(SERVICE_ACCOUNT_EMAIL)
                    .setServiceAccountScopes(Collections.singleton(DriveScopes.DRIVE))
                    .setServiceAccountPrivateKeyFromP12File(key)                    
                    .build();
                


                Drive service = new Drive.Builder(httpTransport, JSON_FACTORY,credential).build();
                Permission newPermission = new Permission();
                newPermission.setValue("fagner.luiz.de.souza@gmail.com");
                newPermission.setType("user");
                newPermission.setRole("reader");
                

                //Insert a file  
                /*
                com.google.api.services.drive.model.File body = new com.google.api.services.drive.model.File();
                body.setTitle("My document");
                body.setDescription("A test document");
                body.setMimeType("text/plain");

                java.io.File fileContent = new java.io.File("C:\\Temp\\Google\\document.txt");
                FileContent mediaContent = new FileContent("text/plain", fileContent);
                
                
                com.google.api.services.drive.model.File file = service.files().insert(body, mediaContent).execute();
                service.permissions().insert(file.getId(), newPermission).execute();
                System.out.println("File ID: " + file.getId());
                System.out.println("Title: " + file.getTitle()+ " "+ file.getAlternateLink());
                */
                List<File> result = new ArrayList<File>();
                        Files.List request = service.files().list();

                        do {
                          try {
                            FileList files = request.execute();

                            result.addAll(files.getItems());
                            request.setPageToken(files.getNextPageToken());
                          } catch (IOException e) {
                            System.out.println("An error occurred: " + e);
                            request.setPageToken(null);
                          }
                        } while (request.getPageToken() != null &&
                                 request.getPageToken().length() > 0);


                        System.out.println("result: "+result.size());
                        for (File f : result) {
                            System.out.println(""+f.getId() + ";"+f.getTitle());
                        }


                return;
              } catch (IOException e) {
                System.err.println(e.getMessage());
              }
            } catch (Throwable t) {
              t.printStackTrace();
            }
            System.exit(1);
          }
}
