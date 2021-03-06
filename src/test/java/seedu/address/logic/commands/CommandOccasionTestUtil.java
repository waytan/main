package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OCCASION_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OCCASION_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OCCASION_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.occasion.Occasion;

/**
 * Contains helper methods for testing commands.
 */
public class CommandOccasionTestUtil {

    public static final String VALID_OCCASIONNAME_ONE = "CS2103 Exam";
    public static final String VALID_OCCASIONNAME_TWO = "Start of Vacation";
    public static final String VALID_OCCASIONDATE_ONE = "2018-12-05";
    public static final String VALID_OCCASIONDATE_TWO = "2018-12-06";
    public static final String VALID_OCCASIONLOCATION_ONE = "MPSH 1A";
    public static final String VALID_OCCASIONLOCATION_TWO = "Home";
    public static final String VALID_TAG_STUDY = "Must Prepare";
    public static final String VALID_TAG_SLEEP = "More Sleep";

    public static final String OCCASIONNAME_DESC_ONE = " " + PREFIX_OCCASION_NAME + VALID_OCCASIONNAME_ONE;
    public static final String OCCASIONNAME_DESC_TWO = " " + PREFIX_OCCASION_NAME + VALID_OCCASIONNAME_TWO;
    public static final String OCCASIONDATE_DESC_ONE = " " + PREFIX_OCCASION_DATE + VALID_OCCASIONDATE_ONE;
    public static final String OCCASIONDATE_DESC_TWO = " " + PREFIX_OCCASION_DATE + VALID_OCCASIONDATE_TWO;
    public static final String OCCASIONLOCATION_DESC_ONE = " " + PREFIX_OCCASION_LOCATION + VALID_OCCASIONLOCATION_ONE;
    public static final String OCCASIONLOCATION_DESC_TWO = " " + PREFIX_OCCASION_LOCATION + VALID_OCCASIONLOCATION_TWO;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_STUDY;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_SLEEP;

    public static final String INVALID_OCCASIONNAME_DESC =
            " " + PREFIX_OCCASION_NAME + "Exam!"; // '!' not allowed in names
    public static final String INVALID_OCCASIONDATE_DESC =
            " " + PREFIX_OCCASION_DATE + "2015-20-10"; // YYYY-MM-DD format used, not YYYY-DD-MM
    public static final String INVALID_OCCASIONLOCATION_DESC = " " + PREFIX_OCCASION_LOCATION + "SR"; // not 3-20 chars
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "HOORAY!"; // '!' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the result message matches {@code expectedMessage} <br>
     * - the {@code actualModel} matches {@code expectedModel} <br>
     * - the {@code actualCommandHistory} remains unchanged.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandHistory actualCommandHistory,
            String expectedMessage, Model expectedModel) {
        CommandHistory expectedCommandHistory = new CommandHistory(actualCommandHistory);
        try {
            CommandResult result = command.execute(actualModel, actualCommandHistory);
            assertEquals(expectedMessage, result.feedbackToUser);
            assertEquals(expectedModel, actualModel);
            assertEquals(expectedCommandHistory, actualCommandHistory);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book and the filtered person list in the {@code actualModel} remain unchanged <br>
     * - {@code actualCommandHistory} remains unchanged.
     */
    public static void assertCommandFailure(Command command, Model actualModel, CommandHistory actualCommandHistory,
            String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Occasion> expectedFilteredList = new ArrayList<>(actualModel.getFilteredOccasionList());

        CommandHistory expectedCommandHistory = new CommandHistory(actualCommandHistory);

        try {
            command.execute(actualModel, actualCommandHistory);
            throw new AssertionError("The expected CommandException was not thrown.");
        } catch (CommandException e) {
            assertEquals(expectedMessage, e.getMessage());
            assertEquals(expectedAddressBook, actualModel.getAddressBook());
            assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
            assertEquals(expectedCommandHistory, actualCommandHistory);
        }
    }

    /**
     * Updates {@code model}'s filtered list to show only the occasion at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showOccasionAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredOccasionList().size());

        Occasion occasion = model.getFilteredOccasionList().get(targetIndex.getZeroBased());
        model.updateFilteredOccasionList(occasion1 -> (occasion1.isSameOccasion(occasion)));

        assertEquals(1, model.getFilteredOccasionList().size());
    }

    /**
     * Deletes the first occasion in {@code model}'s filtered list from {@code model}'s address book.
     */
    public static void deleteFirstOccasion(Model model) {
        Occasion firstOccasion = model.getFilteredOccasionList().get(0);
        model.deleteOccasion(firstOccasion);
        model.commitAddressBook();
    }
}
