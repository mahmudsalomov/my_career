package uz.napa.my_career.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.napa.my_career.entity.FileStorage;
import uz.napa.my_career.entity.enums.FileStorageStatus;
import uz.napa.my_career.repository.FileStorageRepository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileStorageService {
    @Autowired
    private FileStorageRepository fileStorageRepository;

    public List<FileStorage> save(MultipartFile[] files){
        List<FileStorage> fileStorages=new ArrayList<>();

        try {
            if (files!=null&&files.length>0){


                for (MultipartFile multipartFile : files){


                    /** File ma'lumotlari saqlash **/
                    FileStorage fileStorage= FileStorage
                            .builder()
                            .name(multipartFile.getOriginalFilename())
                            .fileSize(multipartFile.getSize())
                            .contentType(multipartFile.getContentType())
                            .status(FileStorageStatus.ACTIVE)
                            .extension(extension(multipartFile.getOriginalFilename()))
                            .build();
                    fileStorageRepository.save(fileStorage);


                    /** Fileni o'zini saqlash **/

                    File uploadFolder=new File("images");

                    System.out.println(uploadFolder);
                    System.out.println(uploadFolder.getAbsolutePath());
                    System.out.println(uploadFolder.getAbsoluteFile());


                    if (!uploadFolder.exists()&&uploadFolder.mkdir()){
                        System.out.println("Folder yaraldi");
                    }

                    fileStorage.setUploadPath("images/"+fileStorage.getId()+"."+fileStorage.getExtension());



                    File abs=uploadFolder.getAbsoluteFile();
                    File file=new File(abs,fileStorage.getId()+"."+fileStorage.getExtension());

                    try {
                        multipartFile.transferTo(file);
                        fileStorages.add(fileStorageRepository.save(fileStorage));

                    }catch (IOException e){
                        e.printStackTrace();
                        return null;
                    }


                }

                return fileStorages;

            }
            return null;

        }catch (Exception e){
            //Exception yasash kerak
            e.printStackTrace();
            return null;
        }

    }

    public String extension(String fileName){
        String extension = "";
        if (fileName!=null&&!fileName.isEmpty()){
            int nuqta=fileName.lastIndexOf(".");
            if (nuqta>0&&(nuqta<=fileName.length()-2)){
                extension=fileName.substring(nuqta+1);
            }

        }
        return extension;
    }

}
