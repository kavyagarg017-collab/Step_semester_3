import java.util.*;

public class OnlineExaminationSystem {

    static class Student {
        private String name;

        Student(String name) {
            this.name = name;
        }
    }

    interface Question {
        int evaluate(String answer);
        int getMarks();
    }

    static class MultipleChoiceQuestion implements Question {
        private String correctAnswer;
        private int marks;

        MultipleChoiceQuestion(String correctAnswer, int marks) {
            this.correctAnswer = correctAnswer;
            this.marks = marks;
        }

        public int evaluate(String answer) {
            return correctAnswer.equalsIgnoreCase(answer) ? marks : 0;
        }

        public int getMarks() {
            return marks;
        }
    }

    static class TrueFalseQuestion implements Question {
        private boolean correctAnswer;
        private int marks;

        TrueFalseQuestion(boolean correctAnswer, int marks) {
            this.correctAnswer = correctAnswer;
            this.marks = marks;
        }

        public int evaluate(String answer) {
            boolean given = Boolean.parseBoolean(answer);
            return given == correctAnswer ? marks : 0;
        }

        public int getMarks() {
            return marks;
        }
    }

    static class ShortAnswerQuestion implements Question {
        private String correctAnswer;
        private int marks;

        ShortAnswerQuestion(String correctAnswer, int marks) {
            this.correctAnswer = correctAnswer;
            this.marks = marks;
        }

        public int evaluate(String answer) {
            return correctAnswer.equalsIgnoreCase(answer.trim()) ? marks : 0;
        }

        public int getMarks() {
            return marks;
        }
    }

    static class Examination {
        private String name;
        private List<Question> questions = new ArrayList<>();

        Examination(String name) {
            this.name = name;
        }

        void addQuestion(Question question) {
            questions.add(question);
        }

        int getTotalMarks() {
            int total = 0;

            for (Question q : questions) {
                total += q.getMarks();
            }

            return total;
        }
    }

    static class Attempt {
        private Student student;
        private Examination exam;
        private List<String> answers = new ArrayList<>();
        private boolean submitted = false;

        Attempt(Student student, Examination exam) {
            this.student = student;
            this.exam = exam;
        }

        void answer(String answer) {
            if (submitted) {
                System.out.println("Cannot change answers after submission.");
                return;
            }

            answers.add(answer);
        }

        void submit() {
            if (submitted) {
                System.out.println("Exam already submitted.");
                return;
            }

            submitted = true;

            int score = 0;

            for (int i = 0; i < answers.size(); i++) {
                score += exam.questions.get(i).evaluate(answers.get(i));
            }

            System.out.println("Exam submitted by " + student.name);
            System.out.println("Total score: " + score + "/"
                    + exam.getTotalMarks());
        }
    }

    public static void main(String[] args) {

        Student student = new Student("Student 1");

        Examination exam = new Examination("Exam A");

        exam.addQuestion(
                new MultipleChoiceQuestion("C", 5));

        exam.addQuestion(
                new TrueFalseQuestion(true, 5));

        Attempt attempt = new Attempt(student, exam);

        System.out.println("Exam A started by Student 1.");

        attempt.answer("C");
        System.out.println("Answer recorded for Question 1.");

        attempt.answer("true");
        System.out.println("Answer recorded for Question 2.");

        attempt.submit();

        attempt.answer("A");
    }
}