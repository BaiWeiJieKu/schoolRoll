package com.purple.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.purple.service.TotalService;
import com.purple.util.JsonData;

@RestController
public class TotalController {
	
	@Resource
	private TotalService totalService;
	
	@GetMapping("/total")
	public JsonData total(@RequestParam("uid")String uid, @RequestParam("sid")String sid) {
		List<Map> total = totalService.total(uid, sid);
		return JsonData.success(total);
	}
	
	@GetMapping("/tUser")
	public JsonData tUser() {
		return JsonData.success(totalService.tUser());
	}
	
}
