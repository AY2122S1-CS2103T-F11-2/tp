package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Marks an applicant as "Done" identified using it's displayed index from the address book.
 */
public class MarkCommand extends MarkingCommand {

    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the applicants identified by the index numbers used in the displayed person list as Done.\n"
            + "Parameters: INDEX... (must be positive integers)\n"
            + "Example: " + COMMAND_WORD + " 1 4 7";

    public static final String MESSAGE_MARKED_PERSON_SUCCESS = "Marked Person(s) as Done: \n%1$s";

    private final Index[] targetIndexes;

    public MarkCommand(Index[] targetIndexes) {
        this.targetIndexes = targetIndexes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        StringBuilder result = new StringBuilder();

        for (Index targetIndex : targetIndexes) {
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            Person personToMark = lastShownList.get(targetIndex.getZeroBased());
            model.markPerson(personToMark);
            result.append(personToMark);

        }

        return new CommandResult(String.format(MESSAGE_MARKED_PERSON_SUCCESS, result.toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MarkCommand // instanceof handles nulls
                && Arrays.equals(targetIndexes, ((MarkCommand) other).targetIndexes)); // state check
    }
}
