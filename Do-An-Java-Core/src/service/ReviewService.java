package service;

import entities.Review;
import utils.file.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ReviewService {
    private final String REVIEW_DATA_FILE = "src/data/orderReview.dat";
    private List<Review> reviews;

    public ReviewService() {
        reviews = new ArrayList<>();
    }

    public void addReview(Review review) {
        getReviewFormFile();
        reviews.add(review);
        FileUtil.writeDataFile(reviews, REVIEW_DATA_FILE);
    }

    public List<Review> getReviewByProductId(int productID) {
        getReviewFormFile();
        List<Review> reviewsProduct = new ArrayList<>();
        for (Review review : reviews) {
            if (review.getProductID() == productID) {
                reviewsProduct.add(review);
            }
        }
        return reviewsProduct;
    }

    public double getAvgRatingByProductID(int productID) {
        getReviewFormFile();
        List<Review> reviews = getReviewByProductId(productID);
        if (reviews.isEmpty()) {
            return 0;
        }

        int totalRating = 0;
        for (Review review: reviews) {
            totalRating += review.getRating();
        }

        double avgRating = (double) totalRating/reviews.size();
        return (double) Math.round(avgRating * 10) / 10;
    }

    public void getReviewFormFile() {
        if (new File(REVIEW_DATA_FILE).exists()) {
            reviews = (List<Review>) FileUtil.readDataFile(REVIEW_DATA_FILE);
        }
    }
}
