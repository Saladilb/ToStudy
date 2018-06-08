package ru.itpark.rest;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.itpark.domain.Wish;
import ru.itpark.service.WishService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/wishes")
public class WishController {
    @Autowired
    private final WishService wishService;

    public WishController(WishService wishService) {
        this.wishService = wishService;
    }

    @GetMapping
    @ApiOperation("getAll")
    public List<Wish> findAll() {
        return wishService.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation("getById")
    public Wish findById(@PathVariable int id) {
        return wishService.findById(id);
    }

    @PostMapping("/add")
    @ApiOperation("add")
    public void addWishList(@RequestBody Wish wish) {
        wishService.addWish(wish);
    }

    @GetMapping("/{id}/delete")
    @ApiOperation("delete")
    public void deleteWishList(@PathVariable int id) {
        wishService.delete(id);
    }

    @PostMapping("/{id}")
    @ApiOperation("dislike")
    public void setLike(@PathVariable int id) {
        wishService.setLike(id);
    }
    @DeleteMapping("/{id}")
    @ApiOperation("like")
    public void setDisLike(@PathVariable int id){
        wishService.setDislike(id);
    }
}
