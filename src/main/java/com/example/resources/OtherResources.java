package com.example.resources;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@RestController
public class OtherResources {


    public static byte[] createDocFile(String fileName) {

        try {
            File file = new File(fileName);
            FileOutputStream fos = new FileOutputStream(file.getAbsolutePath());

            XWPFDocument doc = new XWPFDocument();
            XWPFParagraph tempParagraph = doc.createParagraph();
            XWPFRun tempRun = tempParagraph.createRun();

            tempRun.setText("This is a Paragraph");
            tempRun.setFontSize(30);
            doc.write(fos);
            fos.close();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            doc.write(baos);
            return baos.toByteArray();


        } catch (Exception e) {

        }

        return null;
    }


    @GetMapping(value = "/export.docx/{name}", produces = "application/msword")
    public byte[] wordExport(@PathVariable String name) throws IOException {

        Logger logger = LoggerFactory.getLogger(OtherResources.class);
        logger.info(name);

        byte [] hej = {1,2,3,4,5,5};

        byte[] docFile = createDocFile(name);


        return docFile;
    }

}
