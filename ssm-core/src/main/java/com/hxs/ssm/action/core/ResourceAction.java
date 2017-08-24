package com.hxs.ssm.action.core;

import com.hxs.ssm.UserInfo;
import com.hxs.ssm.utils.digest.Base64;
import com.hxs.ssm.utils.image.ImageSizeUtils;
import com.hxs.ssm.web.HttpContext;
import com.hxs.ssm.web.annotation.Login;
import lombok.extern.log4j.Log4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/core/res")
@Log4j
public class ResourceAction {

    @Value("#{settings['storeDir']}")
    private String storeDir;

    private String[] suffixArray = new String[]{"jpg", "png", "gif", "jpeg", "bmp"};

    //获取Url
    private String getUrl(String dir, File file) {
        String name = file.getName();
        if (name.endsWith(".jpg") || name.endsWith(".png") || name.endsWith(".gif") || name.endsWith(".bmp")) {
            name = name.substring(0, name.length() - 4);
        }
        if (!StringUtils.isEmpty(dir)) {
            name = dir + "/" + name;
        }
        String url = HttpContext.getUrlRoot() + "/core/res/get/" + name;
        log.info("getUrl=" + url);
        return url;
    }

    //上传文件
    private UploadResult upload(String dir, MultipartFile file) throws IOException {
        String separator = File.separator;
        File directory = new File(storeDir + separator + dir);
        directory.mkdirs();
        String original = file.getOriginalFilename();

        String suffix = ".jpg";
        int index;
        if ((index = original.lastIndexOf(".")) != -1) {
            suffix = original.substring(index).toLowerCase();
        }
        long size = file.getSize();
        //生成文件名
        String name = DigestUtils.md5Hex(UUID.randomUUID().toString())
                .substring(8, 24) + suffix;
        String path = directory.getAbsolutePath() + separator + name;
        log.info("upload path= " + path);
        File locFile = new File(path);
        InputStream input = file.getInputStream();
        OutputStream output = new FileOutputStream(locFile);
        IOUtils.copy(input, output);
        output.close();
        input.close();

        String url = getUrl(dir, locFile);
        UploadResult result = new UploadResult();
        result.setName(name);
        result.setUrl(url);
        result.setType(suffix);
        result.setSize(size);
        result.setState("SUCCESS");
        result.setOriginal(original);
        log.info("UploadResult=" + result.toString());
        return result;
    }

    @ResponseBody
    @RequestMapping("upload")
    public Object upload(@Login UserInfo userInfo, @RequestParam("files") MultipartFile[] files,
                         HttpServletRequest request) throws IOException {
        List<UploadResult> results = new ArrayList<UploadResult>();
        for (MultipartFile file : files) {
            results.add(upload("", file));
        }
        return results;
    }

    @ResponseBody
    @RequestMapping("upload2")
    public Object upload(@RequestParam("files") MultipartFile[] files,
                         HttpServletRequest request) throws IOException {
        List<UploadResult> results = new ArrayList<UploadResult>();
        for (MultipartFile file : files) {
            results.add(upload("members", file));
        }
        return results;
    }

    @ResponseBody
    @RequestMapping("uploadBase64")
    public Object uploadBase64(String data) throws IOException {
        List<UploadResult> results = new ArrayList<UploadResult>();
        for (String item : data.split(";")) {
            String[] array = item.split(",", 2);
            String format = array[0];
            String base64 = array[1];
            if (format.contains("png")) {
                format = "png";
            } else if (format.contains("gif")) {
                format = "gif";
            } else {
                format = "jpg";
            }
            String name = "unknown" + format;
            byte[] bytes = Base64.decode(base64);
            MockMultipartFile file = new MockMultipartFile(name, bytes);
            results.add(upload("members", file));
        }
        return results;
    }

