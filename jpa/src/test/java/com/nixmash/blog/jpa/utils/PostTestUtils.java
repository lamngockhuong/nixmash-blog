package com.nixmash.blog.jpa.utils;

import com.nixmash.blog.jpa.dto.CategoryDTO;
import com.nixmash.blog.jpa.dto.PostDTO;
import com.nixmash.blog.jpa.dto.TagDTO;
import com.nixmash.blog.jpa.enums.PostDisplayType;
import com.nixmash.blog.jpa.enums.PostType;
import com.nixmash.blog.jpa.enums.TwitterCardType;
import com.nixmash.blog.jpa.model.Category;
import com.nixmash.blog.jpa.model.Post;
import com.nixmash.blog.jpa.model.PostMeta;
import com.nixmash.blog.jpa.model.Tag;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by daveburke on 6/1/16.
 */
public class PostTestUtils {

    public static final Long USER_ID = 1L;
    public static final Long CATEGORY_ID = 1L;
    public static final String POST_TITLE = "Post title";
    public static final String POST_NAME = "post-title";
    public static final String POST_LINK = "http://test.link";
    public static final String POST_CONTENT = "Post content.";
    public static final PostType POST_TYPE = PostType.POST;
    public static final PostDisplayType DISPLAY_TYPE = PostDisplayType.LINK;
    public static final TwitterCardType TWITTER_CARD_SUMMARY = TwitterCardType.SUMMARY;
    public static final String JAVA_CATEGORY_VALUE = "Java";


    public static PostDTO createPostDTO(int i) {
        return PostDTO.getBuilder(USER_ID,
                fieldit(POST_TITLE, i), fieldit(POST_NAME, i), POST_LINK, POST_CONTENT, POST_TYPE, DISPLAY_TYPE, CATEGORY_ID, TWITTER_CARD_SUMMARY)
                .tags(getTestTagDTOs(2))
                .build();
    }

    private static String fieldit(String field, int i) {
        return String.format("%s-%d", field, i);
    }


    public static PostDTO createPostDTO(String appender) {
        return PostDTO.getBuilder(USER_ID,
                fieldit(POST_TITLE, appender), fieldit(POST_NAME, appender), POST_LINK, POST_CONTENT, POST_TYPE, DISPLAY_TYPE, CATEGORY_ID, TWITTER_CARD_SUMMARY)
                .tags(getTestTagDTOs(2))
                .build();
    }

    public static List<Post> createPostList(int n) {
        List<Post> posts = new ArrayList<Post>(n);
        for (int i = 1; i < n + 1; i++) {
            PostDTO postDTO = createPostDTO(n);
            posts.add(PostUtils.postDtoToPost(postDTO));
        }
        return posts;
    }

    public static Set<TagDTO> getTestTagDTOs(int i) {
        Set<TagDTO> tagDTOs = new LinkedHashSet<>();
        for (int j = 1000; j < i; j++) {
            tagDTOs.add(new TagDTO(i, "tag-" + i));
        }
        return tagDTOs;
    }

    public static Set<Tag> getTestTags(int i) {
        Set<Tag> tags = new LinkedHashSet<>();
        for (int j = 1; j < i; j++) {
            tags.add(new Tag((long) i, "tag-" + i));
        }
        return tags;
    }

    public static CategoryDTO getTestCategoryDTO() {
        return new CategoryDTO(2L, "Java", 0, true, true);
    }

    public static Category getTestCategory() {
        return new Category(2L, JAVA_CATEGORY_VALUE, true, true);
    }

    public static Category getUncategorizedCategory() {
        return new Category(1L, "Uncategorized", true, true);
    }

    private static String fieldit(String field, String appender) {
        return String.format("%s-%s", field, appender);
    }

    public static PostMeta createPostMeta() {
        return PostMeta.getBuilder(TwitterCardType.SUMMARY,
                "Test Title", "@testsite", "@testblogger")
                .twitterDescription("This is a test card")
                .postId(1L)
                .twitterImage("http://testsite.com/x/test/myimage.png")
                .twitterUrl("http://testsite.com/post/test-post")
                .build();
    }
}
