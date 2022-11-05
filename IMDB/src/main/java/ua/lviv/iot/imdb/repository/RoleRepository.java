package ua.lviv.iot.imdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.lviv.iot.imdb.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}

