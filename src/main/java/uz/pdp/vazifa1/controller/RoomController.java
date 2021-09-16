package uz.pdp.vazifa1.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.vazifa1.dto.RoomDto;
import uz.pdp.vazifa1.entity.Hotel;
import uz.pdp.vazifa1.entity.Room;
import uz.pdp.vazifa1.repository.HotelRepository;
import uz.pdp.vazifa1.repository.RoomRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    HotelRepository hotelRepository;



    // Hotel id orqali shu mehmonxonaga tegishli room lar ro'yxatini pageable qilib olib keluvchi method yozing.
    @GetMapping("/byHotelId/{hotelId}")
    public Page<Room> getRoomsByHotelId(@PathVariable Integer hotelId, @RequestParam Integer page) {
        Pageable pageable = PageRequest.of(page, 10);
        return roomRepository.findAllByHotelId(hotelId, pageable);
    }



    // Create Room
    @PostMapping
    public String addRoom(@RequestBody RoomDto roomDto){
        Optional<Hotel> optionalHotel = hotelRepository.findById(roomDto.getHotelId());
        if (!optionalHotel.isPresent()){
            return "This Hotel is not found";
        }

        boolean exist = roomRepository.existsByNumberAndHotelId(roomDto.getNumber(), roomDto.getHotelId());
        if(exist){
            return "This room is already added to his Hotel";
        }

        Room room = new Room();
        room.setNumber(roomDto.getNumber());
        room.setFloor(roomDto.getFloor());
        room.setSize(roomDto.getSize());
        room.setHotel(optionalHotel.get());
        roomRepository.save(room);
        return "New room added successfully";
    }


    @GetMapping
    public List<Room> getRooms() {
        return roomRepository.findAll();
    }

    // Read by Room Id
    @GetMapping("/{id}")
    public Room getRegionById(@PathVariable Integer id) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (!optionalRoom.isPresent()) {
            return new Room();
        }
        return optionalRoom.get();
    }

    //    UPDATE
    @PutMapping("/{id}")
    public String editRoom(@PathVariable Integer id, @RequestBody RoomDto roomDto) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (!optionalRoom.isPresent()){
            return "This room is not found";
        }
        Optional<Hotel> optionalhotel = hotelRepository.findById(roomDto.getHotelId());
        if (!optionalhotel.isPresent()) {
            return "Hotel is not found";
        }
        boolean exists = roomRepository.existsByNumberAndHotelId(roomDto.getNumber(), roomDto.getHotelId());
        if (exists) {
            return "This room is already added to this hotel";
        }
        Room room = optionalRoom.get();
        room.setId(id);
        room.setNumber(roomDto.getNumber());
        room.setHotel(optionalhotel.get());
        room.setSize(roomDto.getSize());
        roomRepository.save(room);
        return "Room is added";
    }

    //    DELETE
    @DeleteMapping("/{id}")
    public String deleteRoomById(@PathVariable Integer id){
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (!optionalRoom.isPresent()){
            return "Room is not found";
        }
        roomRepository.deleteById(id);
        return "District is deleted";
    }
}
