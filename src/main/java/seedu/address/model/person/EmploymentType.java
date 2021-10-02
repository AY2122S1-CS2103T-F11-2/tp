package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class EmploymentType {

    public enum Type {
        FULL_TIME("full time"),
        PART_TIME("part time"),
        TEMPORARY("temporary"),
        INTERNSHIP("internship");

        private final String term;
        Type(String term) {
            this.term = term;
        }
    }

    public static final String MESSAGE_CONSTRAINTS = "Employment type can only be one of the following: "
            + "full time, part time, temporary or internship";

    public final String employmentType;

    /**
     * Constructs a {@code EmploymentType}.
     *
     * @param employmentType A valid employment type.
     */
    public EmploymentType(String employmentType) {
        requireNonNull(employmentType);
        checkArgument(isValidEmploymentType(employmentType), MESSAGE_CONSTRAINTS);
        this.employmentType = employmentType;
    }

    /**
     * Returns true if a given string is a valid employment type.
     *
     * @param test String to be tested.
     * @return Boolean indicating if given string is a valid employment type.
     */
    public static boolean isValidEmploymentType(String test) {
        return test.equals(Type.FULL_TIME.term) || test.equals(Type.PART_TIME.term)
                || test.equals(Type.TEMPORARY.term) || test.equals(Type.INTERNSHIP.term) ;
    }

    @Override
    public String toString() {
        return employmentType;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EmploymentType // instanceof handles nulls
                && employmentType.equals(((EmploymentType) other).employmentType)); // state check
    }

    @Override
    public int hashCode() {
        return employmentType.hashCode();
    }

}
