package com.tams.controller;

import com.tams.domain.Student;
import com.tams.dto.AjaxResult;
import com.tams.dto.PageParam;
import com.tams.dto.PageResult;
import com.tams.model.StudentModel;
import com.tams.service.StudentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author swiChen
 * @date 2022/1/5
 **/

@RestController
@RequestMapping("/student/")
public class StudentController {

    @Resource
    private StudentService studentService;

    @GetMapping("{id}/query")
    public AjaxResult<Student> query(@PathVariable("id") Long sId){
        return AjaxResult.succ(studentService.getById(sId));
    }

    @GetMapping("list/all")
    public AjaxResult<PageResult> getList(PageParam page){
        return AjaxResult.succ(studentService.getStudents(page));
    }

    @PostMapping("remove")
    public AjaxResult<Void> remove(@RequestBody Long []ids){
        studentService.remove(ids);
        return AjaxResult.succ(null);
    }

    @PostMapping("add")
    public AjaxResult<Void> remove(@RequestBody StudentModel studentModel){
        studentService.add(studentModel);
        return AjaxResult.succ(null);
    }

    @GetMapping("online")
    public AjaxResult online(){
        PageResult online = studentService.online();
        return AjaxResult.succ(online);
    }

    @PostMapping("offline")
    public AjaxResult offline(String []ids){
        studentService.offline(ids);
        return AjaxResult.succ(null);
    }
}
