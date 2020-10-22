package com.movements.springboot.backend.apirest.models.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileServiceImpl implements IUploadFileService {

	private final Logger log = LoggerFactory.getLogger(UploadFileServiceImpl.class);

	private final static String UPLOAD_DIRECTORY = "src/main/resources/static/uploads";

	
	@Override
	public Resource upload(String imageName) throws MalformedURLException {

		Path filePath = getPath(imageName);

		log.info(filePath.toString());

		Resource resource = null;

		resource = new UrlResource(filePath.toUri());

		if (!resource.exists() && !resource.isReadable()) {
			filePath = Paths.get("src/main/resources/static/images").resolve("no-image.png").toAbsolutePath();

			resource = new UrlResource(filePath.toUri());

			log.error("Error no s'ha pogut carregar la imatge: " + imageName);
		}

		return resource;
	}

	
	
	@Override
	public String copy(MultipartFile file) throws IOException {
		

		String fileName = removeDiacritics(UUID.randomUUID().toString() + "_" + file.getOriginalFilename().replace(" ", ""));
		
		Path filePath = getPath(fileName);

		log.info(filePath.toString());

		Files.copy(file.getInputStream(), filePath);

		return fileName;
	}

	
	
	@Override
	public boolean delete(String imageName) {
		if (imageName != null && imageName.length()>0) {
			Path oldFilePath = Paths.get("src/main/resources/static/uploads").resolve(imageName).toAbsolutePath();
			File fileOldFile = oldFilePath.toFile();
			if (fileOldFile.exists() && fileOldFile.canRead()) {
				fileOldFile.delete();
				return true;
			}
		}
		return false;
	}

	
	
	@Override
	public Path getPath(String imageName) {
		return Paths.get(UPLOAD_DIRECTORY).resolve(imageName).toAbsolutePath();
	}

	
	public static String removeDiacritics(String s) {
	    s = Normalizer.normalize(s, Normalizer.Form.NFD);
	    s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
	    return s;
	}
}