    @ResponseBody
    @RequestMapping("ueditor/controller")
    public Object controller(
            @Login UserInfo userInfo,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        String separator = File.separator;
        String action = request.getParameter("action");
        if ("config".equals(action)) {
            String path = request.getSession().getServletContext()
                    .getRealPath("/resources/components/ueditor/1.4.3/config.json");
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html; charset=utf-8");
            byte[] data = FileUtils.readFileToByteArray(new File(path));
            IOUtils.write(data, response.getOutputStream());
            return null;
        } else if ("uploadimage".equals(action)) {
            MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
            MultipartFile file = mRequest.getFile("upfile");
            UploadResult result = upload("", file);
            return result;
        } else if ("listimage".equals(action)) {
            FileList result = new FileList();
            int start = Integer.parseInt(request.getParameter("start"));
            int size = Integer.parseInt(request.getParameter("size"));
            File directory = new File(storeDir);
            if (directory.exists()) {
                int end = start + size;
                File[] files = directory.listFiles();
                result.total = files.length;
                result.start = start;
                result.state = "SUCCESS";
                for (int i = start; i < end && i < files.length; i++) {
                    File file = files[i];
                    String url = getUrl("", file);
                    long mtime = file.lastModified() / 1000;
                    result.list.add(new FileList.File(url, mtime));
                }
            } else {
                result.state = "FAIL";
            }
            return result;
        }
        return null;
    }

    @RequestMapping("get/**")
    public void dowload(
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException {
        String thumbnail = null;
        String requestURI = request.getRequestURI();
        String path = requestURI.substring(requestURI.lastIndexOf("/"), requestURI.length());
        int index = path.indexOf("/thumbnail/");
        if (index != -1) {
            thumbnail = path.substring(index + 11);
            path = path.substring(0, index);
            index = thumbnail.indexOf(".");
            if (index != -1) {
                thumbnail = thumbnail.substring(0, index);
            }
        }
        String separator = File.separator;
        String format = "";
        String lcPath = "";
        index = path.indexOf(".");
        if (index == -1) {
            for (String s : suffixArray) {
                String tmp = path;
                tmp += "." + s;
                File directory = new File(storeDir);
                String tempPath = directory.getAbsolutePath() + tmp.replace("/", separator);
                File downloadFile = new File(tempPath);
                if (downloadFile.exists()) {
                    format = s;
                    lcPath = directory.getAbsolutePath() + tmp.replace("/", separator);
                    break;
                }
            }
        } else {
            format = path.substring(index + 1);
        }
        try {
            InputStream input = new FileInputStream(lcPath);
            OutputStream output = response.getOutputStream();
            // 对图片进行裁剪
            if (thumbnail != null) {
                String[] array = thumbnail.split("x");
                int w = Integer.parseInt(array[0]);
                int h = Integer.parseInt(array[1]);
                ImageSizeUtils.cut(format, input, output, w, h);
            } else {
                IOUtils.copy(input, output);
            }
            input.close();
            output.close();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    public static class UploadResult {
        private String original;
        private String name;
        private String url;
        private long size;
        private String type;
        private String state;// SUCCESS

        public String getOriginal() {
            return original;
        }

        public void setOriginal(String original) {
            this.original = original;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public long getSize() {
            return size;
        }

        public void setSize(long size) {
            this.size = size;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        @Override
        public String toString() {
            return "UploadResult{" +
                    "original='" + original + '\'' +
                    ", name='" + name + '\'' +
                    ", url='" + url + '\'' +
                    ", size=" + size +
                    ", type='" + type + '\'' +
                    ", state='" + state + '\'' +
                    '}';
        }
    }

    public static class FileList {

        public static class File {
            private String url;
            private long mtime;

            public File(String url, long mtime) {
                this.url = url;
                this.mtime = mtime;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public long getMtime() {
                return mtime;
            }

            public void setMtime(long mtime) {
                this.mtime = mtime;
            }
        }

        public FileList() {
            this.list = new ArrayList<File>();
        }

        private String state;// SUCCESS
        private List<File> list;
        private int start;
        private int total;

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public List<File> getList() {
            return list;
        }

        public void setList(List<File> list) {
            this.list = list;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }
    }
}
