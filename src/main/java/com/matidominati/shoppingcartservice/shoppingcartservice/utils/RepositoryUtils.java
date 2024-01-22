package com.matidominati.shoppingcartservice.shoppingcartservice.utils;

import com.matidominati.shoppingcartservice.shoppingcartservice.exception.DataNotFoundException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RepositoryUtils {

    public static <T> T findByIdOrThrow(Long id, JpaRepository<T, Long> jpaRepository, Class<T> entityName) {
        Optional<T> entity = jpaRepository.findById(id);
        if (entity.isEmpty()) {
            throw new DataNotFoundException(entityName.getSimpleName() + " with the provided ID does not exist.");
        }
        return entity.get();
    }
}

