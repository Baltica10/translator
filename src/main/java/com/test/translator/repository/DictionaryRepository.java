package com.test.translator.repository;

import com.test.translator.model.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DictionaryRepository extends JpaRepository<Dictionary, Long> {

    List<Dictionary> findAllByRuInIgnoreCase(Iterable<String> words);
}
