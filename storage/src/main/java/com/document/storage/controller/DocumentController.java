package com.document.storage.controller;

import com.document.storage.util.DocumentUtil;
import com.document.storage.util.FileUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@RequestMapping("/storage/documents")
public class DocumentController {

    // 获取项目主目录绝度路径
    private static String path = System.getProperty("user.dir");

//    static {
//        System.out.println(path);
//    }

    /**
     * 创建资源
     * @param response HTTP响应
     * @param text 资源内容
     * @return 资源ID
     */
    @PostMapping()
    public String create(HttpServletResponse response, @RequestBody String text) {
        response.setStatus(201);
        String id = DocumentUtil.getUuid();
        String filePath = path + "/" + id;
        File file = new File(filePath);

        // 避免文件ID重复导致已存在的被文件覆盖
        while (file.exists()) {
            id = DocumentUtil.getUuid();
            filePath = path + "/" + id;
            file = new File(filePath);
        }
        FileUtil.writeToFile(filePath, text, false);
        return id;
    }

    /**
     * 根据资源ID获取资源
     * @param id 资源的ID
     * @return 资源的内容
     */
    @GetMapping("/{docId}")
    public String query(@PathVariable("docId") String id) {
        return FileUtil.readFromFile(path + "/" + id);
    }

    /**
     * 根据资源ID修改资源
     * @param response HTTP响应
     * @param id 资源的ID
     * @param text 修改后的资源内容
     */
    @PutMapping("/{docId}")
    public void update(HttpServletResponse response, @PathVariable("docId") String id, @RequestBody String text) {
        response.setStatus(204);
        System.out.println(text);
        FileUtil.writeToFile(path + "/" + id, text, true);
    }

    /**
     * 根据资源ID删除资源
     * @param response HTTP响应
     * @param id 资源的ID
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
