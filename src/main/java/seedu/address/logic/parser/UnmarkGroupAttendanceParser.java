package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.MarkGroupAttendanceCommand;
import seedu.address.logic.commands.UnmarkGroupAttendanceCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Attendance;
import seedu.address.model.person.Group;

/**
 * Parses input arguments and creates a new {@code UnMarkGroupAttendanceCommand} object
 */
public class UnmarkGroupAttendanceParser implements Parser<UnmarkGroupAttendanceCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code UnmarkGroupAttendanceCommand}
     * and returns a {@code UnmarkGroupAttendanceCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnmarkGroupAttendanceCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TUTORIAL);
        // parse group
        Group group;
        try {
            group = ParserUtil.parseGroup(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MarkGroupAttendanceCommand.MESSAGE_USAGE), ive);
        }
        // parse tutorial
        int tutorial = 0;
        if (argMultimap.getValue(PREFIX_TUTORIAL).isPresent()) {
            tutorial = ParserUtil.parseTutorial(argMultimap.getValue(PREFIX_TUTORIAL).get());
        }

        if (!(tutorial >= 1 && tutorial <= 12)) {
            throw new ParseException(Attendance.TUTORIAL_ERROR_MSG);
        }
        return new UnmarkGroupAttendanceCommand(group, Index.fromOneBased(tutorial));
    }
}