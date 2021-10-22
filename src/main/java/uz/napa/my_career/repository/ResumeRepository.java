package uz.napa.my_career.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.napa.my_career.entity.Resume;


public interface ResumeRepository extends JpaRepository<Resume,Long> {



}
