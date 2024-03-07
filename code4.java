import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class QuizQuestion {
    String question;
    String[] options;
    int correctOptionIndex;

    public QuizQuestion(String question, String[] options, int correctOptionIndex) {
        this.question = question;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }
}

public class QuizApplication {
    static int score = 0;

    public static void main(String[] args) {
        QuizQuestion[] quizQuestions = initializeQuizQuestions();

        Scanner scanner = new Scanner(System.in);

        for (QuizQuestion question : quizQuestions) {
            presentQuestion(question, scanner);
        }

        displayResult();
    }

    private static QuizQuestion[] initializeQuizQuestions() {
        QuizQuestion[] quizQuestions = new QuizQuestion[8];

        String[] options1 = { "A. Java", "B. Python", "C. C++", "D. JavaScript" };
        quizQuestions[0] = new QuizQuestion("What programming language is this quiz written in?", options1, 0);

        String[] options2 = { "A. Red", "B. Blue", "C. Green", "D. Yellow" };
        quizQuestions[1] = new QuizQuestion("What color is the sky on a clear day?", options2, 1);

        String[] options3 = { "A. 42", "B. 64", "C. 128", "D. 256" };
        quizQuestions[2] = new QuizQuestion("What is the square of 8?", options3, 1);

        // Additional DSA Questions
        String[] options4 = { "A. O(1)", "B. O(log N)", "C. O(N)", "D. O(N^2)" };
        quizQuestions[3] = new QuizQuestion("What is the time complexity of binary search?", options4, 1);

        String[] options5 = { "A. FIFO", "B. LIFO", "C. LILO", "D. FILO" };
        quizQuestions[4] = new QuizQuestion("Which data structure follows the Last In, First Out (LIFO) principle?",
                options5, 1);

        String[] options6 = { "A. Graph", "B. Tree", "C. Queue", "D. Stack" };
        quizQuestions[5] = new QuizQuestion("Which data structure is suitable for hierarchical relationships?",
                options6, 1);

        String[] options7 = { "A. QuickSort", "B. MergeSort", "C. BubbleSort", "D. InsertionSort" };
        quizQuestions[6] = new QuizQuestion("Which sorting algorithm has an average time complexity of O(n log n)?",
                options7, 1);

        String[] options8 = { "A. BFS", "B. DFS", "C. Dijkstra's", "D. Prim's" };
        quizQuestions[7] = new QuizQuestion("Which algorithm is used to find the shortest path in a graph?", options8,
                2);

        return quizQuestions;
    }

    private static void presentQuestion(QuizQuestion question, Scanner scanner) {
        System.out.println(question.question);

        for (int i = 0; i < question.options.length; i++) {
            System.out.println(question.options[i]);
        }

        System.out.print("Enter your answer (A, B, C, or D): ");

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("\nTime's up! Moving to the next question.");
                timer.cancel();
                timer.purge();
            }
        };

        timer.schedule(task, 15000); // 15 seconds timer

        String userAnswer = scanner.nextLine().toUpperCase();
        timer.cancel();
        timer.purge();

        if (userAnswer.equals(question.options[question.correctOptionIndex].substring(0, 1))) {
            System.out.println("Correct!\n");
            score++;
        } else {
            System.out.println(
                    "Incorrect! The correct answer is " + question.options[question.correctOptionIndex] + "\n");
        }
    }

    private static void displayResult() {
        System.out.println("Quiz completed!\nYour final score is: " + score + " out of 8");
    }
}
