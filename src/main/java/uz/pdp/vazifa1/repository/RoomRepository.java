package uz.pdp.vazifa1.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.vazifa1.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    boolean existsByNumberAndHotelId(String number, Integer region_id);

    Page<Room> findAllByHotelId(Integer hotel_id, Pageable pageable);

//    List<Room> findAllByHotelId(Integer hotel_id);
}
