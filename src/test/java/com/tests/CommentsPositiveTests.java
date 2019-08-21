package com.tests;

import domain.CommentsResponse;
import domain.GetCommentsBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class CommentsPositiveTests {
    @Test(groups = "P1", testName = "test_12345_GET_ALL_comments",
            description = "Verify possibility to get all comments and check comment's content")
    public void test_12345_GET_ALL_comments() {
        List<CommentsResponse> comments = new GetCommentsBuilder()
                .run()
                .verifyStatus(200)
                .parsed();

        int actualAmountOfComments = (int) comments.stream().filter(x -> x.postId == 40).count();
        Assert.assertEquals(actualAmountOfComments, 5, "The postId 40 does not contain expected amount of comments");

        String expectedCommentName = "pariatur aspernatur nam atque quis";
        String expectedCommentEmail = "Cooper_Boehm@damian.biz";
        String expectedCommentBody = "veniam eos ab voluptatem in fugiat ipsam quis\nofficiis non qui\nquia ut id voluptates et a molestiae commodi quam\ndolorem enim soluta impedit autem nulla";

        Assert.assertTrue(comments.stream()
                .filter(x -> x.postId == 40)
                .map(y -> y.name)
                .anyMatch(e -> e.equals(expectedCommentName)), "The postId 40 does not contain expected name: + " + expectedCommentName);

        Assert.assertTrue(comments.stream()
                .filter(x -> x.postId == 40)
                .map(y -> y.email)
                .anyMatch(e -> e.equals(expectedCommentEmail)), "The postId 40 does not contain expected email: + " + expectedCommentEmail);

        Assert.assertTrue(comments.stream()
                .filter(x -> x.postId == 40)
                .map(y -> y.body)
                .anyMatch(e -> e.equals(expectedCommentBody)), "The postId 40 does not contain expected body: " + expectedCommentBody);
    }
}
