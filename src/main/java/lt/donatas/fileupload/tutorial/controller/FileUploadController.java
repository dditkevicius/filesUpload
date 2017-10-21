package lt.donatas.fileupload.tutorial.controller;

import java.io.IOException;

import lt.donatas.fileupload.tutorial.helper.FileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {

    @GetMapping("/")
    public String fileUploadForm(Model model) {
        return "fileUploadForm";
    }

    @PostMapping("/multipleFileUpload")
    public String multipleFileUpload(@RequestParam("file") MultipartFile[] files,
                                     Model model) throws IOException {

        if (files[0].getOriginalFilename().isEmpty()) {
            model.addAttribute("msg", "Please select at least one file...");
            return "fileUploadForm";
        }

        FileUpload fileUpload = new FileUpload();
        fileUpload.splitWordsFromFilesAndWriteToNewFiles(files);

        model.addAttribute("agPart", fileUpload.getAgPart());
        model.addAttribute("hnPart", fileUpload.getHnPart());
        model.addAttribute("ouPart", fileUpload.getOuPart());
        model.addAttribute("vzPart", fileUpload.getVzPart());
        return "resultForm";
    }
}
