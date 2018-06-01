package com.example.controller;

import java.util.List;

import org.springframework.social.facebook.api.Album;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Permission;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.Reference;
import org.springframework.social.facebook.api.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ApiController {

    private final Facebook facebook;

    @GetMapping("/profile")
    public User profile() {
        //return facebook.userOperations().getUserProfile();
        String [] fields = { "id", "email",  "first_name", "last_name", "birthday", "locale", "work" };
        return facebook.fetchObject("me", User.class, fields);
    }

    @GetMapping("/permissions")
    public List<Permission> permissions() {
        return facebook.userOperations().getUserPermissions();
    }

    @GetMapping("/albums")
    public PagedList<Album> albums() {
        return facebook.mediaOperations().getAlbums();
    }

    @GetMapping("/friends")
    public List<Reference> friends() {
        return facebook.friendOperations().getFriends();
    }

    @GetMapping("/friendProfiles")
    public PagedList<User> friendProfiles() {
        return facebook.friendOperations().getFriendProfiles();
    }

    @GetMapping("/feeds")
    public PagedList<Post> feeds() {
        return facebook.feedOperations().getFeed();
    }
}
