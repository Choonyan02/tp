package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.NpcTrack;

/**
 * Send warning to clear the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Are you sure you want to clear? Enter 'yes' if you want to clear.";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Confirmation message to clear. "
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setAddressBook(new NpcTrack());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
