package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXPECTED_SALARY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.AddressContainsKeywordsPredicate;
import seedu.address.model.person.EmailContainsKeywordsPredicate;
import seedu.address.model.person.ExpectedSalaryWithinRangePredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.PhoneContainsKeywordsPredicate;
import seedu.address.model.tag.TagContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        // One whitespace required before first prefix.
        String trimmedArgs = " " + args.trim();

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenizeWithoutPreamble(trimmedArgs, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_ADDRESS, PREFIX_EXPECTED_SALARY, PREFIX_TAG);

        if (argMultimap.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        FindDescriptor findDescriptor = new FindDescriptor(argMultimap);

        return new FindCommand(findDescriptor.getPredicates());
    }

    public static class FindDescriptor {
        private ArrayList<Predicate<Person>> predicateList = new ArrayList<>();

        /**
         * Constructs a FindDescriptor.
         * FindDescriptors extracts user input for each Prefix and converts them into a list of Predicates.
         */
        FindDescriptor(ArgumentMultimap argMultimap) {

            if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
                String arg = argMultimap.getValue(PREFIX_NAME).get();
                String trimmedArg = arg.trim();
                if (!trimmedArg.isEmpty()) {
                    String[] keywords = splitByWhiteSpace(trimmedArg);
                    predicateList.add(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
                }
            }
            if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
                String arg = argMultimap.getValue(PREFIX_PHONE).get();
                String trimmedArg = arg.trim();
                if (!trimmedArg.isEmpty()) {
                    String[] keywords = splitByWhiteSpace(trimmedArg);
                    predicateList.add(new PhoneContainsKeywordsPredicate(Arrays.asList(keywords)));
                }
            }
            if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
                String arg = argMultimap.getValue(PREFIX_EMAIL).get();
                String trimmedArg = arg.trim();
                if (!trimmedArg.isEmpty()) {
                    String[] keywords = splitByWhiteSpace(trimmedArg);
                    predicateList.add(new EmailContainsKeywordsPredicate(Arrays.asList(keywords)));
                }
            }
            if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
                String arg = argMultimap.getValue(PREFIX_ADDRESS).get();
                String trimmedArg = arg.trim();
                if (!trimmedArg.isEmpty()) {
                    String[] keywords = splitByWhiteSpace(trimmedArg);
                    predicateList.add(new AddressContainsKeywordsPredicate(Arrays.asList(keywords)));
                }
            }
            if (argMultimap.getValue(PREFIX_EXPECTED_SALARY).isPresent()) {
                String arg = argMultimap.getValue(PREFIX_EXPECTED_SALARY).get();
                String trimmedArg = arg.trim();
                if (!trimmedArg.isEmpty()) {
                    String[] keywords = splitByWhiteSpace(trimmedArg);
                    predicateList.add(new ExpectedSalaryWithinRangePredicate(Arrays.asList(keywords)));
                }
            }
            if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
                String arg = argMultimap.getValue(PREFIX_TAG).get();
                String trimmedArg = arg.trim();
                if (!trimmedArg.isEmpty()) {
                    String[] keywords = splitByWhiteSpace(trimmedArg);
                    predicateList.add(new TagContainsKeywordsPredicate(Arrays.asList(keywords)));
                }
            }
        }

        public ArrayList<Predicate<Person>> getPredicates() {
            return predicateList;
        }

        private static String[] splitByWhiteSpace(String arg) {
            return arg.split("\\s+");
        }
    }
}
