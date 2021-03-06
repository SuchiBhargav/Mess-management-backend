package com.example.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.example.model.Countplates;

import com.example.service.UserPDFExporter;
import com.example.service.UserServicePdf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import com.lowagie.text.DocumentException;

@CrossOrigin("*")
@Controller
public class MessManagerpdfGenerate {


    @Autowired
    private UserServicePdf userServicePdf;

    @GetMapping("/users/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Countplates> listHistory = userServicePdf.listAll();

        UserPDFExporter exporter = new UserPDFExporter(listHistory);
        exporter.export(response);
    }
}



