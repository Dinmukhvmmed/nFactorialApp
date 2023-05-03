package application.repository;

import application.model.Bigram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BigramRepository extends JpaRepository<Bigram, Integer> {
    @Query(value = "SELECT * FROM bigram WHERE letters LIKE %:letter% ORDER BY frequency DESC LIMIT 1", nativeQuery = true)
    Bigram findMaxFrequencyByLetter(@Param("letter") String letter);

    @Query(value = "SELECT * FROM bigram WHERE letters LIKE %:letter% ORDER BY frequency DESC LIMIT 5", nativeQuery = true)
    List<Bigram> findFiveMaxFrequencyByLetter(@Param("letter") String letter);

    @Query(value = "SELECT * FROM bigram WHERE letters LIKE %:letter% ORDER BY frequency ASC LIMIT 5", nativeQuery = true)
    List<Bigram> findFiveMinFrequencyByLetter(@Param("letter") String letter);
}
