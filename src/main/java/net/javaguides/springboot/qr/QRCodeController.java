package net.javaguides.springboot.qr;

import net.javaguides.springboot.model.ExperimentLink;
import net.javaguides.springboot.repository.ExperimentLinkRepository;
import net.javaguides.springboot.service.ExperimentLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Base64;

//QR code controller to generate qr codes on particular mappings
@Controller
public class QRCodeController {
    private ExperimentLinkService experimentLinkService;
    @Autowired
   private ExperimentLinkRepository experimentLinkRepository;
 private final int WIDTH = 350;
 private final int HEIGHT = 350;
 private final String QR_TEXT = "https://drive.google.com/drive/folders/1x1y9-HTFexKyZ6HjcNeAiqx-CpyBfz7q?usp=share_link";
 private final String QR_aos = "https://drive.google.com/drive/folders/1JtYzauJujHYqtp2RlwbAPspy83ePuCpu?usp=sharing";
 private final String QR_amp="https://drive.google.com/drive/folders/1BIO8CQdAujyvhPQv_LejrKr2RlZlA5do?usp=sharing";
 private final String QR_cjt="https://drive.google.com/drive/folders/1TCcUJawY32fdqjT6Uxo5Cdj2NFxAHCke?usp=sharing";
 private final String QR_dbms="https://drive.google.com/drive/folders/1TC8RK8vJ4-MBNSoIVK7rqnzCIMZDIEH7?usp=sharing";
 private final String QR_daa="https://drive.google.com/drive/folders/15i_ar2fXDRKS42nXMVJOblahz9N4F5Ke?usp=sharing";

 @Autowired private QRCodeService qrCodeService;
 @GetMapping("/aos")
 public ResponseEntity<byte[]> getQrCode1() {
        byte[] qrImage = qrCodeService.generate(QR_aos, WIDTH, HEIGHT);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(qrImage);
    }
    @GetMapping("/amp")
    public ResponseEntity<byte[]> getQrCodeAmp() {
        byte[] qrImage = qrCodeService.generate(QR_amp, WIDTH, HEIGHT);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(qrImage);
    }
    @GetMapping("/cjt")
    public ResponseEntity<byte[]> getQrCodeCjt() {
        byte[] qrImage = qrCodeService.generate(QR_cjt, WIDTH, HEIGHT);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(qrImage);
    }
    @GetMapping("/dbms")
    public ResponseEntity<byte[]> getQrCodeDbms() {
        byte[] qrImage = qrCodeService.generate(QR_dbms, WIDTH, HEIGHT);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(qrImage);
    }
    @GetMapping("/daa")
    public String getQrCodeTry(Model model) {
        byte[] qrImageBytes = qrCodeService.generate(QR_daa, WIDTH, HEIGHT);
        String qrImageBase64 = Base64.getEncoder().encodeToString(qrImageBytes);
        model.addAttribute("qrImage", qrImageBase64);
        // Return the name of the Thymeleaf template to render
        return "qrCode";
    }
    @GetMapping("/experimentqr")
    public String getExperimentLinks(Model model) {
        java.util.List<ExperimentLink> experimentLinks = experimentLinkRepository.findAll(); // retrieve links from database
        ArrayList<Object> qrImageBase64List = new ArrayList<Object>();
        for (ExperimentLink experimentLink : experimentLinks) {
            byte[] qrImageBytes = qrCodeService.generate(experimentLink.getLink(), WIDTH, HEIGHT); // generate QR code for each link
            String qrImageBase64 = Base64.getEncoder().encodeToString(qrImageBytes);
            qrImageBase64List.add(qrImageBase64);
        }
        model.addAttribute("experimentLinks", experimentLinks);
        model.addAttribute("qrImageBase64List", qrImageBase64List);
        return "experimentqr";
    }
    @GetMapping("/main")
    public String getFront() {
        return "index";
    }
}