package uz.napa.my_career.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.napa.my_career.entity.FileStorage;

public interface FileStorageRepository extends JpaRepository<FileStorage,Long> {
}
