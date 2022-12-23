package com.example.Schedule.Controller;

import com.example.Schedule.Model.Schedule;
import com.example.Schedule.Service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLException;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    @GetMapping("/showAll")
    //localhost:8001/schedule/showAll
    public Iterable<Schedule> showAllData() throws SQLException {
        return service.showAll();
    }


    @Autowired ScheduleService service;
    @GetMapping("/add")
    //    http://localhost:8001/schedule/add?patientId=1&postId=1&date=2022-12-23&price=9000
    public void insertData(@RequestParam int patientId,@RequestParam int postId,@RequestParam String date,@RequestParam double price) throws SQLException, ClassNotFoundException {
        service.insertData(new Schedule(-1,patientId,postId,date,price));
    }
    @GetMapping("/delete")
    public ResponseEntity<Object> deleteData(@RequestParam int id) throws SQLException, ClassNotFoundException {
        var result = service.selectById(id);
        if(result == null) {
            return ResponseEntity.badRequest().build();
        }
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/change")
    public ResponseEntity updateData(@RequestParam int id,@RequestParam int patientId,@RequestParam int postId,@RequestParam String date,@RequestParam double price) throws SQLException, ClassNotFoundException{
        var result = service.selectById(id);
        if(result == null) {
            return ResponseEntity.badRequest().build();
        }
        service.updateById(new Schedule(id,patientId,postId,date,price));
        return ResponseEntity.ok().build();
    }
}
