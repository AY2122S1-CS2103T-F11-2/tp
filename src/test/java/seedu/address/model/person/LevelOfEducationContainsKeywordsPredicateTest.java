package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class LevelOfEducationContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        LevelOfEducationContainsKeywordsPredicate firstPredicate =
                new LevelOfEducationContainsKeywordsPredicate(firstPredicateKeywordList);
        LevelOfEducationContainsKeywordsPredicate secondPredicate =
                new LevelOfEducationContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NameContainsKeywordsPredicate firstPredicateCopy = new NameContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_LevelOfEducationContainsKeywords_returnsTrue() {
        // One keyword
        LevelOfEducationContainsKeywordsPredicate predicate =
                new LevelOfEducationContainsKeywordsPredicate(Collections.singletonList("Masters"));
        assertTrue(predicate.test(new PersonBuilder().withLevelOfEducation("Masters").build()));
    }

    @Test
    public void test_LevelOfEducationDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        LevelOfEducationContainsKeywordsPredicate predicate =
                new LevelOfEducationContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withLevelOfEducation("PhD").build()));

        // Non-matching keyword
        predicate = new LevelOfEducationContainsKeywordsPredicate(Arrays.asList("Masters"));
        assertFalse(predicate.test(new PersonBuilder().withLevelOfEducation("PhD").build()));

        // Keywords match name, phone, email and address, but does not match level of education
        predicate = new LevelOfEducationContainsKeywordsPredicate(Arrays
                .asList("Alice", "12345", "alice@email.com", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").withLevelOfEducation("PhD").build()));
    }
}
