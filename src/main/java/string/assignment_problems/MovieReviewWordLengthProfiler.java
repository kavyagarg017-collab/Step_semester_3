public class MovieReviewWordLengthProfiler {
    public static void main(String[] args) {
        String review = "This movie was absolutely amazing";

        String[] words = review.split(" ");

        for (String word : words) {
            System.out.println(word + " : " + word.length());
        }
    }
}