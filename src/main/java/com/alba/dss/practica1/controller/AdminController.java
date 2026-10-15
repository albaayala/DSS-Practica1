package com.alba.dss.practica1.controller;


import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alba.dss.practica1.service.DatabaseExportService;

@Controller
public class AdminController {
	final DatabaseExportService database;
	
	public AdminController(DatabaseExportService database) {
		this.database = database;
	}
	
	@RequestMapping (value = "/admin/export", method = RequestMethod.GET)
	public ResponseEntity<byte[]> exportBD()
	{
		byte[] file = database.exportDatabaseToSql();
		
		HttpHeaders headers = new HttpHeaders();
		
		headers.setContentType(MediaType.parseMediaType("applicaction/sql; charset=UTF-8"));
		
		// Cabeceras HTTP para indicar que es un fichero descargable
		headers.setContentDisposition(
				ContentDisposition.attachment()
				.filename("products.sql")
				.build()
				);
		
		headers.setContentLength(file.length);
		
		return new ResponseEntity<byte[]>(file, headers, HttpStatus.OK);
		
	}
}
