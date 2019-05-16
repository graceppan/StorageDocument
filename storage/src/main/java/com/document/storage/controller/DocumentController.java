package com.document.storage.controller;

import com.document.storage.util.DocumentUtil;
import com.document.storage.util.FileUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@RequestMapping("/storage/documents")
public class DocumentController {

    // get absolute path of home directory
    private static String path = System.getProperty("user.dir");

//    static {
//        System.out.println(path);
//    }

    /**
     * create document
     * @param response: HTTP responce
     * @param text: document context
     * @return document id
     */
    @PostMapping()
    public String create(HttpServletResponse response, @RequestBody String text) {
        response.setStatus(201);
        String id = DocumentUtil.getUuid();
        String filePath = path + "/" + id;
        File file = new File(filePath);

        while (file.exists()) {
            id = DocumentUtil.getUuid();
            filePath = path + "/" + id;
            file = new File(filePath);
        }
        FileUtil.writeToFile(filePath, text, false);
        return id;
    }

    /**
     * get document with docId
     * @param id: docId
     * @return document context
     */
    @GetMapping("/{docId}")
    public String query(@PathVariable("docId") String id) {
        return FileUtil.readFromFile(path + "/" + id);
    }

    /**
     * update document context with docId
     * @param response: HTTP response
     * @param id: docId
     * @param text: updated document context
     */
    @PutMapping("/{docId}")
    public void update(HttpServletResponse response, @PathVariable("docId") String id, @RequestBody String text) {
        response.setStatus(204);
        System.out.println(text);
        FileUtil.writeToFile(path + "/" + id, text, true);
    }

    /**
     * delete document with docId
     * @param response: HTTP response
     * @param id: docId
     */
    @DeleteMapping("/{docId}")
    @ResponseBody
    public void delete(HttpServletResponse response, @PathVariable("docId") String id) {
        response.setStatus(204);
        File file = new File(path + "/" + id);
        if (file.exists() && file.isFile()) {
            file.delete();
        }
    }



}
