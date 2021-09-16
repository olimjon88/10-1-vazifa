package uz.pdp.vazifa1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.vazifa1.entity.Hotel;
import uz.pdp.vazifa1.repository.HotelRepository;
import uz.pdp.vazifa1.repository.RoomRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    RoomRepository roomRepository;

    // Create Hotel
    @PostMapping
    public String addHotel(@RequestBody Hotel hotel){
        boolean exists = hotelRepository.existsByHotelName(hotel.getHotelName());
        if (exists){
            return "This Hotel is exist";
        }
        hotelRepository.save(hotel);
        return "New Hotel added successfully";
    }

    //    READ
    @GetMapping
    public List<Hotel> getHotels(){
        return hotelRepository.findAll();
    }

    //    READ by Id
    @GetMapping("/{id}")
    public Hotel getHotelById(@PathVariable Integer id){
        Optional<Hotel> optionalRegion = hotelRepository.findById(id);
        return optionalRegion.orElseGet(Hotel::new);
    }

    //    UPDATE
    @PutMapping("/{id}")
    public String editHotel(@PathVariable Integer id, @RequestBody Hotel hotel){
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (!optionalHotel.isPresent()){
            return "Hotel is not found";
        }
        boolean exists = hotelRepository.existsByHotelName(hotel.getHotelName());
        if (exists){
            return "This Hotel is exist";
        }
        hotel.setId(id);
        hotelRepository.save(hotel);
        return "Hotel has been edited";
    }



}
