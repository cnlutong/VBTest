package domain.algorithm;

import domain.WordBank;
import domain.model.Question;
import domain.model.UserModel;
import domain.model.Word;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class IRTRollbackTest {

    @Test
    public void testRollbackWhenEarlyTerminationTriggered() throws Exception {
        // 1. Setup - Create a dummy CSV for WordBank
        java.io.File tempFile = java.io.File.createTempFile("words", ".csv");
        java.nio.file.Files.writeString(tempFile.toPath(), "word,chinese,difficulty\napple,苹果,1\n");
        WordBank mockBank = new WordBank(tempFile.getAbsolutePath());

        IRTAlgorithm algorithm = new IRTAlgorithm(mockBank);
        UserModel user = new UserModel("test", "user");
        algorithm.initializeUser(user);

        List<Question> answeredQuestions = new ArrayList<>();

        // Initial ability is 0
        double initialAbility = user.getAbilityEstimate();

        // 2. Simulate questions
        // Let's say user answers 1st question correctly
        Word w1 = new Word("1", "apple", "苹果", 1);
        Question q1 = new Question(w1, List.of("苹果"), 0);
        algorithm.updateUserModel(user, q1, true);
        q1.isCorrect(0); // Mark as correct for answeredQuestions logic
        answeredQuestions.add(q1);

        double abilityAfterFirstCorrect = user.getAbilityEstimate();
        assertTrue(abilityAfterFirstCorrect > initialAbility, "Ability should increase after correct answer");

        // 3. Now trigger early termination: 4 wrongs in a row
        // The window size is 5, so [Correct, Wrong, Wrong, Wrong, Wrong] triggers it (4
        // wrongs)
        for (int i = 0; i < 4; i++) {
            Word wi = new Word("err" + i, "word" + i, "fail" + i, 5);
            Question qi = new Question(wi, List.of("fail" + i), 0);

            // Record what ability was before this sequence of errors started
            // For the first error, this snapshot should be equal to
            // abilityAfterFirstCorrect

            algorithm.updateUserModel(user, qi, false);
            qi.isCorrect(1); // Mark as incorrect
            answeredQuestions.add(qi);
        }

        // Verify user model shows 4 recent wrongs
        assertEquals(4, user.getRecentWrongCount());
        assertTrue(algorithm.isTestComplete(user, answeredQuestions));

        // 4. Calculate Final Result
        var result = algorithm.calculateFinalResult(user, answeredQuestions);

        // The final ability in the result SHOULD be rolled back to the state AFTER the
        // first correct
        // answer but BEFORE the first of the four errors began.
        // Wait, the logic says: rollback to the snapshot of the question that first
        // failed in the window.
        // In our sequence: [Correct, Wrong1, Wrong2, Wrong3, Wrong4]
        // Window is last 5. 1st wrong is Wrong1.
        // Q1 (Correct) snapshot: 0.0
        // Q2 (Wrong1) snapshot: abilityAfterFirstCorrect
        // Final Ability should be snapshot of Q2 = abilityAfterFirstCorrect

        assertEquals(abilityAfterFirstCorrect, result.getFinalAbilityEstimate(), 1e-9,
                "Final ability should be rolled back to state before the errors started");

        assertTrue(result.getFinalAbilityEstimate() > user.getAbilityEstimate(),
                "The result ability should be higher than the current (deducted) user ability");
    }
}
