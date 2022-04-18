package site.metacoding.everytimeback.domain.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "SELECT * FROM user WHERE username=:username AND password=:password", nativeQuery = true)
    User mLogin(@Param("username") String username, @Param("password") String password);

    @Query(value = "SELECT * FROM user WHERE username=:username", nativeQuery = true)
    Optional<User> findByUsername(@Param("username") String username);

    @Query(value = "SELECT * FROM user WHERE email=:email", nativeQuery = true)
    Optional<User> findByEmail(@Param("email") String email);
}
