package lodeinfo.controller;

import lodeinfo.model.ForAdminsFileEntity;
import lodeinfo.repository.ForAdminsFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.Iterator;

@CrossOrigin
@RestController
public class ForAdminsFileController {

    @Autowired
    ForAdminsFileRepository fileRepository;

    // DOWNLOAD
    @RequestMapping(
            value = "/forAdmins/downloadFile/{id}",
            method = RequestMethod.GET)
    public ResponseEntity downloadFile(@PathVariable Long id) {

        ForAdminsFileEntity fileUpload = fileRepository.findById(id);

        // No file found based on the supplied filename
        if (fileUpload == null) {
            return new ResponseEntity<>("{}", HttpStatus.NOT_FOUND);
        }

        // Generate the http headers with the file properties
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-disposition", "attachment; filename=" + fileUpload.getName());

        // Split the mimeType into primary and sub types
        String primaryType, subType;
        try {
            primaryType = fileUpload.getMimeType().split("/")[0];
            subType = fileUpload.getMimeType().split("/")[1];
        } catch (IndexOutOfBoundsException | NullPointerException ex) {
            return new ResponseEntity<>("{}", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        headers.setContentType(new MediaType(primaryType, subType));

        return new ResponseEntity<>(fileUpload.getFile(), headers, HttpStatus.OK);
    }

    // UPLOAD
    @RequestMapping(
            value = "/forAdmins/uploadFiles",
            method = RequestMethod.POST
    )
    public ResponseEntity uploadFile(MultipartHttpServletRequest request) {

        try {

            Long newsId = Long.parseLong(request.getParameter("newsId"));

            Iterator<String> itr = request.getFileNames();

            while (itr.hasNext()) {
                String uploadedFile = itr.next();
                MultipartFile file = request.getFile(uploadedFile);
                String mimeType = file.getContentType();
                String name = file.getOriginalFilename();
                Long size = file.getSize();
                byte[] bytes = file.getBytes();

                ForAdminsFileEntity newFile = new ForAdminsFileEntity(name, mimeType, bytes, newsId, size);

                fileRepository.saveAndFlush(newFile);
            }
        }
        catch (Exception e) {
            return new ResponseEntity<>("{}", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

    //DELETE
    @RequestMapping(
            value = "/forAdmins/deleteFile/{id}",
            method = RequestMethod.DELETE
    )
    public ResponseEntity deleteFile(@PathVariable Long id) {
        fileRepository.delete(id);
        return new ResponseEntity("{}", HttpStatus.OK);
    }
}