package alba.CarHelper.repo;

import alba.CarHelper.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<Message, Long>{

}
